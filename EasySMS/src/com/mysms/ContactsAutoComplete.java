package com.mysms;


import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.telephony.gsm.SmsManager;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") public class ContactsAutoComplete extends Activity {
	 Button send;
	  private String id;
	   private String lookupKey;
	   private String name;
	   private int phcounter;
	   NetworkInfo info;
	   String msgEnc;
	   ArrayList al=new ArrayList();
		 String[] str;
		 SQLiteDatabase sampleDB = null;
	 MultiAutoCompleteTextView textView;
	 MultiAutoCompleteTextView myMultiAutoCompleteTextView; 
	 EditText msg;
	 TextView chars;
	 ImageView ii;
		private final String SAMPLE_DB_NAME = "MySmsDB";
		private final String SAMPLE_TABLE_NAME = "MySmsTable";
		
	 int chars1=0;
        @TargetApi(Build.VERSION_CODES.ECLAIR)
		@Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.screen2);
               send=(Button)findViewById(R.id.send);
               msg=(EditText)findViewById(R.id.msg);
              // ii=(ImageView)findViewById(R.id.image1);
               myMultiAutoCompleteTextView = (MultiAutoCompleteTextView)findViewById(R.id.contacts);
                    
             Bundle bundle=getIntent().getExtras();
             if(bundle!=null){
            	 String temp=bundle.getString("BulkSms");
            	 Toast.makeText(getBaseContext(), temp, 20).show();
            	 myMultiAutoCompleteTextView.setText(temp);
             }
                              msg.setOnKeyListener(new OnKeyListener(){

				
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					// TODO Auto-generated method stub
					
					chars.setText(Integer.toString(chars1));
					chars1=msg.getText().toString().length();
					
					
					if(chars1<158)
					chars.setTextColor(Color.WHITE);
					else{
					
						
						//msg.setText(msg.getText().toString().substring(0, 8));
						return true;
               }
					return false;
				}
            	   
               });
               chars=(TextView)findViewById(R.id.chars);
              // chars.setText("160");
                send.setOnClickListener(new OnClickListener(){
    
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						send();
						 
					}
                	
                });
                
                
                
             
               
                
                ContentResolver cr = getContentResolver();
                Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null);
               str=new String[cur.getCount()];
                int i=0;
                if (cur.getCount() > 0) {
                	while (cur.moveToNext()) {
                		String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                		String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                		if (Integer.parseInt(cur.getString(
                                cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                             Cursor pCur = cr.query(
                            		 ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
                            		 null, 
                            		 ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
                            		 new String[]{id}, null);
                             while (pCur.moveToNext()) {
                            	 String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            	 String phoneno=" "+name+"- "+phoneNo;
                                 // str[i]=phoneno;
                                  al.add(phoneno);
                  				i++;
                            	
                             } 
              	        pCur.close();
              	    }
                	}
                }
              // al.toArray(str);
                		myMultiAutoCompleteTextView.setAdapter(
                        new ArrayAdapter<String>(this, 
                          R.layout.list_item,al));
                      myMultiAutoCompleteTextView.setTokenizer(
                        new MultiAutoCompleteTextView.CommaTokenizer());
              
    }


   

	protected void send() {
			// TODO Auto-generated method stub
    	//Toast.makeText(getBaseContext(), "hi", Toast.LENGTH_SHORT).show();
		String no=myMultiAutoCompleteTextView.getText().toString();
		//Toast.makeText(getBaseContext(),no, Toast.LENGTH_SHORT).show();
			String message=msg.getText().toString();
			int i=no.length();
			int j=message.length();
			
		//	Toast.makeText(this,"hi"+ j, 20).show();
			if(j>160){
				Toast.makeText(this, "yes",10).show();
				message=message.substring(1,159);}
			
			if(i==0 ||i<=9){
				Toast.makeText(this, "Please Enter Phone number", 20).show();
			}else
				if(j==0){
					Toast.makeText(this, "Please Enter Message", 20).show();
				}else{
					
					
					Intent it=new Intent("com.mysms.SENDINGMESSAGE");
					it.putExtra("message", message);
					it.putExtra("number", no);
					//startActivityForResult(it, 0);
						//Toast.makeText(getBaseContext(), "yes", 20).show();
						SendSmsApp sms=new SendSmsApp();
						//EmployeeTable emp=new EmployeeTable(this);
						Toast.makeText(getApplicationContext(), "siva"+no, 90).show();
						String SALTCHARS = "1234567890";
				        StringBuilder salt = new StringBuilder();
				        Random rnd = new Random();
				        while (salt.length() < 4) {
				            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				            salt.append(SALTCHARS.charAt(index));
				        }
				        String saltStr = salt.toString().trim();
						 try {
							 msgEnc = "encrp"+AESHelper.encrypt(message, saltStr);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
						
						boolean flag=sms.sendSms(msgEnc,no,saltStr);
						if(flag){
							info=null;
							Toast.makeText(this, "your message hasbeen sent succesfuly", 20).show();
							msg.setText("");
							myMultiAutoCompleteTextView.setText("");
							chars.setText("0");
							String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
							Toast.makeText(this, currentDateTimeString, 02).show();
							 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
								sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
						    			SAMPLE_TABLE_NAME +
						    			" (smsId  integer primary key autoincrement, intentedName VARCHAR," +
						    			" number VARCHAR, date VARCHAR);");
								sampleDB.execSQL("INSERT INTO " +
						    			SAMPLE_TABLE_NAME +
						    			"(intentedName,number,date) Values ('"+no+"','"+message+"','"+currentDateTimeString+"');");
						    	Cursor c = sampleDB.rawQuery("SELECT intentedName, number,date FROM " +
						    			SAMPLE_TABLE_NAME , null);
						    	//sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME);
								if (c != null ) {
						    		if  (c.moveToFirst()) {
						    			do {
						    				String firstName = c.getString(c.getColumnIndex("intentedName"));
						    				String num = c.getString(c.getColumnIndex("number"));
						    				String date = c.getString(c.getColumnIndex("date"));
						    			//	Toast.makeText(this,firstName+" "+num+" "+date,20).show();
						    			}while (c.moveToNext());
						    		} }
								
					}
					}

					
				}
		



    private boolean haveInternet(){
         info= ((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info==null || !info.isConnected()) {
                return false;
        }
        else
        return true;
}		
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0)
			doRefresh();
	}


	private void doRefresh() {
		// TODO Auto-generated method stub
		myMultiAutoCompleteTextView.setText("");
		msg.setText("");
		chars1=0;
		chars.setText(Integer.toString(chars1));
		 
    	
	}
    

}

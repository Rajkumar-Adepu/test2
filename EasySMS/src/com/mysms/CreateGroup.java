package com.mysms;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CreateGroup extends Activity {
	static final String[] COUNTRIES = new String[] {
	    "Afghanistan", "Albania", "Algeria"};
	private final String SAMPLE_DB_NAME = "MySmsDB";
	private final String SAMPLE_TABLE_NAME = "MyGroups";
	private final String SAMPLE_TABLE_NAME2 = "MyNewGroupsNumbers";
	 String[] str;
	 SQLiteDatabase sampleDB = null;
	 ArrayList smshis=new ArrayList();
	 String createnew;
	 ListView lv;
	// String grupname,numbers;
	 String[] colours;
		protected ArrayList<CharSequence> selectedColours = new ArrayList<CharSequence>();
		protected ArrayList<CharSequence> selectedColours2 = new ArrayList<CharSequence>();
		protected ArrayList<CharSequence> selectedColours3 = new ArrayList<CharSequence>();
		private String groupname;
		private String number,number3;
		
		String detetItem;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ss);
		CheckBox cc=new CheckBox(this);
		//smshis.add("Create New");
	Button bt=(Button)findViewById(R.id.button1);
	bt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			openOptionsMenu();
		}
	});
		lv=(ListView)findViewById(R.id.listView1);
		lv.setAdapter(new ArrayAdapter<String>(this,R.layout.list_item2,smshis));
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String sss=((TextView) arg1).getText().toString();
			//	Toast.makeText(getBaseContext(), ((TextView) arg1).getText(), 20).show();
			onPopMenus(sss);
					
			}
			
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
	     // Toast.makeText(getBaseContext(),((TextView) arg1).getText(),Toast.LENGTH_LONG).show();
	      setDetetItem(((TextView) arg1).getText().toString());
				registerForContextMenu(lv);
				
				return false;
			}
			
		});
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
	    			SAMPLE_TABLE_NAME +
	    			" (groupId  integer primary key autoincrement, groupName VARCHAR);");
			
			
			
				    Cursor c = sampleDB.rawQuery("SELECT groupName FROM " +
	    			SAMPLE_TABLE_NAME , null);
	    	
			if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String firstName = c.getString(c.getColumnIndex("groupName"));
	    				//String num = c.getString(c.getColumnIndex("number"));
	    				//String date = c.getString(c.getColumnIndex("date"));
	    				//int ss=c.getInt(c.getColumnIndex("smsId"));
	    				String temp=firstName;
	    				smshis.add(firstName);
	    				//Toast.makeText(this,temp,20).show();
	    			}while (c.moveToNext());
	    		}else{
	    			smshis.add("please add groups");

	    		}
	    		}
			
	}
	
	public String getDetetItem() {
		return detetItem;
	}

	public void setDetetItem(String detetItem) {
		this.detetItem = detetItem;
	}

	@Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                    ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            
            
      //      Toast.makeText(getBaseContext(),((TextView) v).getText(),Toast.LENGTH_LONG).show();
			
            menu.setHeaderTitle("Choose an option");
            menu.add(0, v.getId(), 0, "Delete");
            menu.add(0, v.getId(), 0, "Delete All");
    }
	
	 public boolean onContextItemSelected(MenuItem item) {
         AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

         if (item.getTitle() == "Delete") {
        	String dItem=getDetetItem();
        	Toast.makeText(this, dItem, 20).show();
        	 
        	 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
        		sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME+ " where groupName='"+dItem+"'");
        		sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME2+ " where groupName='"+dItem+"'");
        		Intent it=new Intent(CreateGroup.this,CreateGroup.class);
        		startActivity(it);
         }
          // Do something }
         else if (item.getTitle() == "Delete All") {
        	 Toast.makeText(this, "deleting all", 20).show();
        	 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
        	 sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME);
        	 sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME2);
        	 Intent it=new Intent(CreateGroup.this,CreateGroup.class);
     		startActivity(it);
                 // Do something
         } else {
                 return false;
         }
         return true;
 }
	 public boolean onCreateOptionsMenu(Menu menu){
 		
 		super.onCreateOptionsMenu(menu);
 		MenuInflater mi=getMenuInflater();
 		mi.inflate(R.menu.main_menu, menu);
 		
 		return true;
 		
 	}
 	public boolean onOptionsItemSelected(MenuItem item){
 		
 		switch (item.getItemId()) {
 		case R.id.sweetmenu:
 			//Toast.makeText(this, "fi", 20).show();
 			Intent it=new Intent(this,CreateNewGroup.class);
 			startActivityForResult(it, 0);
 		return true;
 		
 		
 		}
 		return false;
 		
 		
 	}
	protected void onPopMenus(String groupname) {
		// TODO Auto-generated method stub
		
		this.groupname=groupname;
		selectChecked();
		  ContentResolver cr = getContentResolver();
	        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
	                null, null, null, null);
	       colours=new String[cur.getCount()];
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
	                    	 String phoneno=phoneNo;
	                    	 colours[i]=phoneno;
	                    //	 Log.d("flag3", temp);
	                  //  	 Log.d("flag4", phoneno);
	                       //   al.add(phoneno);
	          				i++;
	                    	
	                     } 
	      	        pCur.close();
	      	    }
	        	}
	        }
	        showSelectColoursDialog();
	}
	private void selectChecked() {
		// TODO Auto-generated method stub
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
	    			SAMPLE_TABLE_NAME2 +
	    			" (groupcontactId  integer primary key autoincrement, groupName VARCHAR,nameNumber VARCHAR);");
			
			
		//	number="";
				    Cursor c = sampleDB.rawQuery("SELECT groupName,nameNumber FROM " +
	    			SAMPLE_TABLE_NAME2 +" WHERE groupName='"+groupname+"'", null);
	    	
			if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String firstName = c.getString(c.getColumnIndex("groupName"));
	    				String nameNumber = c.getString(c.getColumnIndex("nameNumber"));
	    					    				String temp=nameNumber;
	    				selectedColours3.add(temp);
	    		
	    			}while (c.moveToNext());
	    		} }
	}

	protected void onChangeSelectedColours() {
		StringBuilder stringBuilder = new StringBuilder();

		for(CharSequence colour : selectedColours)
			number=colour + ",";
		
	//send2Db(number);
	//Toast.makeText(this,"fdfgg"+ number, 10).show();
		//selectColoursButton.setText(stringBuilder.toString());
	}
	private void send2Db(String number) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, number, 20).show();
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
	    			SAMPLE_TABLE_NAME2 +
	    			" (groupcontactId  integer primary key autoincrement, groupName VARCHAR,nameNumber VARCHAR);");
			sampleDB.execSQL("INSERT INTO " +
	    			SAMPLE_TABLE_NAME2 +
	    			"(groupName,nameNumber) Values ('"+groupname+"','"+number+"');");
	    
			
			number="";
				    Cursor c = sampleDB.rawQuery("SELECT groupName,nameNumber FROM " +
	    			SAMPLE_TABLE_NAME2 +" WHERE groupName='"+groupname+"'", null);
	    	
			if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String firstName = c.getString(c.getColumnIndex("groupName"));
	    				String nameNumber = c.getString(c.getColumnIndex("nameNumber"));
	    				//String num = c.getString(c.getColumnIndex("number"));
	    				//String date = c.getString(c.getColumnIndex("date"));
	    				//int ss=c.getInt(c.getColumnIndex("smsId"));
	    				String temp=nameNumber;
	    			//	smshis.add(temp);
	    				//Toast.makeText(this,temp,20).show();
	    			//	Log.d("flag2", temp);
	    			}while (c.moveToNext());
	    		} }
		//	sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME2);
	}

	protected void showSelectColoursDialog() {
		boolean[] checkedColours = new boolean[colours.length];
		int count = colours.length;

		for(int i = 0; i < count; i++){
			
			checkedColours[i] = selectedColours3.contains(colours[i]);
			
		}
		
			selectedColours3.removeAll(selectedColours3);
			
		DialogInterface.OnMultiChoiceClickListener coloursDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked){
					selectedColours.add(colours[which]);
				inserintoDb();	
				}
				else{
					selectedColours2.add(colours[which]);
					deleteDb();
				}
				onChangeSelectedColours();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select Colours");
		builder.setMultiChoiceItems(colours, checkedColours, coloursDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	protected void deleteDb() {
		// TODO Auto-generated method stub
		for(CharSequence colour : selectedColours2)
			 number3=colour + ",";
	//Toast.makeText(this,"delte"+ number, 10).show();
		number3=number3.replace(",", "");
		delete2Db(number3);
	}

	private void delete2Db(String number2) {
		// TODO Auto-generated method stub
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			
			sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME2+" where nameNumber='"+number2+"'");
			
			number2="";
				    Cursor c = sampleDB.rawQuery("SELECT groupName,nameNumber FROM " +
	    			SAMPLE_TABLE_NAME2 +" WHERE groupName='"+groupname+"'", null);
	    	
			if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String firstName = c.getString(c.getColumnIndex("groupName"));
	    				String nameNumber = c.getString(c.getColumnIndex("nameNumber"));
	    				//String num = c.getString(c.getColumnIndex("number"));
	    				//String date = c.getString(c.getColumnIndex("date"));
	    				//int ss=c.getInt(c.getColumnIndex("smsId"));
	    				String temp=firstName+" "+nameNumber;
	    			//	smshis.add(temp);
	    				//Toast.makeText(this,temp,20).show();
	    			}while (c.moveToNext());
	    		} }
		
	}

	protected void inserintoDb() {
		// TODO Auto-generated method stub
		for(CharSequence colour : selectedColours)
			number=colour + ",";
		//Toast.makeText(this,"insert"+ number, 10).show();
	number=number.replace(",", "");
		send2Db(number);
	}

	protected void ceateNew() {
		// TODO Auto-generated method stub
		//Toast.makeText(this, "fi", 20).show();
		Intent it=new Intent(this,CreateNewGroup.class);
		startActivityForResult(it, 0);
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0)
			doRefresh();
	}


	private void doRefresh() {
		// TODO Auto-generated method stub
		
			//lv.setAdapter(new ArrayAdapter<String>(this,R.layout.list_item2,smshis));
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent it=new Intent(this,Main.class);
		startActivity(it);
	}
	
}

package com.mysms;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class inbox extends Activity {
	ArrayList<String> ListItems = new ArrayList<String>();
	ArrayList<String>a1=new ArrayList<String>();
	ArrayList<String>al2=new ArrayList<String>();
	ArrayList<String>a2=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private static final String PNUM = ContactsContract.CommonDataKinds.Phone.NUMBER;

    protected void onCreate(Bundle savedInstanceState)
    {      
           super.onCreate(savedInstanceState);
           setContentView(R.layout.mainlist);
           final ListView lv=(ListView)findViewById(R.id.listView1);
           Uri uri = Uri.parse("content://sms/inbox");
           Cursor c = getContentResolver().query(uri,new String[] { "_id","address","body"},null,null,null); 
           String number = null;
           String name=null;
           String id=null;
           long date2=0;
           Cursor c2=null;
   		if(c!=null){
        if(c.moveToFirst()) {
       	 do{
       	number = c.getString(1);
       	
       	c2=getContentResolver().query
           (ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
           		null, PNUM + " = ?",  new String[]{number},
           		ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
       	int i=c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
      startManagingCursor(c2);
   		
   		if(c2.moveToFirst())
   		{
   		 String body = c.getString(c.getColumnIndexOrThrow("body")).toString();
   				name=c2.getString(i);
   			

   				
   				
   				a1.add(name+":"+number+":"+"\n"+body);
   				lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a1));
   				
   		}else{
   		 String body = c.getString(c.getColumnIndexOrThrow("body")).toString();
   			
           a1.add(""+":"+number+"\n"+body);
   		}
   		}while(c.moveToNext());
       	 
        }
        else{
        	Toast.makeText(getApplicationContext(), "No messages",90).show();
        	Intent it=new Intent(inbox.this,Main.class);
        	startActivity(it);}
        }
       	 
        
  
          lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String ss=lv.getItemAtPosition(arg2).toString();
				String[] aa=ss.split(":");
				String a=aa[1];
				Intent it=new Intent(inbox.this,ContactsAutoComplete.class);
				it.putExtra("BulkSms",a);
				startActivity(it);
				Toast.makeText(getApplicationContext(), a, 90).show();
				
			}
          		});

        	 }}

package com.mysms;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SendBulkSms extends ListActivity{
	private final String SAMPLE_DB_NAME = "MySmsDB";
	private final String SAMPLE_TABLE_NAME = "MyGroups";
	 SQLiteDatabase sampleDB = null;
	 ArrayList smshis=new ArrayList();
		private final String SAMPLE_TABLE_NAME2 = "MyNewGroupsNumbers";
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,R.layout.list_item2,smshis));
		ListView lv=getListView();
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String sss=((TextView) arg1).getText().toString();
			//	Toast.makeText(getBaseContext(), ((TextView) arg1).getText(), 20).show();
			sendBulkSms(sss);
					
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
	    				smshis.add(temp);
	    				//Toast.makeText(this,temp,20).show();
	    			}while (c.moveToNext());
	    		} }

	}
	protected void sendBulkSms(String sss) {
		// TODO Auto-generated method stub
		Toast.makeText(this, sss, 30).show();
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
	    			SAMPLE_TABLE_NAME2 +
	    			" (groupcontactId  integer primary key autoincrement, groupName VARCHAR,nameNumber VARCHAR);");
			
			
		//	number="";
				    Cursor c = sampleDB.rawQuery("SELECT groupName,nameNumber FROM " +
	    			SAMPLE_TABLE_NAME2 +" WHERE groupName='"+sss+"'", null);
				    String temp = "";
			if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String firstName = c.getString(c.getColumnIndex("groupName"));
	    				String nameNumber = c.getString(c.getColumnIndex("nameNumber"));
	    				//String num = c.getString(c.getColumnIndex("number"));
	    				//String date = c.getString(c.getColumnIndex("date"));
	    				//int ss=c.getInt(c.getColumnIndex("smsId"));
	    				temp+=nameNumber+", ";
	    				//selectedColours3.add(temp);
	    				
	    		//		Log.d("flag3", temp);
	    			}while (c.moveToNext());
	    		} }
			Toast.makeText(this,temp,20).show();
			
			Intent it=new Intent(this,ContactsAutoComplete.class);
			it.putExtra("BulkSms", temp);
			startActivity(it);
	}


	
}

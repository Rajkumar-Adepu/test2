package com.mysms;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewGroup extends Activity{
	private final String SAMPLE_DB_NAME = "MySmsDB";
	private final String SAMPLE_TABLE_NAME = "MyGroups";
	 String[] str;
	 SQLiteDatabase sampleDB = null;
	 String ss;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createnewgroup);
		
		Button btn=(Button)findViewById(R.id.create);
		final EditText group=(EditText)findViewById(R.id.edittext);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ss=group.getText().toString();
				if(ss==""){
					Toast.makeText(getBaseContext(), "Please enter group name", 30).show();
					
				}
				else
				xxx(ss);
			}
			
		});
	}

	protected void xxx(String ss) {
		// TODO Auto-generated method stub
		sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
	    			SAMPLE_TABLE_NAME +
	    			" (groupId  integer primary key autoincrement, groupName VARCHAR);");
			//sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME);
			sampleDB.execSQL("INSERT INTO " +
	    			SAMPLE_TABLE_NAME +
	    			"(groupName) Values ('"+ss+"');");
	    
			
			
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
	    			//	smshis.add(temp);
	    				//Toast.makeText(this,temp,20).show();
	    			}while (c.moveToNext());
	    		} }
			Intent it=new Intent(this,CreateGroup.class);
			startActivity(it);
			finish();
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

	
}

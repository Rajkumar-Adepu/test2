package com.mysms;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class displayHistory extends ListActivity {
	private final String SAMPLE_DB_NAME = "MySmsDB";
	private final String SAMPLE_TABLE_NAME = "MySmsTable";
	 String[] str;
	 String dddd="";
	 SQLiteDatabase sampleDB = null;
	 ListView smsHistory;
	 ListView lv;
	 int temp;
	 int itemId;
ArrayList smshis=new ArrayList();
ArrayList smshis2=new ArrayList();
ArrayList smshis3=new ArrayList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this,R.layout.list_item2,smshis));
		
		lv=getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				dddd=(String) ((TextView) arg1).getText();
				temp=arg2;
				popactivity(arg2);
			}
			
		})	;
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				setItemId(arg2);
				return false;
			}
			
		});
		@SuppressWarnings("unused")
		String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
		//Toast.makeText(this, currentDateTimeString, 02).show();
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
			sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
	    			SAMPLE_TABLE_NAME +
	    			" (smsId  integer primary key autoincrement, intentedName VARCHAR," +
	    			" number VARCHAR, date VARCHAR);");
		
	    	Cursor c = sampleDB.rawQuery("SELECT smsId,intentedName,number,date FROM " +
	    			SAMPLE_TABLE_NAME , null);
	    //	Toast.makeText(getBaseContext(), c.toString(), 20).show();
			if (c != null ) {
	    		if  (c.moveToFirst()) {
	    			do {
	    				String firstName = c.getString(c.getColumnIndex("intentedName"));
	    				String num = c.getString(c.getColumnIndex("number"));
	    				String date = c.getString(c.getColumnIndex("date"));
	    				int index=c.getInt(c.getColumnIndex("smsId"));
	    				//int ss=c.getInt(c.getColumnIndex("smsId"));
	    				String temp=firstName+"\n"+date;
	    				smshis.add(temp);
	    				smshis2.add(num);
	    				smshis3.add(index);
	    				//Toast.makeText(this,temp,20).show();
	    			}while (c.moveToNext());
	    		}else{
					Toast.makeText(getBaseContext(), "No Messages", 20).show();
					Intent it=new Intent(this,Main.class);
					startActivity(it);
				} }
			else{
				//Toast.makeText(getBaseContext(), c.toString(), 20).show();
			}
			registerForContextMenu(getListView());
	}


	public int getItemId() {
		return itemId;
	}


	public void setItemId(int itemId) {
		this.itemId = itemId;
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
        	int id=getItemId();
        	int id2=Integer.parseInt(smshis3.get(id).toString());
        	//Toast.makeText(this,dItem, 20).show();
        	 
        	 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
             sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME+ " where smsId='"+id2+"'");
             Intent it=new Intent(this,displayHistory.class);
     		startActivity(it);
         }
          // Do something }
         else if (item.getTitle() == "Delete All") {
        	 Toast.makeText(this, "deleting all", 20).show();
        	 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
        	 sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME);
        	 Intent it=new Intent(this,displayHistory.class);
     		startActivity(it);
                 // Do something
         } else {
                 return false;
         }
         return true;
 }

	protected void popactivity(int arg2) {
		// TODO Auto-generated method stub
		//int id=arg2+1;
		//Toast.makeText(this, Integer.toString(id),20).show();
		String sss=smshis2.get(arg2).toString();
		//int smsId=Integer.parseInt(smshis3.get(arg2).toString());
		//Toast.makeText(getBaseContext(), smshis3.get(arg2).toString(), 20).show();
	    
			
			Intent it=new Intent(this,PopActivity.class);
			it.putExtra("message", sss);
			it.putExtra("id", smshis3.get(arg2).toString());
			startActivityForResult(it, 0);
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

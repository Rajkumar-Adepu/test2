package com.mysms;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MessagesActivity extends Activity {
	SQLiteDatabase db;
	ArrayList<String> al=new ArrayList<String>();

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_messages);
		Bundle b=getIntent().getExtras();
		String aa=b.getString("aa");
				final ListView lv=(ListView)findViewById(R.id.listView1);
				db = openOrCreateDatabase("enc",
						MODE_PRIVATE, null);
		db.execSQL("create table if not exists msg(number varchar,msg1 varchar)");
		Cursor c=db.rawQuery("Select * from msg where number='"+aa+"'",null);
		if(c.getCount()==0){
			Toast.makeText(getApplicationContext(), "Sorry No Messages",100).show();
		}else{
		if(c!=null){
			c.moveToFirst();
			do{
				String no=c.getString(c.getColumnIndex("msg1"));
				al.add(no);
				lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,al));
				
			}while(c.moveToNext());
		}}
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String aa=lv.getItemAtPosition(arg2).toString();
				Intent it=new Intent(MessagesActivity.this,DecryptActivity.class);
				it.putExtra("aa",aa);
				startActivity(it);
			}
		});
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.activity_messages, menu);
			return true;
		}

	}

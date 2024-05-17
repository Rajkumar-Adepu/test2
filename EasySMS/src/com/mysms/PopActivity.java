package com.mysms;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PopActivity  extends Activity{
	private final String SAMPLE_DB_NAME = "MySmsDB";
	private final String SAMPLE_TABLE_NAME = "MySmsTable";

	 SQLiteDatabase sampleDB = null;
	 int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popxml);
		Bundle b=getIntent().getExtras();
		String message=b.getString("message");
		final String smsId=b.getString("id");
		id=Integer.parseInt(smsId);
		TextView b1=(TextView)findViewById(R.id.T1);
		Button btn=(Button)findViewById(R.id.delete);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), smsId, 20).show();
				delete();
			}
			
		});
		b1.setText(message);
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=getIntent();
			setResult(RESULT_OK, i);
			finish();
			}
			
		});
		
	}

	protected void delete() {
		// TODO Auto-generated method stub
		 sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
	  	 sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME+" where smsId='"+id+"'");
	  	Toast.makeText(getBaseContext(), "Message Deleted Succesfuly", 20).show();
		Intent it=new Intent(this,displayHistory.class);
		startActivity(it);
		finish();
	}

	
}

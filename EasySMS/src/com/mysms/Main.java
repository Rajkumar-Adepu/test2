package com.mysms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button start=(Button)findViewById(R.id.start);
        Button history=(Button)findViewById(R.id.history);
        Button group=(Button)findViewById(R.id.group);
        
        Button inbox=(Button)findViewById(R.id.button1);
        Button sendbulksms=(Button)findViewById(R.id.sendbulksms);
        sendbulksms.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendBulkSms();
			}
        	
        });
        group.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createGroup();
			}
        	
        });
        history.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				histroy();
			}
        	
        });
        start.setOnClickListener(new OnClickListener(){

			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send();
			}
        	
        });
       inbox.setOnClickListener(new OnClickListener(){

			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(Main.this,InboxActivity.class);
				startActivity(it);
			}
        	
        });
    }
    

	protected void sendBulkSms() {
		// TODO Auto-generated method stub
		Intent it=new Intent(this,SendBulkSms.class);
		startActivity(it);
	}


	protected void createGroup() {
		// TODO Auto-generated method stub
		Intent it=new Intent(this,CreateGroup.class);
		startActivity(it);
	}

	protected void histroy() {
		// TODO Auto-generated method stub
		Intent it=new Intent(this,displayHistory.class);
		startActivity(it);
	}

	protected void send() {
		// TODO Auto-generated method stub
		Intent it=new Intent(this,ContactsAutoComplete.class);
		startActivity(it);
	}
}
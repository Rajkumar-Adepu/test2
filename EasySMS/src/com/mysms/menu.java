package com.mysms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class menu extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen3);
	}
public boolean onCreateOptionMenu(Menu menu){
		
		super.onCreateOptionsMenu(menu);
		MenuInflater mi=getMenuInflater();
		mi.inflate(R.menu.main_menu, menu);
		
		return true;
		
	}
	public boolean onOptionItemSelected(MenuItem item){
		
		switch (item.getItemId()) {
		case R.id.sweetmenu:
			Toast.makeText(this, "fi", 20).show();
			Intent it=new Intent(this,CreateNewGroup.class);
			startActivityForResult(it, 0);
		return true;
		
		}
		return false;
		
		
	}
	
	
}

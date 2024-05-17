package com.mysms;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.telephony.SmsMessage;
import android.widget.Toast;

@SuppressLint("NewApi") public class SmsReceiver extends BroadcastReceiver {
	String seedValue = "This Is MySecure";
	public static final String MyPREFERENCES = "MyPrefs";
	public static final String Name = "nameKey";
	public static final String Password = "pwdkey";
	public static final String Phone = "phonekey";
	SQLiteDatabase db;
	SharedPreferences sharedpreferences;
	public static final String SMS_BUNDLE = "pdus";

	public void onReceive(Context context, Intent intent) {
		Bundle intentExtras = intent.getExtras();
		if (intentExtras != null) {
			Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
			for (int i = 0; i < sms.length; ++i) {
				SmsMessage smsMessage = SmsMessage
						.createFromPdu((byte[]) sms[i]);
				String smsBody = smsMessage.getMessageBody().toString();
				String address = smsMessage.getOriginatingAddress();
				sharedpreferences = context.getSharedPreferences(MyPREFERENCES,
						Context.MODE_PRIVATE);
				String name = sharedpreferences.getString(Name, "");

				String pass = sharedpreferences.getString(Password, "");
				try {
					boolean aa = smsBody.startsWith("encrp");
					if (aa == true) {
						String ss[] = smsBody.split("encrp");
						db = context.openOrCreateDatabase("enc",
								context.MODE_PRIVATE, null);
						db.execSQL("create table if not exists msg(number varchar,msg1 varchar)");
						db.execSQL("insert into msg values('" + address + "','"
								+ ss[1] + "')");

						// String normalTextDec = SimpleProtector.decrypt(pass,
						// smsBody);
						// Toast.makeText(context, normalTextDec,
						// Toast.LENGTH_SHORT).show();
					} else {

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// this will update the UI with message
		}
	}
}
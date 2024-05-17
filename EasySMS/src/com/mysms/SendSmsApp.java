package com.mysms;

import java.net.URLEncoder;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import android.annotation.SuppressLint;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("NewApi") public class SendSmsApp {

	String number, message,salt;
	String numbertosend = "";
	Vector<String> node = new Vector<String>();
	Vector<String> node2 = new Vector<String>();
	Vector<String> names = new Vector<String>();
	Vector<String> node3 = new Vector<String>();
	Vector<String> finalnumbaer = new Vector<String>();

	public boolean sendSms(String message, String no, String saltStr) {
		// TODO Auto-generated method stub
		number = no;
		this.message = message;
		System.out.print(message + no);
		Log.d("message SendSms", message);
this.salt=saltStr;
		boolean flag = shivaSpecial(number);
		return flag;
	}

	private boolean shivaSpecial(String number2) {
		// TODO Auto-generated method stub
		String separator = ",";
		int res = number.indexOf(separator);
		while (res >= 0) {
			int index = number.indexOf(",");

			String n1 = number.substring(0, index);
			node.add(number.substring(0, index).toString());
			number = number.substring(index + 1 + separator.length());
    
			Log.d("n1", n1);
			res = number.indexOf(separator);
		}
		node.add(number);
		for (int n = 0; n < node.size(); n++) {
			// numbertosend+=isShivaSpecial(node.elementAt(n))+",";
			Log.d("shiva special", node.elementAt(n));
			node3.add(isShivaSpecial(node.elementAt(n)));
		}
		for (int g = 0; g < node3.size(); g++) {
			if (node3.elementAt(g).length() == 12)
				numbertosend += (node3.elementAt(g)).substring(2) + ",";
			else
				numbertosend += node3.elementAt(g) + ",";
			// Log.d("cccc",Integer.toString(node3.elementAt(g).length()));
		}
String aa[]=number.split(",");
for(int i=0;i<aa.length;i++){
		Log.d("finalgrtgyrrtertkjhkjhkhnumber", aa[i]);
		Log.d("finalmessage", message);
}
		boolean flag = sendSms2(message, numbertosend);
		System.out.println("sivaaaaaaaaaa"+numbertosend);

		
		return flag;
	}

	private String isShivaSpecial(String elementAt) {
		String iChars = "1234567890";
		String ssss = "";
		for (int i = 0; i < elementAt.length(); i++) {
			if (iChars.indexOf(elementAt.charAt(i)) != -1) {

				ssss += elementAt.charAt(i);

			}
		}
		return ssss;
	}

	@SuppressWarnings("finally")
	private boolean sendSms2(String message, String numbertosend) {
		
		System.out.println("$^%$%^$%$%^"+message);
		  try {
		        
	        	// Get the default instance of the SmsManager
	        	            SmsManager smsManager = SmsManager.getDefault();
	        	            smsManager.sendTextMessage(numbertosend,
	        	                    null, 
	        	                    message,
	        	                    null,
	        	                    null);
	        	           
	        	        } catch (Exception ex) {
	        	           
	        	            ex.printStackTrace();
	        
	        	        }
		
		  try {
		        
	        	// Get the default instance of the SmsManager
	        	            SmsManager smsManager = SmsManager.getDefault();
	        	            smsManager.sendTextMessage(numbertosend,
	        	                    null, 
	        	                    salt,
	        	                    null,
	        	                    null);
	        	           
	        	        } catch (Exception ex) {
	        	           
	        	            ex.printStackTrace();
	        
	        	        }
		
		return true;

	}}

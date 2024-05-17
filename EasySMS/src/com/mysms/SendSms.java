package com.mysms;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SendSms extends Activity {
	private static final String SOAP_ACTION ="http://bulk.rocktwosms.com/spanelv2/api.php";
    private static final String METHOD_NAME ="api.php";
    private static final String NAMESPACE ="http://bulk.rocktwosms.com/spanelv2/";
	    private static String URL="";
	    private Object resultsRequestSOAP = null;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.screen3);
		
		Bundle bundle=getIntent().getExtras();
		
		String name=bundle.getString("name");
		String number=bundle.getString("number");
		String message=bundle.getString("message");
		
		Toast.makeText(getBaseContext(), name+number+message, 10).show();
		URL="http://bulk.rocktwosms.com/spanelv2/api.php?username=coignedutran&password=123456&to="+number+"&from=coigne&message="+message+"";

		sendSms(name,number,message);
	}

	private void sendSms(String name, String number, String message) {
		// TODO Auto-generated method stub
		 SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		 Log.d("soapobject",request.toString());
//Toast.makeText(this, request.toString(), 10).show();
System.out.print(request);
	        // SoapObject
	       request.addProperty("username","coignedutran");
	       request.addProperty("password","123456");
	       request.addProperty("to",number);
	       request.addProperty("from","coigne");
	      
	       request.addProperty("message",message);
	       
	       Log.d("soapobject2",request.toString());
	       // request.addProperty("ZipcodeName", "Nework");
	        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	        envelope.setOutputSoapObject(request);
	        envelope.dotNet=true;
	        Log.d(null,"Status : set HttpTransport");


	        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	        try {
	        	Toast.makeText(getBaseContext(), "test1",10).show();
	            Log.d(null,"Status : b4 Call");
	            androidHttpTransport.call(SOAP_ACTION, envelope);
	            Toast.makeText(getBaseContext(), "test2",10).show();
	            Log.d(null,"Status : Call done");
	            SoapObject response = (SoapObject)envelope.getResponse();
	            Toast.makeText(getBaseContext(), response.getProperty(0).toString(),10).show();
	            Log.d(null,"Status : Response received");
	           
	           
	        } catch (Exception aE) {
	            //aE.printStackTrace();
	            Log.d(null,"Exception: " + aE);
	            System.out.print(aE);
	        }
	}

}

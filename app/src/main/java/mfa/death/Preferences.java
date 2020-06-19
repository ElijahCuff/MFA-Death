package mfa.death;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class Preferences extends PreferenceActivity implements
SharedPreferences.OnSharedPreferenceChangeListener {


	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
		// load new changes
		settings = Settings.get(this);

		// notify user with response
		//Toast.makeText(getBaseContext(), "Configuration Changed", Toast.LENGTH_SHORT).show();
		
		// Check if the activity is hidden now
		if (key.equals("useHide")) {
			if (settings.hideApp()) {
				updateAlias("home-show", false);
				updateAlias("home-hide", true);
				setTitle(getString(R.string.app_name_hidden));
			  } else {
				updateAlias("home-show", true);
				updateAlias("home-hide", false);
				setTitle("MFA Death");
			  }
		  }

	  }
	Settings settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// load settings 
		settings = Settings.get(getBaseContext().getApplicationContext());

		// register the preference listener
		SharedPreferences preferences = settings.pref;
        preferences.registerOnSharedPreferenceChangeListener(Preferences.this);
		addPreferencesFromResource(R.xml.settings);

		// do some changes for incompatible firmwares
		if (Build.VERSION.SDK_INT < 11) {
			// disable by key
			// getPreferenceScreen().findPreference("pref_key").setEnabled(false);
		  }

		// check if the Title has changed to the hidden title
		if (settings.hideApp()) {
			setTitle(getString(R.string.app_name_hidden));
		  } else {
			  setTitle("MFA Death");
		  }
	  }

	// custom function to reload activity-alias
	public void updateAlias(String activityRefference, boolean enabledState) {
		int state = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
		if (enabledState) {
			state = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
		  }
		getPackageManager().setComponentEnabledSetting(
		  new ComponentName(getPackageName(), getPackageName() + "." + activityRefference), 
		  state, PackageManager.DONT_KILL_APP);
	  }

	// stop user from exiting on accident
	private static long back_pressed;
	@Override
	public void onBackPressed() {
        if (back_pressed + 400 > System.currentTimeMillis()) super.onBackPressed();
        else Toast.makeText(getBaseContext(), "Double Tap To Close !!!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
	  }

  }

package mfa.death;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class home extends Activity {
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
			  }
		  } else {
			startActivity(new Intent(this, Preferences.class));
		  }
	  }

  }

package com.mbad.ninerhunt;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class MainActivity extends Activity {

	LocationManager locManager;

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_main);
	// locManager = (LocationManager)
	// getSystemService(Context.LOCATION_SERVICE);

	// ImageButton btnGetHint = (ImageButton)
	// this.findViewById(R.id.ibLogin);
	// btnGetHint.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// Intent intObj = new Intent(getBaseContext(), HomeActivity.class);
	// startActivity(intObj);
	// }
	// });

	// }

	private Dialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// Initialize the Parse Data Store
		Log.w("test", getString(R.string.parseAppId));
		Log.w("test", getString(R.string.parseClientKey));
		Parse.initialize(this, getString(R.string.parseAppId),
				getString(R.string.parseClientKey));

		// Check if there is a currently logged in user
		// and it's linked to a Facebook account.
		ParseUser currentUser = ParseUser.getCurrentUser();
		if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
			// Go to the user info activity
			GoToHome();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
	}

	public void onLoginClick(View v) {
		progressDialog = ProgressDialog.show(this, "", "Logging in...", true);

		List<String> permissions = Arrays.asList("public_profile", "email");

		ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException err) {
				progressDialog.dismiss();
				if (user == null) {
					// Log.d(IntegratingFacebookTutorialApplication.TAG,
					// "Uh oh. The user cancelled the Facebook login.");
				} else if (user.isNew()) {
					// Log.d(IntegratingFacebookTutorialApplication.TAG,
					// "User signed up and logged in through Facebook!");
					GoToHome();
				} else {
					// Log.d(IntegratingFacebookTutorialApplication.TAG,
					// "User logged in through Facebook!");
					GoToHome();
				}
			}
		});
	}

	private void GoToHome() {
		Intent intent = new Intent(this, HomeActivity.class);
		startActivity(intent);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getString(R.string.GPSNotEnabled))
					.setMessage(getString(R.string.EnableGPS))
					.setCancelable(true)
					.setPositiveButton(getString(R.string.Yes),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent i = new Intent(
											Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									startActivity(i);
								}
							})
					.setNegativeButton(getString(R.string.No),
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									finish();

								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		}

	}
}

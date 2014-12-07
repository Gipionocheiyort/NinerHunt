package com.mbad.ninerhunt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	LocationManager locManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		ImageButton btnGetHint = (ImageButton) this.findViewById(R.id.ibLogin);
		btnGetHint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intObj = new Intent(getBaseContext(), HomeActivity.class);
				startActivity(intObj);
			}
		});

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

package com.mbad.ninerhunt;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActiveQuestHudFragment extends Fragment implements
		LocationListener {
	View currentView;
	LocationManager locManager;
	String CurrentGoalHint;

	User currentUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// TODO: Retrieve Username
		currentUser = new User("Phil");

		View view = inflater.inflate(R.layout.activequesthud_fragment,
				container, false);

		Button btnGetHint = (Button) view.findViewById(R.id.btnGetHint);
		btnGetHint.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				btnGetHint_click(v);
			}
		});

		locManager = (LocationManager) getActivity().getSystemService(
				getActivity().LOCATION_SERVICE);
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);

		currentView = view;

		return view;
	}

	public void btnGetHint_click(View view) {
		// TODO: Retrieve Current Goal Hint
		String goalHint = "";
		Toast.makeText(getActivity(), goalHint, Toast.LENGTH_LONG).show();
	}

	// Originally this was going to be done using the Google Distance Matrix
	// API,
	// but after researching the problem I found that it could be done far more
	// efficiently with the mathematical haversine formula since both lat and
	// long were already known
	// https://en.wikipedia.org/wiki/Haversine_formula
	public double distanceFromGoal(double lat1, double lon1, double lat2,
			double lon2) {
		double lat1Radians = lat1 * Math.PI / 180;
		double lat2Radians = lat2 * Math.PI / 180;
		double latDiffRadians = (lat2 - lat1) * Math.PI / 180;
		double lonDiffRadians = (lon2 - lon1) * Math.PI / 180;
		double a = Math.sin(latDiffRadians / 2) * Math.sin(latDiffRadians / 2)
				+ Math.cos(lat1Radians) * Math.cos(lat2Radians)
				* Math.sin(lonDiffRadians / 2) * Math.sin(lonDiffRadians / 2);
		double radians = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double Feet = radians * 3437.74677 * 1.1508 * 5.2800102998e+3;
		return Feet;
	}

	@Override
	public void onLocationChanged(Location location) {
		double currentLat = location.getLatitude();
		double currentLon = location.getLongitude();

		((ILocationReceiver) getActivity()).setLocation(currentLat, currentLon);

		TextView tvDistanceRemaining = (TextView) currentView
				.findViewById(R.id.tvDistanceRemaining);

		double distanceFromGoal = distanceFromGoal(currentUser.getCurrentGoal()
				.getLat(), currentUser.getCurrentGoal().getLon(), currentLat,
				currentLon);

		tvDistanceRemaining.setText("Distance From Goal: " + distanceFromGoal
				+ " ft");

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}

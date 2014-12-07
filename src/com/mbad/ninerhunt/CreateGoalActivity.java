package com.mbad.ninerhunt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CreateGoalActivity extends Activity implements ILocationReceiver{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_goal);
	}

	@Override
	public void setLocation(Double lat, Double lon) {
		// TODO Auto-generated method stub
		
	}
}

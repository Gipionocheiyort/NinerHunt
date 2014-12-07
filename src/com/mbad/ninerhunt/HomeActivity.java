package com.mbad.ninerhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends Activity implements ILocationReceiver {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		Button btnCreateGoal = (Button) this.findViewById(R.id.btnCreateGoal);
		btnCreateGoal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intObj = new Intent(getBaseContext(),
						CreateGoalActivity.class);
				startActivity(intObj);
			}
		});

		Button btnCreateNewHunt = (Button) this
				.findViewById(R.id.btnCreateNewHunt);
		btnCreateNewHunt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intObj = new Intent(getBaseContext(),
						NewHuntActivity.class);
				startActivity(intObj);
			}
		});

		Button btnLeaderboard = (Button) this.findViewById(R.id.btnLeaderboard);
		btnLeaderboard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intObj = new Intent(getBaseContext(),
						LeaderboardActivity.class);
				startActivity(intObj);
			}
		});

		Button btnViewHuntLog = (Button) this.findViewById(R.id.btnViewHuntLog);
		btnViewHuntLog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intObj = new Intent(getBaseContext(),
						HuntLogActivity.class);
				startActivity(intObj);
			}
		});

	}

	@Override
	public void setLocation(Double lat, Double lon) {
		// TODO Auto-generated method stub
		
	}
}

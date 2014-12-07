package com.mbad.ninerhunt;

import java.util.ArrayList;

public class User {
	int userId;
	String username;
	int activeHuntId;
	double points;

	ArrayList<HuntLogItem> huntLog;

	public User(String Username) {
		setUsername(Username);
		retrieveUserData();
	}

	public void Save() {
		// Todo: Save Item
	}

	private void retrieveUserData() {
		// TODO: Retrieve These From SQL
		setUserId(1);
		setActiveHuntId(1);
		setPoints(10);

		populateHuntLog();

	}

	private void populateHuntLog() {
		// Todo: Retrieve From SQL
		huntLog = new ArrayList<HuntLogItem>();
		huntLog.add(new HuntLogItem(1, 1, 1));

	}

	public Hunt getActiveHunt() {
		return new Hunt(activeHuntId);
	}

	private int getActiveGoalId() {
		// TODO: Search Arraylist For Active Goal
		return 1;
	}

	public Goal getCurrentGoal() {
		return new Goal(getActiveGoalId());
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getActiveHuntId() {
		return activeHuntId;
	}

	public void setActiveHuntId(int activeHuntId) {
		this.activeHuntId = activeHuntId;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
}

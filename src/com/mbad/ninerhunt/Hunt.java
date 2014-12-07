package com.mbad.ninerhunt;

import java.util.ArrayList;

public class Hunt {
	int huntId;
	String huntName;

	ArrayList<Goal> huntGoals;

	public Hunt(int huntId) {
		setHuntId(huntId);
		retrieveHuntData();
	}

	public ArrayList<Goal> getGoals() {
		if (huntGoals != null) {
			return huntGoals;
		} else {
			return new ArrayList<Goal>();
		}
	}

	public Hunt(String huntName, int pointValue, String startText,
			Double startLat, Double StartLon) {
		huntGoals = new ArrayList<Goal>();
		setHuntName(huntName);

		saveHunt();

		Goal startGoal = new Goal(getHuntId(), startText, startLat, StartLon);

		huntGoals.add(startGoal);

	}

	private void saveHunt() {
		// TODO: Save to databaseStartLon
		// Todo: Set HuntID
	}

	private void populateHuntGoals() {
		// Todo: Retrieve From SQL
		huntGoals = new ArrayList<Goal>();
		huntGoals.add(new Goal(1));

	}

	private void retrieveHuntData() {
		// TODO: Retrieve These From SQL
		setHuntName("TestHunt");
		populateHuntGoals();
	}

	public int getHuntId() {
		return huntId;
	}

	public void setHuntId(int huntId) {
		this.huntId = huntId;
	}

	public String getHuntName() {
		return huntName;
	}

	public void setHuntName(String huntName) {
		this.huntName = huntName;
	}

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	int createdByUserId;
	int pointValue;
	boolean Active;
}

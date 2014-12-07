package com.mbad.ninerhunt;

public class Goal {
	int goalId;
	String hint;
	double lat;
	double lon;
	int huntId;

	public Goal(int goalId) {
		setGoalId(goalId);
		retrieveGoalData();
	}

	public Goal(int huntId, String hint, Double lat, Double lon) {
		setHuntId(huntId);
		setHint(hint);
		setLat(lat);
		setLon(lon);
		saveGoal();
	}

	public String getTagText() {
		return "HuntId:" + getHuntId() + "; - GoalId:" + getGoalId() + ";";
	}

	private void saveGoal() {
		// TODO: Save Goal To Database
	}

	private void retrieveGoalData() {
		// Todo: Retrieve From SQL
		setHint("DemoHint");
		setLat(35.2036325);
		setLon(-80.8401144278239);
		setHuntId(1);

	}

	public int getGoalId() {
		return goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getHuntId() {
		return huntId;
	}

	public void setHuntId(int huntId) {
		this.huntId = huntId;
	}

}

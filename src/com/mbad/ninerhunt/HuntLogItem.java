package com.mbad.ninerhunt;

public class HuntLogItem {
	int HuntLogItemId;
	int UserId;
	int HuntId;
	int GoalId;
	boolean completed;

	public HuntLogItem(int itemId, int userId, int huntId) {
		setHuntId(huntId);
		setHuntLogItemId(itemId);
		setUserId(userId);
	}

	public int getHuntLogItemId() {
		return HuntLogItemId;
	}

	public void Save() {
		// Todo: Save Item
	}

	public void setHuntLogItemId(int huntLogItemId) {
		HuntLogItemId = huntLogItemId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getHuntId() {
		return HuntId;
	}

	public void setHuntId(int huntId) {
		HuntId = huntId;
	}

	public int getCurrentGoalId() {
		return GoalId;
	}

	public void setCurrentGoalId(int currentGoalId) {
		this.GoalId = currentGoalId;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}

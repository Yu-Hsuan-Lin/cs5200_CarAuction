package carAuction.model;

import java.util.Date;


public class UserActivity {
	protected String activityID;
	protected Users user;
	protected ActivityType activityType;
	protected Date timeStamp;
	
	
	public enum ActivityType{
		login,logout
	}


	public UserActivity(String activityID, Users user, ActivityType activityType, Date timeStamp) {
		this.activityID = activityID;
		this.user = user;
		this.activityType = activityType;
		this.timeStamp = timeStamp;
	}


	public String getActivityID() {
		return activityID;
	}


	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}


	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public ActivityType getActivityType() {
		return activityType;
	}


	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}


	public Date getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}

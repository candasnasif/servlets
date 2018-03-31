package com.hacettepe.hello;

public class Event {
	private int eventID;

	private String title;
	private String location;
	private String description;
	private String date;
	private String startTime;
	private String endTime;

	public Event(int eventID, String title, String location, String description,String date, String startTime, String endTime) {
		super();
		this.eventID = eventID;
		this.title = title;
		this.location = location;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String toString() {
		String result = "";
		result ="EventID : " + this.eventID+"\n"+
				"Title : " + this.title+"\n"+
				"Location : " + this.location +"\n"+
				"Description : " + this.description +"\n"+
				"Date : " + this.date +"\n"+
				"Start Time : " + this.startTime +"\n"+
				"End Time : " +this.endTime + "\n"+
				"----------------------------------------------------------\n";
		return result;
	}
	public String toStringShare() {
		String result = "";
		result ="Title : " + this.title+"\n"+
				"Location : " + this.location +"\n"+
				"Description : " + this.description +"\n"+
				"Date : " + this.date +"\n"+
				"Start Time : " + this.startTime +"\n"+
				"End Time : " +this.endTime + "\n"+
				"----------------------------------------------------------\n";
		return result;
	}

}

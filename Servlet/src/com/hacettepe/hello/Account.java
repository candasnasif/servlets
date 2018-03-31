package com.hacettepe.hello;

import java.util.ArrayList;
import java.util.HashMap;

public class Account {
	public String username;
	private String password;
	public HashMap<Integer, Event> myEvents = new HashMap<Integer, Event>();
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void addEvent(Event newEvent) {
		myEvents.put(newEvent.getEventID(),newEvent);
	}
	public void removeEvent(int eventID) {
		myEvents.remove(eventID);
	}
	public void updateEvent(int eventID, Event newEvent) {
		for (int key : this.myEvents.keySet()) {
			if(key == eventID) {
				this.myEvents.put(key, newEvent);
			}
		}
	}
	public String toString() {
		String result = "";
		for (Integer key : this.myEvents.keySet()) {
			result += this.myEvents.get(key).toString();
		}
		
		return result;
	}

	
}

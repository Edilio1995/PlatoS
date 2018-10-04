package com.example.edilio.guitiming;

public class AndroidWidget {
	
	private String type;
	private String id;
	private int priority;
	private String action;
	private float time;
	
	
	public AndroidWidget(String type, String id, int priority, float time) {
		this.type = type;
		this.id = id;
		this.priority = priority;
		action = "";
		this.time = time;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public float getTime() {
		return time;
	}


	public void setTime(float time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "AndroidObj [type=" + type + ", id=" + id + ", priority=" + priority + ", action=" + action + ", time="
				+ time + "]";
	}


	
	
	
	

}

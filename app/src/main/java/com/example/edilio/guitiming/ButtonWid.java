package com.example.edilio.guitiming;

public class ButtonWid extends AndroidWidget {

	private String click;
	
	public ButtonWid(String type, String id, int priority, float time, String click) {
		super(type, id, priority,time );
		this.click = click;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	@Override
	public String toString() {
		return "Button [click=" + click + ", getType()=" + getType() + ", getId()=" + getId() + ", getPriority()="
				+ getPriority() + ", getAction()=" + getAction() + ", getTime()=" + getTime() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	
	
	

}

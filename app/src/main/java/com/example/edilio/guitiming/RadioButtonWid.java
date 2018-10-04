package com.example.edilio.guitiming;

public class RadioButtonWid extends AndroidWidget {
	
	private Boolean active;

	public RadioButtonWid(String type, String id, int priority, float time) {
		super(type, id, priority, time);
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "RadioButton [active=" + active + ", getType()=" + getType() + ", getId()=" + getId()
				+ ", getPriority()=" + getPriority() + ", getAction()=" + getAction() + ", getTime()=" + getTime()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

	
}

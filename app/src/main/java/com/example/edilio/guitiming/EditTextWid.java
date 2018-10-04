package com.example.edilio.guitiming;

public class EditTextWid extends AndroidWidget{
	
	private String text;

	public EditTextWid(String type, String id, int priority, float time, String text) {
		super(type, id, priority, time);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "EditText [text=" + text + ", getType()=" + getType() + ", getId()=" + getId() + ", getPriority()="
				+ getPriority() + ", getAction()=" + getAction() + ", getTime()=" + getTime() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
}

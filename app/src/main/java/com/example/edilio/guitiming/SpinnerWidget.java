package com.example.edilio.guitiming;

import java.util.ArrayList;

public class SpinnerWidget extends AndroidWidget {

    private int index;
    private ArrayList<String> list;

    public SpinnerWidget(String type, String id, int priority, float time, ArrayList<String> list) {
        super(type, id, priority, time);
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListView [index=" + index + ", getType()=" + getType() + ", getId()=" + getId() + ", getPriority()="
                + getPriority() + ", getAction()=" + getAction() + ", getTime()=" + getTime() + ", toString()="
                + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
    }

    public ArrayList<String> getList() {
        return list;
    }
}

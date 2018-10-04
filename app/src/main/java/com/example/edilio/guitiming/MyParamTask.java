package com.example.edilio.guitiming;

/**
 * Created by Edilio on 01/12/2017.
 */

public class MyParamTask {

    private int type;
    private String name;
    private String path;

    public MyParamTask(int type, String name,String path){
        this.type = type;
        this.name = name;
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}

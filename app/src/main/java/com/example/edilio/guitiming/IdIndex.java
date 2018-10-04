package com.example.edilio.guitiming;

import java.io.Serializable;

/**
 * Created by Edilio on 13/10/2017.
 */

public class IdIndex implements Serializable{
    private int id;
    private String idString;
    private String type;

    public IdIndex(int id, String idString, String type){
        this.type = type;
        this.id = id;
        this.idString = idString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }
}

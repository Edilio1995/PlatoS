package com.example.edilio.guitiming;

/**
 * Created by Edilio on 29/10/2017.
 */

public class UserData {

    private String name;
    private String surname;
    private String birthday;
    private String occupation;
    private String question;
    private Boolean vibration;

    public UserData(String name, String surname, String birthday, String occupation, Boolean question, Boolean vibration){
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.occupation = occupation;
        if(question){
            this.question = "yes";
        }
        else
            this.question = "no";
        if(vibration){
            this.vibration = true;
        }
        else
            this.vibration = false;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", occupation='" + occupation + '\'' +
                ", question='" + question + '\'' +
                ", vibration='" + vibration + '\'' +
                '}';
    }

    public Boolean getVibration(){
        return vibration;
    }

}

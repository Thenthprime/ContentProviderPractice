package edu.psu.swen888.contentproviderpractice;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String number;

    public Contact(String name, String number){
        this.name = name;
        this.number = number;
    }

    //getter methods for the event details

    public String getContact(){
        return name;
    }
    public String getNumber(){
        return number;
    }
}

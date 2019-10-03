package com.example.sqlite;

import android.content.SharedPreferences;

public class Contact {
    int id;
    String name;
    String number;

    public Contact()
    {

    }

    public Contact(String name, String number)
    {
        this.name=name;
        this.number=number;
    }

    public Contact(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }


}
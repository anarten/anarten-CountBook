package com.example.anarten_countbook;

import java.util.Date;

/**
 * Created by Adam on 9/23/2017.
 */

public class Counter {
    private String name;
    private Date date;
    private int currentValue;
    private int initialValue;
    private String comment;

    public Counter (String name, int value, String comment) {
        this.name = name;
        this.date = new Date();
        this.setInitialValue(value);
        this.setCurrentValue(value);
        this.comment = comment;
    }

    public void setCurrentValue (int value) {
        this.currentValue = value;
    }

    public void setInitialValue (int value) {
        this.initialValue = value;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString() {
        return date.toString() + " | " + name + " | Current Value: " + Integer.toString(currentValue) + " | Initial Value: " + Integer.toString(initialValue) + " | " + comment;
    }
}

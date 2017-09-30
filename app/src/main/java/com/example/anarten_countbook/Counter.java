/*
 *  Class Name: Counter
 *
 *  Version: 1.0
 *
 *  Date: September 30, 2017
 *
 *  Copyright (c) 2017 Adam Narten, CMPUT301, University of Alberta - All Rights Reserverd. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at the University of Alberta
 */

package com.example.anarten_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a counter that can be used to count things
 *
 * @author anarten
 * @version 1.0
 * @since 1.0
 */
public class Counter {
    private String name;
    private Date date;
    private int currentValue = 0;
    private int initialValue;
    private String comment;

    /**
     * Constructor for the counter class
     * @param name the name of the counter
     * @param value the initial and current value of the counter
     * @param comment the comment about the counter
     */
    public Counter (String name, int value, String comment) {
        this.setName(name);
        this.date = new Date();
        this.setInitialValue(value);
        this.setCurrentValue(value);
        this.setComment(comment);
    }

    /**
     * Sets the current value of the counter.
     * Also updates the date if a valid value is passed.
     *
     * @param value int for the new current value for the counter
     */
    public void setCurrentValue (int value) {
        if (value >= 0 && value != currentValue) {
            this.currentValue = value;
            this.date = new Date();
        }
    }

    /**
     * Sets the inital value of the counter
     * @param value int for the new initial value for the counter
     */
    public void setInitialValue (int value) {
        this.initialValue = value;
    }

    /**
     * Fetches the current value of the counter
     * @return int representing currentValue
     */
    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * Fetches the initial value of the counter
     * @return int representing initialValue
     */
    public int getInitialValue() {
        return initialValue;
    }

    /**
     * Sets the name of the counter
     * @param name String representing the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Fetches the name of the counter
     * @return String respresenting the name of the counter
     */
    public String getName() {
        return name;
    }

    /**
     * Fetches the updated date of the counter
     * @return String representing the date in format "yyyy-MM-dd"
     */
    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * Fetches the comment of the counter
     * @return String representing the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment for the counter
     * @param comment String representing the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Overrides the toString function to return details about a counter
     * @return String with name, current value, and date in format "yyyy-MM-dd"
     */
    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return name + " | Current Value: " + Integer.toString(currentValue) + "\nUpdated: " + df.format(date);
    }
}

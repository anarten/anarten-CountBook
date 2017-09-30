/*
 *  Class Name: ViewCounterActivity
 *
 *  Version: 1.0
 *
 *  Date: September 30, 2017
 *
 *  Copyright (c) 2017 Adam Narten, CMPUT301, University of Alberta - All Rights Reserverd. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at the University of Alberta
 *
 */

package com.example.anarten_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Represents the activity to view counter details for the anarten-CountBook app
 *
 * @author anarten
 * @version 1.0
 * @see EditCounterActivity
 * @see MainActivity
 * @see Counter
 * @since 1.0
 */
public class ViewCounterActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private Counter c;
    private int position;

    /**
     * OnCreate function that runs the first time the activity is loaded
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_counter);

        loadFromFile();

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        c = counters.get(position);

        TextView textView = (TextView) findViewById(R.id.counterTitle);
        textView.setText(c.getName());
        textView = (TextView) findViewById(R.id.initialValue);
        textView.setText(Integer.toString(c.getInitialValue()));
        textView = (TextView) findViewById(R.id.currentValue);
        textView.setText(Integer.toString(c.getCurrentValue()));

        FloatingActionButton deleteButton = (FloatingActionButton) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                counters.remove(position);
                saveInFile();
                finish();
            }
        });

        Button increment = (Button) findViewById(R.id.incrementButton);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                c.setCurrentValue(c.getCurrentValue()+1);
                counters.set(position,c);
                TextView textView = (TextView) findViewById(R.id.currentValue);
                textView.setText(Integer.toString(c.getCurrentValue()));
                textView = (TextView) findViewById(R.id.dateView);
                textView.setText("Updated: " + c.getDate());
                saveInFile();
            }
        });

        Button decrement = (Button) findViewById(R.id.decrementButton);
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                c.setCurrentValue(c.getCurrentValue()-1);
                counters.set(position,c);
                TextView textView = (TextView) findViewById(R.id.currentValue);
                textView.setText(Integer.toString(c.getCurrentValue()));
                textView = (TextView) findViewById(R.id.dateView);
                textView.setText("Updated: " + c.getDate());
                saveInFile();
            }
        });

        Button reset = (Button) findViewById(R.id.resetButton);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                c.setCurrentValue(c.getInitialValue());
                counters.set(position,c);
                TextView textView = (TextView) findViewById(R.id.currentValue);
                textView.setText(Integer.toString(c.getCurrentValue()));
                textView = (TextView) findViewById(R.id.dateView);
                textView.setText("Updated: " + c.getDate());
                saveInFile();
            }
        });

    }

    /**
     * OnStart function that runs every time the activity is loaded
     */
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();

        c = counters.get(position);

        TextView textView = (TextView) findViewById(R.id.counterTitle);
        textView.setText(c.getName());
        textView = (TextView) findViewById(R.id.initialValue);
        textView.setText(Integer.toString(c.getInitialValue()));
        textView = (TextView) findViewById(R.id.currentValue);
        textView.setText(Integer.toString(c.getCurrentValue()));
        textView = (TextView) findViewById(R.id.dateView);
        textView.setText("Updated: " + c.getDate());
        textView = (TextView) findViewById(R.id.commentView);
        textView.setText(c.getComment());
    }

    /**
     * editCounter function passes intent to EditCounterActivity
     * @param view
     */
    public void editCounter(View view) {
        Intent intent = new Intent(this, EditCounterActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    /**
     * Loads the necessary files from the device
     * Taken from lonelytwitter lab exercises
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counters = gson.fromJson(in,listType);
            //https://github.com/google/gson/blob/master/UserGuide.md#TOC-Collections-Examples

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counters = new ArrayList<Counter>();
        }

    }

    /**
     * Saves the necessary files to the device
     * Taken from lonelytwitter lab exercises
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters,writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}

/*
 *  Class Name: MainActivity
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
 * Represents the initial activity for the anarten-CountBook app
 *
 * @author anarten
 * @version 1.0
 * @see CreateCounterActivity
 * @see ViewCounterActivity
 * @see Counter
 * @since 1.0
 */
public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    private ListView countersList;

    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;

    /**
     * OnCreate function that runs the first time the activity is loaded
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countersList = (ListView) findViewById(R.id.counterList);
        countersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Functionality for clicking items in ListView
             * Created with reference to https://stackoverflow.com/questions/21295328/android-listview-with-onclick-items
             * @param parent
             * @param view
             * @param position The clicked item's position in the listview
             * @param id
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewCounterActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
                Counter item = (Counter) parent.getItemAtPosition(position);
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
        adapter = new ArrayAdapter<Counter>(this,android.R.layout.simple_list_item_1, counters);
        countersList.setAdapter(adapter);

        TextView title = (TextView) findViewById(R.id.counterListTitle);
        title.setText("Counter List - " + counters.size() + " Counters");
    }

    /**
     * createCounter function passes intent to CreateCounterActivity
     * @param view
     */
    public void createCounter(View view) {
        // do some
        Intent intent = new Intent(this, CreateCounterActivity.class);
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

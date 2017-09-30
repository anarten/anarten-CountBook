/*
 *  Class Name: CreateCounterActivity
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
 * Represents the activity to create counters for the anarten-CountBook app
 *
 * @author anarten
 * @version 1.0
 * @see MainActivity
 * @see ViewCounterActivity
 * @see Counter
 * @since 1.0
 */
public class CreateCounterActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    private ArrayList<Counter> counters = new ArrayList<Counter>();

    /**
     * OnCreate function that runs the first time the activity is loaded
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        Intent intent = getIntent();
        loadFromFile();

        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText initialValueText = (EditText) findViewById(R.id.initialValueText);
        final EditText commentText = (EditText) findViewById(R.id.commentText);

        final Button createButton = (Button) findViewById(R.id.create);
        createButton.setEnabled(false);

        /**
         * TextChangedListeners created with reference to:
         * https://stackoverflow.com/questions/22680106/how-to-disable-button-if-edittext-is-empty
         */
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkInputs();
            }

            private void checkInputs() {
                if ( (!nameText.getText().toString().trim().equals("")) && ( !initialValueText.getText().toString().trim().equals("") ) ) {
                    createButton.setEnabled(true);
                } else {
                    createButton.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }
            @Override
            public void afterTextChanged(Editable s) {
                checkInputs();
            }
        });

        /**
         * TextChangedListeners created with reference to:
         * https://stackoverflow.com/questions/22680106/how-to-disable-button-if-edittext-is-empty
         */
        initialValueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkInputs();
            }

            private void checkInputs() {
                if ( (!nameText.getText().toString().trim().equals("")) && ( !initialValueText.getText().toString().trim().equals("") ) ) {
                    createButton.setEnabled(true);
                } else {
                    createButton.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }
            @Override
            public void afterTextChanged(Editable s) {
                checkInputs();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);

                String name = nameText.getText().toString();
                int initialValue = Integer.parseInt(initialValueText.getText().toString());
                String comment = commentText.getText().toString();

                counters.add(new Counter(name, initialValue, comment));
                saveInFile();
                finish();
            }
        });
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

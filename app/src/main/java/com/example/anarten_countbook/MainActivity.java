package com.example.anarten_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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
 * Created by Adam on 9/23/2017.
 */

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    private ListView countersList;

    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countersList = (ListView) findViewById(R.id.counterList);


    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Counter>(this,android.R.layout.simple_list_item_1, counters);
        countersList.setAdapter(adapter);
    }

    public void createCounter(View view) {
        // do some
        Intent intent = new Intent(this, CreateCounterActivity.class);
        startActivity(intent);
    }

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

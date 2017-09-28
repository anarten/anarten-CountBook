package com.example.anarten_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CreateCounterActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    private ArrayList<Counter> counters = new ArrayList<Counter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_counter);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        loadFromFile();
        // Capture the layout's TextView and set the string as its text
        //TextView textView = (TextView) findViewById(R.id.editText);
        //textView.setText(message);

        Button createButton = (Button) findViewById(R.id.create);

        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);

                EditText nameText = (EditText) findViewById(R.id.nameText);
                EditText initialValueText = (EditText) findViewById(R.id.initialValueText);
                EditText commentText = (EditText) findViewById(R.id.commentText);

                if(nameText != null || initialValueText != null) {

                    String name = nameText.getText().toString();
                    int initialValue = Integer.parseInt(initialValueText.getText().toString());
                    String comment = commentText.getText().toString();

                    counters.add(new Counter(name, initialValue, comment));
                    saveInFile();
                    finish();
                }
            }
        });
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

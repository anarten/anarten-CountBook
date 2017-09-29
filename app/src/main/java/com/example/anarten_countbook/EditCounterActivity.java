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

public class EditCounterActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private Counter c;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        loadFromFile();
        c = counters.get(position);

        final EditText nameText = (EditText) findViewById(R.id.editName);
        final EditText initialValueText = (EditText) findViewById(R.id.editIV);
        final EditText currentValueText = (EditText) findViewById(R.id.editCV);
        final EditText commentText = (EditText) findViewById(R.id.editComment);

        nameText.setText(c.getName());
        initialValueText.setText(Integer.toString(c.getInitialValue()));
        currentValueText.setText(Integer.toString(c.getCurrentValue()));
        commentText.setText(c.getComment());


        final Button updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setEnabled(true);


        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkInputs();
            }

            private void checkInputs() {
                if ( (!nameText.getText().toString().trim().equals("")) && ( !initialValueText.getText().toString().trim().equals("") ) && (!currentValueText.getText().toString().trim().equals("")) ) {
                    updateButton.setEnabled(true);
                } else {
                    updateButton.setEnabled(false);
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

        initialValueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkInputs();
            }

            private void checkInputs() {
                if ( (!nameText.getText().toString().trim().equals("")) && ( !initialValueText.getText().toString().trim().equals("") ) && (!currentValueText.getText().toString().trim().equals("")) ) {
                    updateButton.setEnabled(true);
                } else {
                    updateButton.setEnabled(false);
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

        currentValueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                checkInputs();
            }

            private void checkInputs() {
                if ( (!nameText.getText().toString().trim().equals("")) && ( !initialValueText.getText().toString().trim().equals("") ) && (!currentValueText.getText().toString().trim().equals("")) ) {
                    updateButton.setEnabled(true);
                } else {
                    updateButton.setEnabled(false);
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

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);

                String name = nameText.getText().toString();
                int initialValue = Integer.parseInt(initialValueText.getText().toString());
                int currentValue = Integer.parseInt(currentValueText.getText().toString());
                String comment = commentText.getText().toString();

                c.setName(name);
                c.setInitialValue(initialValue);
                c.setCurrentValue(currentValue);
                c.setComment(comment);

                counters.set(position, c);
                saveInFile();
                finish();
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

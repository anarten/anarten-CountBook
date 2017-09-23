package com.example.anarten_countbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Adam on 9/23/2017.
 */

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ListView oldCountersList;

    private ArrayList<Counter> counters = new ArrayList<Counter>();
    private ArrayAdapter<Counter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bodyText = (EditText) findViewById(R.id.body);
        Button addButton = (Button) findViewById(R.id.addButton);
        //oldCountersList = (ListView) findViewById(R.id.oldCountersList);


        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);

            }
        });

    }

}

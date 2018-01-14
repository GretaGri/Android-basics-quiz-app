package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Greta on 2018-01-13.
 */

public class Results extends AppCompatActivity {
    //declare variables
    int points;
    String userName;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        //get extras
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        points = extras.getInt("points");
        userName = extras.getString("name");
        progress = extras.getInt("progress");

        ImageView navigation = (ImageView) findViewById(R.id.navigation);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent myIntent = new Intent(Results.this, VideoActivity.class);
                myIntent.putExtra("points", points);
                myIntent.putExtra("name", userName);
                myIntent.putExtra("progress",progress);
                Results.this.startActivity(myIntent);

            }

        });
    }
}
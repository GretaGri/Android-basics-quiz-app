package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Greta on 2018-01-13.
 */

public class Results extends AppCompatActivity {

    private static final String TAG = "ResultsActivity";

    //declare variables
    int points;
    String userName;
    boolean back_pressed = false;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        // locate views
        final ImageView animeView = (ImageView) findViewById(R.id.arrow);
        final ImageView navigation = (ImageView) findViewById(R.id.navigation);
        final TextView score = (TextView) findViewById(R.id.score);
        final TextView lyrics = (TextView) findViewById(R.id.lyrics);

        //Apply animation to arrow
        Animation pulsingArrow = AnimationUtils.loadAnimation(this, R.anim.pulse);
        animeView.startAnimation(pulsingArrow);

        //get extras
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        points = extras.getInt("points");
        Log.d(TAG, "points" + points);

        userName = extras.getString("name");
        message = userName + getString(R.string.results) + points + getString(R.string.results2);

        if (points == 1) {
            message = userName + getString(R.string.results) + points + getString(R.string.results1);
            lyrics.setText(R.string.lyrics1to2);
        } else if (points == 2) {
            lyrics.setText(R.string.lyrics1to2);
        } else if (points == 3 || points == 4) {
            lyrics.setText(R.string.lyrics3to4);
        } else if (points == 5 || points == 6) {
            lyrics.setText(R.string.lyrics5to6);
        } else if (points == 7 || points == 8) {
            lyrics.setText(R.string.lyrics7to8);
        } else if (points == 9 || points == 10) {
            lyrics.setText(R.string.lyrics9to10);
        }

        score.setText(message);

        //Apply animation to present
        Animation pulsingPresent = AnimationUtils.loadAnimation(this, R.anim.pulse);
        navigation.startAnimation(pulsingPresent);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Results.this, VideoActivity.class);
                myIntent.putExtra("points", points);
                Results.this.startActivity(myIntent);

            }

        });
    }

    @Override
    public void onBackPressed() {
        if (!back_pressed) {
            Toast.makeText(Results.this, "Do you really want to restart quiz? \nPlease, push back one more time.", Toast.LENGTH_SHORT).show();
            back_pressed = true;
        } else {
            Intent homeIntent = new Intent(Results.this, MainActivity.class);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }// your code.
    }
}
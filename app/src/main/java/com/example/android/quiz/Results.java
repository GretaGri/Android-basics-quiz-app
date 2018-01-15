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
    String toast_message1;
    String toast_message2;
    String toast_message3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        //Apply animation to arrow
        ImageView animeView = (ImageView) findViewById(R.id.arrow);
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
        toast_message1 = userName + getString(R.string.toastNoAnswer);
        toast_message2 = userName + getString(R.string.toastCorrectAnswer);
        toast_message3 = userName + getString(R.string.toastWrongAnswer);

        ImageView navigation = (ImageView) findViewById(R.id.navigation);
        Animation pulsingPresent = AnimationUtils.loadAnimation(this, R.anim.pulse);
        navigation.startAnimation(pulsingPresent);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent myIntent = new Intent(Results.this, VideoActivity.class);
                myIntent.putExtra("name", userName);
                Results.this.startActivity(myIntent);

            }

        });
    }
    @Override
    public void onBackPressed() {
        if (!back_pressed) {
            Toast.makeText(Results.this, "Do you really want to restart quiz? \nPlease, push back one more time." , Toast.LENGTH_SHORT).show();
            back_pressed=true;}
        else {Intent homeIntent = new Intent(Results.this,MainActivity.class);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);}// your code.
    }
}
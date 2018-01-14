package com.example.android.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * Created by Greta on 2018-01-13.
 */

public class RadiobuttonActivity extends AppCompatActivity {

    private static final String TAG = "RadiobuttonActivity";

    //declare variables
    int points;
    String userName;
    String toast_message1;
    String toast_message2;
    String toast_message3;
    int progress;
    int clicked = 0;
    boolean back_pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_radiobutton);

        //Apply animation to animeView
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

        progress = extras.getInt("progress");
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.determinateBar);
        progressBar.setProgress(progress);

        // locate views
        ImageView navigation = (ImageView) findViewById(R.id.navigation);
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent myIntent = new Intent(RadiobuttonActivity.this, Results.class);
                    myIntent.putExtra("points", points);
                    myIntent.putExtra("name", userName);
                    myIntent.putExtra("progress", progress);
                    RadiobuttonActivity.this.startActivity(myIntent);
                }
        });
    }
    @Override
    public void onBackPressed() {
        if (!back_pressed) {
            Toast.makeText(RadiobuttonActivity.this, "Do you really want to restart quiz? \nPlease, push back one more time." , Toast.LENGTH_SHORT).show();
            back_pressed=true;}
        else {Intent homeIntent = new Intent(RadiobuttonActivity.this,MainActivity.class);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);}// your code.
    }
    }

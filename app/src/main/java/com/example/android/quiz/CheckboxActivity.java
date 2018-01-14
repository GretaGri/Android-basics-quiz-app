package com.example.android.quiz;

/**
 * Created by Greta on 2018-01-07.
 */

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;


public class CheckboxActivity extends AppCompatActivity {

    private static final String TAG = "CheckboxActivity";

    //declare variables
    int points;
    String userName;
    int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_checkbox);

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
        Log.d (TAG, "points" + points);
           userName = extras.getString("name");
           progress = extras.getInt("progress");
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.determinateBar);
        progressBar.setProgress (progress);
        ImageView navigation = (ImageView) findViewById(R.id.navigation);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent myIntent = new Intent(CheckboxActivity.this, VideoActivity.class);
                //Optional parameters
                CheckboxActivity.this.startActivity(myIntent);

            }

});
    }
}
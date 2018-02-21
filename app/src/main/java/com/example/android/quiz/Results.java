package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Greta on 2018-01-13.
 */

public class Results extends CustomToast {

    // Declare variables.
    int points;
    String userName;
    boolean back_pressed = false;
    String message;
    int toast_no;
    String toast_text;
    String letter_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        // Locate views.
        final ImageView animeView = findViewById(R.id.arrow);
        final ImageView navigation = findViewById(R.id.navigation);
        final TextView score = findViewById(R.id.score);
        final TextView lyrics = findViewById(R.id.lyrics);

        // Apply animation to arrow.
        Animation pulsingArrow = AnimationUtils.loadAnimation(this, R.anim.pulse);
        animeView.startAnimation(pulsingArrow);

        // Get extras.
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        points = extras.getInt("points");
        userName = extras.getString("name");
        message = userName + getString(R.string.results) + points + getString(R.string.results2);

        // Show final result and message depending on points.
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

        // Apply animation to present.
        Animation pulsingPresent = AnimationUtils.loadAnimation(this, R.anim.pulse);
        navigation.startAnimation(pulsingPresent);

        // Set on click listener to navigation button.
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Results.this, VideoActivity.class); // Go to another activity, send extra variables with points.
                myIntent.putExtra("points", points);
                Results.this.startActivity(myIntent);
            }

        });

        Button send = findViewById(R.id.send_letter); // Locate button.
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND); // Create intent to send email with text set after letter button click.
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_SUBJECT, getText(R.string.letter_results_subject));
                letter_text = getText(R.string.letter_results_text1).toString() + points;
                if (points == 1) {
                    letter_text += getText(R.string.letter_results_text2).toString();
                } else {
                    letter_text += getText(R.string.letter_results_text3).toString();
                }
                intent.putExtra(Intent.EXTRA_TEXT, letter_text);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }

    // Go to the beginning/restart app when back button is pushed second time, first time - show toast with question if user wants to go back. Solution: https://stackoverflow.com/questions/4119441/how-to-scroll-to-top-of-long-scrollview-layout
    @Override
    public void onBackPressed() {
        if (!back_pressed) {
            toast_no = 4;
            toast_text = getString(R.string.toastRestart);
            toast(toast_text, toast_no); // Toast message, when the back button is pressed.;
            back_pressed = true;
        } else {
            Intent homeIntent = new Intent(Results.this, MainActivity.class); // Solution found here: https://stackoverflow.com/questions/4756835/how-to-launch-home-screen-programmatically-in-android.
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }
}
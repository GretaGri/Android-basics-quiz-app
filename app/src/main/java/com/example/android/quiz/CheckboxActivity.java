package com.example.android.quiz;

/**
 * Created by Greta on 2018-01-07.
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;


public class CheckboxActivity extends AppCompatActivity {

    private static final String TAG = "CheckboxActivity";

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
        final ImageView picture = (ImageView) findViewById(R.id.picture_question2);
        final TextView question = (TextView) findViewById(R.id.cb_question);
        final CheckBox answer1 = (CheckBox) findViewById(R.id.cb_answer_1);
        final CheckBox answer2 = (CheckBox) findViewById(R.id.cb_answer_2);
        final CheckBox answer3 = (CheckBox) findViewById(R.id.cb_answer_3);
        final CheckBox answer4 = (CheckBox) findViewById(R.id.cb_answer_4);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == 0) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked() & !answer4.isChecked()) {
                        Toast.makeText(CheckboxActivity.this, toast_message1, Toast.LENGTH_SHORT).show(); //Toast message, if user didn't check any answer.
                        return;
                    } else if (answer1.isChecked() & !answer2.isChecked() & answer3.isChecked() & !answer4.isChecked()) {
                        points++; //add points for second answer
                        Toast.makeText(CheckboxActivity.this, toast_message2, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CheckboxActivity.this, toast_message3, Toast.LENGTH_SHORT).show();
                    }
                    //after pushing button go to the screen top.
                    final ScrollView scrollView = (ScrollView) findViewById(R.id.mainScrollView);
                    scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            // Ready, move up
                            scrollView.fullScroll(View.FOCUS_UP);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                    picture.setImageResource(R.drawable.question2_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer3.setClickable(false);
                    answer4.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer4.setClickable(false);
                    progress = progress + 10;
                    ProgressBar progressBar = (ProgressBar) findViewById(R.id.determinateBar);
                    progressBar.setProgress(progress);
                    clicked++;
                } else {
                    Intent myIntent = new Intent(CheckboxActivity.this, RadiobuttonActivity.class);
                    myIntent.putExtra("points", points);
                    myIntent.putExtra("name", userName);
                    myIntent.putExtra("progress", progress);
                    CheckboxActivity.this.startActivity(myIntent);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (!back_pressed) {
            Toast.makeText(CheckboxActivity.this, "Do you really want to restart quiz? \nPlease, push back one more time." , Toast.LENGTH_SHORT).show();
            back_pressed=true;}
            else {Intent homeIntent = new Intent(CheckboxActivity.this,MainActivity.class);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);}// your code.
        }
    }
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    // Saves clicked navigation button count, points, name and progress in case of changing activity.
    static final String STATE_CLICKED = "clicked";
    static final String STATE_SCORE = "score";
    static final String STATE_NAME = "name";
    static final String STATE_PROGRESS = "progress";

    // Declare variables.
    int points;
    String userName;
    String toast_message1;
    String toast_message2;
    String toast_message3;
    int toast_no = 0;
    int progress;
    int clicked = 0;
    boolean back_pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_checkbox);

        // Locate views.
        final ImageView animeView = (ImageView) findViewById(R.id.arrow);
        final ImageView navigation = (ImageView) findViewById(R.id.navigation);
        final ImageView picture = (ImageView) findViewById(R.id.picture_question2);
        final TextView question = (TextView) findViewById(R.id.cb_question);
        final CheckBox answer1 = (CheckBox) findViewById(R.id.cb_answer_1);
        final CheckBox answer2 = (CheckBox) findViewById(R.id.cb_answer_2);
        final CheckBox answer3 = (CheckBox) findViewById(R.id.cb_answer_3);
        final CheckBox answer4 = (CheckBox) findViewById(R.id.cb_answer_4);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.determinateBar);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.mainScrollView);

        // Apply animation to animeView.
        Animation pulsingArrow = AnimationUtils.loadAnimation(this, R.anim.pulse);
        animeView.startAnimation(pulsingArrow);

        // Get extras.
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        points = extras.getInt("points");

        userName = extras.getString("name");
        toast_message1 = userName + getString(R.string.toastNoAnswer);
        toast_message2 = userName + getString(R.string.toastCorrectAnswer);
        toast_message3 = userName + getString(R.string.toastWrongAnswer);

        progress = extras.getInt("progress");
        progressBar.setProgress(progress); // Show progress on progress bar.

        // Set on click listener to navigation button.
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == 0) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked() & !answer4.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no); // Toast message, if user didn't check any answer.
                        return;
                    } else if (answer1.isChecked() & !answer2.isChecked() & answer3.isChecked() & !answer4.isChecked()) {
                        points++; // Add points for correct answer.
                        toast_no = 2;
                        toast (toast_message2, toast_no); // Toast message, when the answer is correct.
                    } else {
                        toast_no = 3;
                        toast(toast_message3, toast_no); // Toast message, when the answer is wrong.
                    }

                    // Set scrollview that after pushing button the layout top is visible.
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

                    // Show correct/wrong answers, check boxes is not clickable.
                    picture.setImageResource(R.drawable.question2_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer3.setClickable(false);
                    answer4.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer4.setClickable(false);

                    // Calculate progress and show it on progress bar
                    progress = progress + 10;
                    progressBar.setProgress(progress);

                    // Count navigation button clicks
                    clicked++;

                } else if (clicked == 1) {
                    // Set new question picture/question/answers, checkboxes clickable/empty, color black.
                    picture.setImageResource(R.drawable.question3);
                    question.setText(R.string.question3);
                    answer1.setText(R.string.question3_answer1);
                    answer1.setTextColor(getResources().getColor(R.color.colorText));
                    answer1.setClickable(true);
                    answer1.setChecked(false);
                    answer2.setText(R.string.question3_answer2_correct);
                    answer2.setTextColor(getResources().getColor(R.color.colorText));
                    answer2.setClickable(true);
                    answer2.setChecked(false);
                    answer3.setText(R.string.question3_answer3);
                    answer3.setTextColor(getResources().getColor(R.color.colorText));
                    answer3.setClickable(true);
                    answer3.setChecked(false);
                    answer4.setText(R.string.question3_answer4_correct);
                    answer4.setTextColor(getResources().getColor(R.color.colorText));
                    answer4.setClickable(true);
                    answer4.setChecked(false);

                    // Count navigation button clicks.
                    clicked++;
                } else if (clicked == 2) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked() & !answer4.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no); // Toast message, if user didn't check any answer.
                        return;
                    } else if (!answer1.isChecked() & answer2.isChecked() & !answer3.isChecked() & answer4.isChecked()) {
                        points++; // Add points for correct answer.
                        toast_no = 2;
                        toast (toast_message2, toast_no); // Toast message, when the answer is correct.
                    } else {
                        toast_no = 3;
                        toast(toast_message3, toast_no); // Toast message, when the answer is wrong.
                    }

                    // Set scrollview that after pushing button the layout top is visible.
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

                    // Show correct/wrong answers, check boxes is not clickable.
                    picture.setImageResource(R.drawable.question3_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer3.setClickable(false);
                    answer4.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer4.setClickable(false);
                    progress += 10;
                    progressBar.setProgress(progress);
                    clicked++;
                } else {
                    Intent myIntent = new Intent(CheckboxActivity.this, RadiobuttonActivity.class); // Go to another activity, send extra variables with points, progress and name.
                    myIntent.putExtra("points", points);
                    myIntent.putExtra("name", userName);
                    myIntent.putExtra("progress", progress);
                    CheckboxActivity.this.startActivity(myIntent);
                }
            }
        });
    }

    // Save the user's current app state (remember to put static final strings!).
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_CLICKED, clicked);
        savedInstanceState.putInt(STATE_SCORE, points);
        savedInstanceState.putString(STATE_NAME, userName);
        savedInstanceState.putInt(STATE_PROGRESS, progress);
        super.onSaveInstanceState(savedInstanceState);
    }

    // Always call the superclass so it can restore the view hierarchy.
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance.
        points = savedInstanceState.getInt(STATE_SCORE);
        clicked = savedInstanceState.getInt(STATE_CLICKED);
        userName = savedInstanceState.getString(STATE_NAME);
        progress = savedInstanceState.getInt(STATE_PROGRESS);

        // Locate views.
        final ImageView picture = (ImageView) findViewById(R.id.picture_question2);
        final TextView question = (TextView) findViewById(R.id.cb_question);
        final CheckBox answer1 = (CheckBox) findViewById(R.id.cb_answer_1);
        final CheckBox answer2 = (CheckBox) findViewById(R.id.cb_answer_2);
        final CheckBox answer3 = (CheckBox) findViewById(R.id.cb_answer_3);
        final CheckBox answer4 = (CheckBox) findViewById(R.id.cb_answer_4);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.determinateBar);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.mainScrollView);

        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {
                // Ready, move up.
                scrollView.fullScroll(View.FOCUS_UP);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        progressBar.setProgress(progress);
        if (clicked == 1) {
            picture.setImageResource(R.drawable.question2_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer3.setClickable(false);
            answer4.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer4.setClickable(false);
        } else if (clicked == 2) {
            picture.setImageResource(R.drawable.question3);
            question.setText(R.string.question3);
            answer1.setText(R.string.question3_answer1);
            answer1.setTextColor(getResources().getColor(R.color.colorText));
            answer1.setClickable(true);
            answer2.setText(R.string.question3_answer2_correct);
            answer2.setTextColor(getResources().getColor(R.color.colorText));
            answer2.setClickable(true);
            answer3.setText(R.string.question3_answer3);
            answer3.setTextColor(getResources().getColor(R.color.colorText));
            answer3.setClickable(true);
            answer4.setText(R.string.question3_answer4_correct);
            answer4.setTextColor(getResources().getColor(R.color.colorText));
            answer4.setClickable(true);

        } else if (clicked == 3) {
            picture.setImageResource(R.drawable.question3_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer3.setClickable(false);
            answer4.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer4.setClickable(false);
        }
    }

    // Go to the beginning/restart app when back button is pushed second time, first time - show toast with question if user wants to go back.
    @Override
    public void onBackPressed() {
        if (!back_pressed) {
            String restart = getString(R.string.toastRestart);
            toast_no = 4;
            toast (restart, toast_no); // Toast message, when the back button is pressed.
            back_pressed = true;
        } else {
            Intent homeIntent = new Intent(CheckboxActivity.this, MainActivity.class);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }
    // Method for displaying custom toast
    public void toast (String toast_text, int toast_no) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom_toast));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0,250);
        ImageView image = (ImageView) layout.findViewById(R.id.toast_image);
        if (toast_no == 1) {
        image.setImageResource(R.drawable.ic_not_found);
        }
        else if (toast_no == 2){
            image.setImageResource(R.drawable.ic_happy);
        }
        else if (toast_no == 3){
            image.setImageResource(R.drawable.ic_sad);
        }
        else if (toast_no == 4){
            image.setImageResource(R.drawable.ic_return);
        }
        TextView textV = (TextView) layout.findViewById(R.id.toast);
        textV.setText(toast_text);

        toast.setView(layout);
        toast.show();
    }
}
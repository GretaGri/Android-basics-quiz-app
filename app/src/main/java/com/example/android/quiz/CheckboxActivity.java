package com.example.android.quiz;

/**
 * Created by Greta on 2018-01-07.
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
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

import java.util.ArrayList;
import java.util.Collections;


public class CheckboxActivity extends CustomToast {

    // Saves clicked navigation button count, points, name and progress in case of changing activity.
    static final String STATE_CLICKED = "clicked";
    static final String STATE_SCORE = "score";
    static final String STATE_NAME = "name";
    static final String STATE_PROGRESS = "progress";
    static final String STATE_ANSWER1 = "answer1string";
    static final String STATE_ANSWER2 = "answer2string";
    static final String STATE_ANSWER3 = "answer3string";
    static final String STATE_ANSWER4 = "answer4string";
    static final String STATE_CORRECT1 = "correct1";
    static final String STATE_CORRECT2 = "correct2";
    static final String STATE_CORRECT3 = "correct3";
    static final String STATE_CORRECT4 = "correct4";

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
    ImageView animeView;
    ProgressBar progressBar;
    ScrollView scrollView;
    ImageView navigation;
    ImageView picture;
    TextView question;
    CheckBox answer1;
    CheckBox answer2;
    CheckBox answer3;
    CheckBox answer4;
    Boolean correct = false;
    int image_no = 0;
    Boolean correct1 = false;
    Boolean correct2 = false;
    Boolean correct3 = false;
    Boolean correct4 = false;
    String answer1string;
    String answer2string;
    String answer3string;
    String answer4string;
    int checkIfCorrect = 0;
    int question_no = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_checkbox);

        // Locate views.
        animeView = findViewById(R.id.arrow);
        navigation = findViewById(R.id.navigation);
        picture = findViewById(R.id.picture_question2);
        question = findViewById(R.id.cb_question);
        answer1 = findViewById(R.id.cb_answer_1);
        answer2 = findViewById(R.id.cb_answer_2);
        answer3 = findViewById(R.id.cb_answer_3);
        answer4 = findViewById(R.id.cb_answer_4);
        progressBar = findViewById(R.id.determinateBar);
        scrollView = findViewById(R.id.mainScrollView);

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

        // Set new question picture/question/answers color black.
        question_no = 2;
        new_question();

        // Set on click listener to navigation button.
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == 0) {
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    correct4 = false;
                    answer1string = answer1.getText().toString();
                    answer2string = answer2.getText().toString();
                    answer3string = answer3.getText().toString();
                    answer4string = answer4.getText().toString();
                    if (answer1string.equals(getString(R.string.question2_answer1_correct)) || answer1string.equals(getString(R.string.question2_answer3_correct))) {
                        correct1 = true;
                    }
                    if (answer2string.equals(getString(R.string.question2_answer1_correct)) || answer2string.equals(getString(R.string.question2_answer3_correct))) {
                        correct2 = true;
                    }
                    if (answer3string.equals(getString(R.string.question2_answer1_correct)) || answer3string.equals(getString(R.string.question2_answer3_correct))) {
                        correct3 = true;
                    }
                    if (answer4string.equals(getString(R.string.question2_answer1_correct)) || answer4string.equals(getString(R.string.question2_answer3_correct))) {
                        correct4 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked() & !answer4.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    }
                    if (answer1.isChecked() && correct1) {
                        checkIfCorrect++;
                    }
                    if (answer2.isChecked() && correct2) {
                        checkIfCorrect++;
                    }
                    if (answer3.isChecked() && correct3) {
                        checkIfCorrect++;
                    }
                    if (answer4.isChecked() && correct4) {
                        checkIfCorrect++;
                    }
                    if (answer1.isChecked() && !correct1) {
                        checkIfCorrect--;
                    }
                    if (answer2.isChecked() && !correct2) {
                        checkIfCorrect--;
                    }
                    if (answer3.isChecked() && !correct3) {
                        checkIfCorrect--;
                    }
                    if (answer4.isChecked() && !correct4) {
                        checkIfCorrect--;
                    }
                   if (checkIfCorrect == 2) {
                        correct = true;
                    }
                    image_no = 1;
                    check_answers(); // Check answers.
                    set_answers(); // Show the correct answers.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
                    clicked++; // Count navigation button clicks.

                } else if (clicked == 1) {
                    question_no = 3;
                    // Set new question picture/question/answers color black.
                    new_question();
                    // Set checkboxes clickable/empty.
                    new_question2();
                    // Count navigation button clicks.
                    clicked++;
                } else if (clicked == 2) {
                    // Set new question picture/question/answers color black.
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    correct4 = false;
                    checkIfCorrect = 0;
                    answer1string = answer1.getText().toString();
                    answer2string = answer2.getText().toString();
                    answer3string = answer3.getText().toString();
                    answer4string = answer4.getText().toString();
                    if (answer1string.equals(getString(R.string.question3_answer2_correct)) || answer1string.equals(getString(R.string.question3_answer4_correct))) {
                        correct1 = true;
                    }
                    if (answer2string.equals(getString(R.string.question3_answer2_correct)) || answer2string.equals(getString(R.string.question3_answer4_correct))) {
                        correct2 = true;
                    }
                    if (answer3string.equals(getString(R.string.question3_answer2_correct)) || answer3string.equals(getString(R.string.question3_answer4_correct))) {
                        correct3 = true;
                    }
                    if (answer4string.equals(getString(R.string.question3_answer2_correct)) || answer4string.equals(getString(R.string.question3_answer4_correct))) {
                        correct4 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked() & !answer4.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    }
                    checkIfCorrect = 0;
                    if (answer1.isChecked() & correct1) {
                        checkIfCorrect++;
                    }
                    if (answer2.isChecked() & correct2) {
                        checkIfCorrect++;
                    }
                    if (answer3.isChecked() & correct3) {
                        checkIfCorrect++;
                    }
                    if (answer4.isChecked() & correct4) {
                        checkIfCorrect++;
                    }
                    if (answer1.isChecked() && !correct1) {
                        checkIfCorrect--;
                    }
                    if (answer2.isChecked() && !correct2) {
                        checkIfCorrect--;
                    }
                    if (answer3.isChecked() && !correct3) {
                        checkIfCorrect--;
                    }
                    if (answer4.isChecked() && !correct4) {
                        checkIfCorrect--;
                    }
                    if (checkIfCorrect == 2) {
                        correct = true;
                    }
                    image_no = 2;
                    check_answers();
                    set_answers(); // Set wrong/correct answers and answer picture.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
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
        savedInstanceState.putBoolean(STATE_CORRECT1, correct1);
        savedInstanceState.putBoolean(STATE_CORRECT2, correct2);
        savedInstanceState.putBoolean(STATE_CORRECT3, correct3);
        savedInstanceState.putBoolean(STATE_CORRECT4, correct4);
        savedInstanceState.putString(STATE_ANSWER1,answer1string);
        savedInstanceState.putString(STATE_ANSWER2,answer2string);
        savedInstanceState.putString(STATE_ANSWER3,answer3string);
        savedInstanceState.putString(STATE_ANSWER4,answer4string);
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
        correct1 = savedInstanceState.getBoolean(STATE_CORRECT1);
        correct2 = savedInstanceState.getBoolean(STATE_CORRECT2);
        correct3 = savedInstanceState.getBoolean(STATE_CORRECT3);
        correct4 = savedInstanceState.getBoolean(STATE_CORRECT4);
        answer1string = savedInstanceState.getString(STATE_ANSWER1);
        answer2string = savedInstanceState.getString(STATE_ANSWER2);
        answer3string = savedInstanceState.getString(STATE_ANSWER3);
        answer4string = savedInstanceState.getString(STATE_ANSWER4);

        answer1 = findViewById(R.id.cb_answer_1);
        answer2 = findViewById(R.id.cb_answer_2);
        answer3 = findViewById(R.id.cb_answer_3);
        answer4 = findViewById(R.id.cb_answer_4);

        setScrollView();

        progressBar.setProgress(progress);

        if (clicked == 1) {
            image_no = 1;
            set_answers();
            answer1.setText(answer1string);
            answer2.setText(answer2string);
            answer3.setText(answer3string);
            answer4.setText(answer4string);
        } else if (clicked == 2) {
            question_no = 3;
            new_question();
        } else if (clicked == 3) {
            question_no = 3;
            new_question();
            image_no = 2;
            set_answers();
            answer1.setText(answer1string);
            answer2.setText(answer2string);
            answer3.setText(answer3string);
            answer4.setText(answer4string);
        }
    }

    // Go to the beginning/restart app when back button is pushed second time, first time - show toast with question if user wants to go back.
    @Override
    public void onBackPressed() {
        if (!back_pressed) {
            String restart = getString(R.string.toastRestart);
            toast_no = 4;
            toast(restart, toast_no); // Toast message, when the back button is pressed.
            back_pressed = true;
        } else {
            Intent homeIntent = new Intent(CheckboxActivity.this, MainActivity.class);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

    // Set new question picture/question/answers color black.
    public void new_question() {
        if (question_no == 2) {
            picture.setImageResource(R.drawable.question2);
            question.setText(R.string.question2);
            ArrayList<String> question2answers = new ArrayList<String>();
            question2answers.add(getText(R.string.question2_answer1_correct).toString());
            question2answers.add(getText(R.string.question2_answer2).toString());
            question2answers.add(getText(R.string.question2_answer3_correct).toString());
            question2answers.add(getText(R.string.question2_answer4).toString());
            Collections.shuffle(question2answers);
            answer1.setText(question2answers.get(0));
            answer2.setText(question2answers.get(1));
            answer3.setText(question2answers.get(2));
            answer4.setText(question2answers.get(3));
        }
        if (question_no == 3) {
            picture.setImageResource(R.drawable.question3);
            question.setText(R.string.question3);
            ArrayList<String> question3answers = new ArrayList<String>();
            question3answers.add(getText(R.string.question3_answer1).toString());
            question3answers.add(getText(R.string.question3_answer2_correct).toString());
            question3answers.add(getText(R.string.question3_answer3).toString());
            question3answers.add(getText(R.string.question3_answer4_correct).toString());
            Collections.shuffle(question3answers);
            answer1.setText(question3answers.get(0));
            answer2.setText(question3answers.get(1));
            answer3.setText(question3answers.get(2));
            answer4.setText(question3answers.get(3));
        }
        answer1.setTextColor(getResources().getColor(R.color.colorText));
        answer2.setTextColor(getResources().getColor(R.color.colorText));
        answer3.setTextColor(getResources().getColor(R.color.colorText));
        answer4.setTextColor(getResources().getColor(R.color.colorText));
    }

    // Set  checkboxes clickable/empty,
    public void new_question2() {
        correct = false;
        answer1.setClickable(true);
        answer1.setChecked(false);
        answer2.setClickable(true);
        answer2.setChecked(false);
        answer3.setClickable(true);
        answer3.setChecked(false);
        answer4.setClickable(true);
        answer4.setChecked(false);
    }

    public void check_answers() {
        if (correct) {
            points++; // Add points for correct answer.
            toast_no = 2;
            toast(toast_message2, toast_no); // Toast message, when the answer is correct.
        } else {
            toast_no = 3;
            toast(toast_message3, toast_no); // Toast message, when the answer is wrong.
        }
    }

    public void set_answers() {
        // Show correct/wrong answers, check boxes is not clickable.
        if (image_no == 1) {
            picture.setImageResource(R.drawable.question2_answer);
        } else if (image_no == 2) {
            picture.setImageResource(R.drawable.question3_answer);
        }
        answer1.setClickable(false);
        answer2.setClickable(false);
        answer3.setClickable(false);
        answer4.setClickable(false);
        if (correct1) {
            answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
        } else {
            answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
        }
        if (correct2) {
            answer2.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
        } else {
            answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
        }
        if (correct3) {
            answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
        } else {
            answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
        }
        if (correct4) {
            answer4.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
        } else {
            answer4.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
        }
    }

    // Calculate progress and show it on progress bar
    public void calculateProgress() {
        progress = progress + 10;
        progressBar.setProgress(progress);
    }

    // Set scrollview that after pushing button the layout top is visible.
    public void setScrollView() {
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
    }

}
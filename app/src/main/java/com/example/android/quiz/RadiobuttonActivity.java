package com.example.android.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Greta on 2018-01-13.
 */

public class RadiobuttonActivity extends CustomToast {

    // Saves clicked navigation button count, points, name and progress in case of changing activity.
    static final String STATE_CLICKED = "clicked";
    static final String STATE_SCORE = "score";
    static final String STATE_NAME = "name";
    static final String STATE_PROGRESS = "progress";
    static final String STATE_CORRECT1 = "correct1";
    static final String STATE_CORRECT2 = "correct2";
    static final String STATE_CORRECT3 = "correct3";

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
    RadioGroup group;
    RadioButton answer1;
    RadioButton answer2;
    RadioButton answer3;
    Boolean correct = false;
    int image_no = 0;
    Boolean correct1 = false;
    Boolean correct2 = false;
    Boolean correct3 = false;
    int question_no = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_radiobutton);

        // Locate views.
        animeView = findViewById(R.id.arrow);
        navigation = findViewById(R.id.navigation);
        picture = findViewById(R.id.picture_question2);
        question = findViewById(R.id.tv_rb_question);
        answer1 = findViewById(R.id.rb_answer_1);
        answer2 = findViewById(R.id.rb_answer_2);
        answer3 = findViewById(R.id.rb_answer_3);
        group = findViewById(R.id.rg_question);
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

        question_no = 4;
        new_question();

        // Set on click listener to navigation button.
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == 0) {
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    if (answer1.getText().toString().equals(getString(R.string.question4_answer3_correct))) {
                        correct1 = true;
                    } else if (answer2.getText().toString().equals(getString(R.string.question4_answer3_correct))) {
                        correct2 = true;
                    } else if (answer3.getText().toString().equals(getString(R.string.question4_answer3_correct))) {
                        correct3 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked() & correct1) {
                        correct = true;
                    } else if (answer2.isChecked() & correct2) {
                        correct = true;
                    } else if (answer3.isChecked() & correct3) {
                        correct = true;
                    }
                    image_no = 1;
                    check_answers(); // Check answers.
                    set_answers(); // Show the correct answers.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
                    clicked++; // Count navigation button clicks.
                } else if (clicked == 1) {
                    // Set new question picture/question/answers, radiobuttons clickable/empty, color black.
                    question_no = 5;
                    new_question();
                    new_question2();
                    clicked++;// Count navigation button clicks.
                } else if (clicked == 2) {
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    if (answer1.getText().toString().equals(getString(R.string.question5_answer2_correct))) {
                        correct1 = true;
                    } else if (answer2.getText().toString().equals(getString(R.string.question5_answer2_correct))) {
                        correct2 = true;
                    } else if (answer3.getText().toString().equals(getString(R.string.question5_answer2_correct))) {
                        correct3 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked() & correct1) {
                        correct = true;
                    } else if (answer2.isChecked() & correct2) {
                        correct = true;
                    } else if (answer3.isChecked() & correct3) {
                        correct = true;
                    }
                    image_no = 2;
                    check_answers(); // Check answers.
                    set_answers(); // Show the correct answers.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
                    clicked++;
                } else if (clicked == 3) {
                    question_no = 6;
                    new_question();
                    new_question2();
                    clicked++;
                } else if (clicked == 4) {
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    if (answer1.getText().toString().equals(getString(R.string.question6_answer1_correct))) {
                        correct1 = true;
                    } else if (answer2.getText().toString().equals(getString(R.string.question6_answer1_correct))) {
                        correct2 = true;
                    } else if (answer3.getText().toString().equals(getString(R.string.question6_answer1_correct))) {
                        correct3 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked() & correct1) {
                        correct = true;
                    } else if (answer2.isChecked() & correct2) {
                        correct = true;
                    } else if (answer3.isChecked() & correct3) {
                        correct = true;
                    }
                    image_no = 3;
                    check_answers(); // Check answers.
                    set_answers(); // Show the correct answers.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
                    clicked++;
                } else if (clicked == 5) {
                    question_no = 7;
                    new_question();
                    new_question2();
                    clicked++;
                } else if (clicked == 6) {
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    if (answer1.getText().toString().equals(getString(R.string.question7_answer2_correct))) {
                        correct1 = true;
                    } else if (answer2.getText().toString().equals(getString(R.string.question7_answer2_correct))) {
                        correct2 = true;
                    } else if (answer3.getText().toString().equals(getString(R.string.question7_answer2_correct))) {
                        correct3 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked() & correct1) {
                        correct = true;
                    } else if (answer2.isChecked() & correct2) {
                        correct = true;
                    } else if (answer3.isChecked() & correct3) {
                        correct = true;
                    }
                    image_no = 4;
                    check_answers(); // Check answers.
                    set_answers(); // Show the correct answers.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
                    clicked++;
                } else if (clicked == 7) {
                    question_no = 8;
                    new_question();
                    new_question2();
                    clicked++;
                } else if (clicked == 8) {
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    if (answer1.getText().toString().equals(getString(R.string.question8_answer3_correct))) {
                        correct1 = true;
                    } else if (answer2.getText().toString().equals(getString(R.string.question8_answer3_correct))) {
                        correct2 = true;
                    } else if (answer3.getText().toString().equals(getString(R.string.question8_answer3_correct))) {
                        correct3 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked() & correct1) {
                        correct = true;
                    } else if (answer2.isChecked() & correct2) {
                        correct = true;
                    } else if (answer3.isChecked() & correct3) {
                        correct = true;
                    }
                    image_no = 5;
                    check_answers(); // Check answers.
                    set_answers(); // Show the correct answers.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
                    clicked++;
                } else if (clicked == 9) {
                    question_no = 9;
                    new_question();
                    new_question2();
                    clicked++;
                } else if (clicked == 10) {
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    if (answer1.getText().toString().equals(getString(R.string.question9_answer3_correct))) {
                        correct1 = true;
                    } else if (answer2.getText().toString().equals(getString(R.string.question9_answer3_correct))) {
                        correct2 = true;
                    } else if (answer3.getText().toString().equals(getString(R.string.question9_answer3_correct))) {
                        correct3 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked() & correct1) {
                        correct = true;
                    } else if (answer2.isChecked() & correct2) {
                        correct = true;
                    } else if (answer3.isChecked() & correct3) {
                        correct = true;
                    }
                    image_no = 6;
                    check_answers(); // Check answers.
                    set_answers(); // Show the correct answers.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
                    clicked++;
                } else if (clicked == 11) {
                    question_no = 10;
                    new_question();
                    new_question2();
                    clicked++;
                } else if (clicked == 12) {
                    correct1 = false;
                    correct2 = false;
                    correct3 = false;
                    if (answer1.getText().toString().equals(getString(R.string.question10_answer1_correct))) {
                        correct1 = true;
                    } else if (answer2.getText().toString().equals(getString(R.string.question10_answer1_correct))) {
                        correct2 = true;
                    } else if (answer3.getText().toString().equals(getString(R.string.question10_answer1_correct))) {
                        correct3 = true;
                    }
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast(toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked() & correct1) {
                        correct = true;
                    } else if (answer2.isChecked() & correct2) {
                        correct = true;
                    } else if (answer3.isChecked() & correct3) {
                        correct = true;
                    }
                    image_no = 7;
                    check_answers(); // Check answers.
                    set_answers(); // Show the correct answers.
                    calculateProgress(); // Add and set progress.
                    setScrollView(); // Set scrollview to the top.
                    clicked++;
                } else {
                    Intent myIntent = new Intent(RadiobuttonActivity.this, Results.class); // Go to another activity, send extra variables with points, progress and name.
                    myIntent.putExtra("points", points);
                    myIntent.putExtra("name", userName);
                    myIntent.putExtra("progress", progress);
                    RadiobuttonActivity.this.startActivity(myIntent);
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

        // Locate views.
        progressBar = findViewById(R.id.determinateBar);
        progressBar.setProgress(progress);
        setScrollView();
        if (clicked == 1) {
            image_no = 1;
            set_answers();
        } else if (clicked == 2) {
            question_no = 5;
            new_question();
        } else if (clicked == 3) {
            question_no = 5;
            new_question();
            image_no = 2;
            set_answers();
        } else if (clicked == 4) {
            question_no = 6;
            new_question();
        } else if (clicked == 5) {
            question_no = 6;
            new_question();
            image_no = 3;
            set_answers();
        } else if (clicked == 6) {
            question_no = 7;
            new_question();
            //answer3.setClickable(true);
        } else if (clicked == 7) {
            question_no = 7;
            new_question();
            image_no = 4;
            set_answers();
        } else if (clicked == 8) {
            question_no = 8;
            new_question();
        } else if (clicked == 9) {
            question_no = 8;
            new_question();
            image_no = 5;
            set_answers();
        } else if (clicked == 10) {
            question_no = 9;
            new_question();
        } else if (clicked == 11) {
            question_no = 9;
            new_question();
            image_no = 6;
            set_answers();
        } else if (clicked == 12) {
            question_no = 10;
            new_question();
        } else if (clicked == 13) {
            question_no = 10;
            new_question();
            image_no = 7;
            set_answers();
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
            Intent homeIntent = new Intent(RadiobuttonActivity.this, MainActivity.class);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
        }
    }

    // Set new question picture/question/answers color black.
    public void new_question() {
        if (question_no == 4) {
            picture.setImageResource(R.drawable.question4);
            question.setText(R.string.question4);
            ArrayList<String> question4answers = new ArrayList<String>();
            question4answers.add(getText(R.string.question4_answer1).toString());
            question4answers.add(getText(R.string.question4_answer2).toString());
            question4answers.add(getText(R.string.question4_answer3_correct).toString());
            Collections.shuffle(question4answers);
            answer1.setText(question4answers.get(0));
            answer2.setText(question4answers.get(1));
            answer3.setText(question4answers.get(2));
        }
        if (question_no == 5) {
            picture.setImageResource(R.drawable.question5);
            question.setText(R.string.question5);
            ArrayList<String> question5answers = new ArrayList<String>();
            question5answers.add(getText(R.string.question5_answer1).toString());
            question5answers.add(getText(R.string.question5_answer2_correct).toString());
            question5answers.add(getText(R.string.question5_answer3).toString());
            Collections.shuffle(question5answers);
            answer1.setText(question5answers.get(0));
            answer2.setText(question5answers.get(1));
            answer3.setText(question5answers.get(2));
        } else if (question_no == 6) {
            picture.setImageResource(R.drawable.question6);
            question.setText(R.string.question6);
            ArrayList<String> question6answers = new ArrayList<String>();
            question6answers.add(getText(R.string.question6_answer1_correct).toString());
            question6answers.add(getText(R.string.question6_answer2).toString());
            question6answers.add(getText(R.string.question6_answer3).toString());
            Collections.shuffle(question6answers);
            answer1.setText(question6answers.get(0));
            answer2.setText(question6answers.get(1));
            answer3.setText(question6answers.get(2));
        } else if (question_no == 7) {
            picture.setImageResource(R.drawable.question7);
            question.setText(R.string.question7);
            ArrayList<String> question7answers = new ArrayList<String>();
            question7answers.add(getText(R.string.question7_answer1).toString());
            question7answers.add(getText(R.string.question7_answer2_correct).toString());
            question7answers.add(getText(R.string.question7_answer3).toString());
            Collections.shuffle(question7answers);
            answer1.setText(question7answers.get(0));
            answer2.setText(question7answers.get(1));
            answer3.setText(question7answers.get(2));
        } else if (question_no == 8) {
            picture.setImageResource(R.drawable.question8);
            question.setText(R.string.question8);
            ArrayList<String> question8answers = new ArrayList<String>();
            question8answers.add(getText(R.string.question8_answer1).toString());
            question8answers.add(getText(R.string.question8_answer2).toString());
            question8answers.add(getText(R.string.question8_answer3_correct).toString());
            Collections.shuffle(question8answers);
            answer1.setText(question8answers.get(0));
            answer2.setText(question8answers.get(1));
            answer3.setText(question8answers.get(2));
        } else if (question_no == 9) {
            picture.setImageResource(R.drawable.question9);
            question.setText(R.string.question9);
            ArrayList<String> question9answers = new ArrayList<String>();
            question9answers.add(getText(R.string.question9_answer1).toString());
            question9answers.add(getText(R.string.question9_answer2).toString());
            question9answers.add(getText(R.string.question9_answer3_correct).toString());
            Collections.shuffle(question9answers);
            answer1.setText(question9answers.get(0));
            answer2.setText(question9answers.get(1));
            answer3.setText(question9answers.get(2));
        } else if (question_no == 10) {
            picture.setImageResource(R.drawable.question10);
            question.setText(R.string.question10);
            ArrayList<String> question10answers = new ArrayList<String>();
            question10answers.add(getText(R.string.question10_answer1_correct).toString());
            question10answers.add(getText(R.string.question10_answer2).toString());
            question10answers.add(getText(R.string.question10_answer3).toString());
            Collections.shuffle(question10answers);
            answer1.setText(question10answers.get(0));
            answer2.setText(question10answers.get(1));
            answer3.setText(question10answers.get(2));
        }
        answer1.setTextColor(getResources().getColor(R.color.colorText));
        answer2.setTextColor(getResources().getColor(R.color.colorText));
        answer3.setTextColor(getResources().getColor(R.color.colorText));
    }

    // Set  checkboxes clickable/empty,
    public void new_question2() {
        answer1.setClickable(true);
        answer2.setClickable(true);
        answer3.setClickable(true);
        group.clearCheck();
        correct = false;
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
            picture.setImageResource(R.drawable.question4_answer);
        } else if (image_no == 2) {
            picture.setImageResource(R.drawable.question5_answer);
        } else if (image_no == 3) {
            picture.setImageResource(R.drawable.question6_answer);
        } else if (image_no == 4) {
            picture.setImageResource(R.drawable.question7_answer);
        } else if (image_no == 5) {
            picture.setImageResource(R.drawable.question8_answer);
        } else if (image_no == 6) {
            picture.setImageResource(R.drawable.question9_answer);
        } else if (image_no == 7) {
            picture.setImageResource(R.drawable.question10_answer);
        }
        answer1.setClickable(false);
        answer2.setClickable(false);
        answer3.setClickable(false);

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

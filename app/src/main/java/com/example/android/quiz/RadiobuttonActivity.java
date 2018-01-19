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

/**
 * Created by Greta on 2018-01-13.
 */

public class RadiobuttonActivity extends AppCompatActivity {

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
        setContentView(R.layout.question_radiobutton);

        // Locate views.
        final ImageView animeView = (ImageView) findViewById(R.id.arrow);
        final ImageView navigation = (ImageView) findViewById(R.id.navigation);
        final ImageView picture = (ImageView) findViewById(R.id.picture_question2);
        final TextView question = (TextView) findViewById(R.id.tv_rb_question);
        final RadioButton answer1 = (RadioButton) findViewById(R.id.rb_answer_1);
        final RadioButton answer2 = (RadioButton) findViewById(R.id.rb_answer_2);
        final RadioButton answer3 = (RadioButton) findViewById(R.id.rb_answer_3);
        final RadioGroup group = (RadioGroup) findViewById(R.id.rg_question);
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
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no); // Toast message, if user didn't check any answer.
                        return;
                    } else if (answer3.isChecked()) {
                        points++; //add points for correct answer
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
                            // Ready, move up.
                            scrollView.fullScroll(View.FOCUS_UP);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                    // Show correct/wrong answers, check boxes is not clickable.
                    picture.setImageResource(R.drawable.question4_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer3.setClickable(false);

                    // Calculate progress and show it on progress bar
                    progress +=  10;
                    progressBar.setProgress(progress);

                    // Count navigation button clicks.
                    clicked++;
                } else if (clicked == 1) {
                    // Set new question picture/question/answers, radiobuttons clickable/empty, color black.
                    group.clearCheck();
                    picture.setImageResource(R.drawable.question5);
                    question.setText(R.string.question5);
                    answer1.setText(R.string.question5_answer1);
                    answer1.setTextColor(getResources().getColor(R.color.colorText));
                    answer1.setClickable(true);
                    answer2.setText(R.string.question5_answer2_correct);
                    answer2.setTextColor(getResources().getColor(R.color.colorText));
                    answer2.setClickable(true);
                    answer3.setText(R.string.question5_answer3);
                    answer3.setTextColor(getResources().getColor(R.color.colorText));
                    answer3.setClickable(true);
                    // Count navigation button clicks.
                    clicked++;
                } else if (clicked == 2) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no);;
                        return;
                    } else if (answer2.isChecked()) {
                        points++;
                        toast_no = 2;
                        toast (toast_message2, toast_no);
                    } else {
                        toast_no = 3;
                        toast(toast_message3, toast_no);
                    }
                    scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            scrollView.fullScroll(View.FOCUS_UP);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                    picture.setImageResource(R.drawable.question5_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer3.setClickable(false);
                    progress +=  10;
                    progressBar.setProgress(progress);
                    clicked++;
                } else if (clicked == 3) {
                    group.clearCheck();
                    picture.setImageResource(R.drawable.question6);
                    question.setText(R.string.question6);
                    answer1.setText(R.string.question6_answer1_correct);
                    answer1.setTextColor(getResources().getColor(R.color.colorText));
                    answer1.setClickable(true);
                    answer2.setText(R.string.question6_answer2);
                    answer2.setTextColor(getResources().getColor(R.color.colorText));
                    answer2.setClickable(true);
                    answer3.setText(R.string.question6_answer3);
                    answer3.setTextColor(getResources().getColor(R.color.colorText));
                    answer3.setClickable(true);
                    clicked++;
                } else if (clicked == 4) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked()) {
                        points++;
                        toast_no = 2;
                        toast (toast_message2, toast_no);
                    } else {
                        toast_no = 3;
                        toast(toast_message3, toast_no);
                    }
                    scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            scrollView.fullScroll(View.FOCUS_UP);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                    picture.setImageResource(R.drawable.question6_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer3.setClickable(false);
                    progress +=  10;
                    progressBar.setProgress(progress);
                    clicked++;
                } else if (clicked == 5) {
                    group.clearCheck();
                    picture.setImageResource(R.drawable.question7);
                    question.setText(R.string.question7);
                    answer1.setText(R.string.question7_answer1);
                    answer1.setTextColor(getResources().getColor(R.color.colorText));
                    answer1.setClickable(true);
                    answer2.setText(R.string.question7_answer2_correct);
                    answer2.setTextColor(getResources().getColor(R.color.colorText));
                    answer2.setClickable(true);
                    answer3.setText(R.string.question7_answer3);
                    answer3.setTextColor(getResources().getColor(R.color.colorText));
                    answer3.setClickable(true);
                    clicked++;
                } else if (clicked == 6) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no);;
                        return;
                    } else if (answer2.isChecked()) {
                        points++;
                        toast_no = 2;
                        toast (toast_message2, toast_no);
                    } else {
                        toast_no = 3;
                        toast(toast_message3, toast_no);
                    }
                    scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            scrollView.fullScroll(View.FOCUS_UP);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                    picture.setImageResource(R.drawable.question7_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer3.setClickable(false);
                    progress +=  10;
                    progressBar.setProgress(progress);
                    clicked++;
                } else if (clicked == 7) {
                    group.clearCheck();
                    picture.setImageResource(R.drawable.question8);
                    question.setText(R.string.question8);
                    answer1.setText(R.string.question8_answer1);
                    answer1.setTextColor(getResources().getColor(R.color.colorText));
                    answer1.setClickable(true);
                    answer2.setText(R.string.question8_answer2);
                    answer2.setTextColor(getResources().getColor(R.color.colorText));
                    answer2.setClickable(true);
                    answer3.setText(R.string.question8_answer3_correct);
                    answer3.setTextColor(getResources().getColor(R.color.colorText));
                    answer3.setClickable(true);
                    clicked++;
                } else if (clicked == 8) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no);
                        return;
                    } else if (answer3.isChecked()) {
                        points++;
                        toast_no = 2;
                        toast (toast_message2, toast_no);
                    } else {
                        toast_no = 3;
                        toast(toast_message3, toast_no);
                    }
                    scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            scrollView.fullScroll(View.FOCUS_UP);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                    picture.setImageResource(R.drawable.question8_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer3.setClickable(false);
                    progress += 10;
                    progressBar.setProgress(progress);
                    clicked++;
                } else if (clicked == 9) {
                    group.clearCheck();
                    picture.setImageResource(R.drawable.question9);
                    question.setText(R.string.question9);
                    answer1.setText(R.string.question9_answer1);
                    answer1.setTextColor(getResources().getColor(R.color.colorText));
                    answer1.setClickable(true);
                    answer2.setText(R.string.question9_answer2);
                    answer2.setTextColor(getResources().getColor(R.color.colorText));
                    answer2.setClickable(true);
                    answer3.setText(R.string.question9_answer3_correct);
                    answer3.setTextColor(getResources().getColor(R.color.colorText));
                    answer3.setClickable(true);
                    clicked++;
                } else if (clicked == 10) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no);
                        return;
                    } else if (answer3.isChecked()) {
                        points++;
                        toast_no = 2;
                        toast (toast_message2, toast_no);
                    } else {
                        toast_no = 3;
                        toast(toast_message3, toast_no);
                    }
                    scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            scrollView.fullScroll(View.FOCUS_UP);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                    picture.setImageResource(R.drawable.question9_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer3.setClickable(false);
                    progress += 10;
                    progressBar.setProgress(progress);
                    clicked++;
                } else if (clicked == 11) {
                    group.clearCheck();
                    picture.setImageResource(R.drawable.question10);
                    question.setText(R.string.question10);
                    answer1.setText(R.string.question10_answer1_correct);
                    answer1.setTextColor(getResources().getColor(R.color.colorText));
                    answer1.setClickable(true);
                    answer2.setText(R.string.question10_answer2);
                    answer2.setTextColor(getResources().getColor(R.color.colorText));
                    answer2.setClickable(true);
                    answer3.setText(R.string.question10_answer3);
                    answer3.setTextColor(getResources().getColor(R.color.colorText));
                    answer3.setClickable(true);
                    clicked++;
                } else if (clicked == 12) {
                    if (!answer1.isChecked() & !answer2.isChecked() & !answer3.isChecked()) {
                        toast_no = 1;
                        toast (toast_message1, toast_no);
                        return;
                    } else if (answer1.isChecked()) {
                        points++;
                        toast_no = 2;
                        toast (toast_message2, toast_no);
                    } else {
                        toast_no = 3;
                        toast(toast_message3, toast_no);
                    }
                    scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            scrollView.fullScroll(View.FOCUS_UP);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }
                    });
                    picture.setImageResource(R.drawable.question10_answer);
                    answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                    answer1.setClickable(false);
                    answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer2.setClickable(false);
                    answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                    answer3.setClickable(false);
                    progress += 10;
                    progressBar.setProgress(progress);
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
        final TextView question = (TextView) findViewById(R.id.tv_rb_question);
        final RadioButton answer1 = (RadioButton) findViewById(R.id.rb_answer_1);
        final RadioButton answer2 = (RadioButton) findViewById(R.id.rb_answer_2);
        final RadioButton answer3 = (RadioButton) findViewById(R.id.rb_answer_3);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.determinateBar);
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
        progressBar.setProgress(progress);
        if (clicked == 1) {
            picture.setImageResource(R.drawable.question4_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer3.setClickable(false);
        } else if (clicked == 2) {
            picture.setImageResource(R.drawable.question5);
            question.setText(R.string.question5);
            answer1.setText(R.string.question5_answer1);
            answer1.setTextColor(getResources().getColor(R.color.colorText));
            answer1.setClickable(true);
            answer2.setText(R.string.question5_answer2_correct);
            answer2.setTextColor(getResources().getColor(R.color.colorText));
            answer2.setClickable(true);
            answer3.setText(R.string.question5_answer3);
            answer3.setTextColor(getResources().getColor(R.color.colorText));
            answer3.setClickable(true);
        } else if (clicked == 3) {
            picture.setImageResource(R.drawable.question5_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer3.setClickable(false);
        } else if (clicked == 4) {
            picture.setImageResource(R.drawable.question6);
            question.setText(R.string.question6);
            answer1.setText(R.string.question6_answer1_correct);
            answer1.setTextColor(getResources().getColor(R.color.colorText));
            answer1.setClickable(true);
            answer2.setText(R.string.question6_answer2);
            answer2.setTextColor(getResources().getColor(R.color.colorText));
            answer2.setClickable(true);
            answer3.setText(R.string.question6_answer3);
            answer3.setTextColor(getResources().getColor(R.color.colorText));
            answer3.setClickable(true);
        } else if (clicked == 5) {
            picture.setImageResource(R.drawable.question6_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer3.setClickable(false);
        } else if (clicked == 6) {
            picture.setImageResource(R.drawable.question7);
            question.setText(R.string.question7);
            answer1.setText(R.string.question7_answer1);
            answer1.setTextColor(getResources().getColor(R.color.colorText));
            answer1.setClickable(true);
            answer2.setText(R.string.question7_answer2_correct);
            answer2.setTextColor(getResources().getColor(R.color.colorText));
            answer2.setClickable(true);
            answer3.setText(R.string.question7_answer3);
            answer3.setTextColor(getResources().getColor(R.color.colorText));
            answer3.setClickable(true);
        } else if (clicked == 7) {
            picture.setImageResource(R.drawable.question7_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer3.setClickable(false);
        } else if (clicked == 8) {
            picture.setImageResource(R.drawable.question8);
            question.setText(R.string.question8);
            answer1.setText(R.string.question8_answer1);
            answer1.setTextColor(getResources().getColor(R.color.colorText));
            answer1.setClickable(true);
            answer2.setText(R.string.question8_answer2);
            answer2.setTextColor(getResources().getColor(R.color.colorText));
            answer2.setClickable(true);
            answer3.setText(R.string.question8_answer3_correct);
            answer3.setTextColor(getResources().getColor(R.color.colorText));
            answer3.setClickable(true);
        } else if (clicked == 9) {
            picture.setImageResource(R.drawable.question8_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer3.setClickable(false);
        } else if (clicked == 10) {
            picture.setImageResource(R.drawable.question9);
            question.setText(R.string.question9);
            answer1.setText(R.string.question9_answer1);
            answer1.setTextColor(getResources().getColor(R.color.colorText));
            answer1.setClickable(true);
            answer2.setText(R.string.question9_answer2);
            answer2.setTextColor(getResources().getColor(R.color.colorText));
            answer2.setClickable(true);
            answer3.setText(R.string.question9_answer3_correct);
            answer3.setTextColor(getResources().getColor(R.color.colorText));
            answer3.setClickable(true);
        } else if (clicked == 11) {
            picture.setImageResource(R.drawable.question9_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer3.setClickable(false);
        } else if (clicked == 12) {
            picture.setImageResource(R.drawable.question10);
            question.setText(R.string.question10);
            answer1.setText(R.string.question10_answer1_correct);
            answer1.setTextColor(getResources().getColor(R.color.colorText));
            answer1.setClickable(true);
            answer2.setText(R.string.question10_answer2);
            answer2.setTextColor(getResources().getColor(R.color.colorText));
            answer2.setClickable(true);
            answer3.setText(R.string.question10_answer3);
            answer3.setTextColor(getResources().getColor(R.color.colorText));
            answer3.setClickable(true);
        } else if (clicked == 13) {
            picture.setImageResource(R.drawable.question10_answer);
            answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            answer1.setClickable(false);
            answer2.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer2.setClickable(false);
            answer3.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
            answer3.setClickable(false);
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
            Intent homeIntent = new Intent(RadiobuttonActivity.this, MainActivity.class);
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

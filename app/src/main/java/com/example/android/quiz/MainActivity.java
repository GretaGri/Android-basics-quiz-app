package com.example.android.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends CustomToast {

    // Saves clicked navigation button count (boolean), points, name and progress in case of changing activity.
    static final String STATE_MESSAGE = "results";
    static final String STATE_SCORE = "score";
    static final String STATE_CLICKED = "clicked";
    static final String STATE_NAME = "name";
    static final String STATE_PROGRESS = "progress";

    // Declare variables.
    int points;
    String message = "";
    int clicked = 0;
    String userName = "";
    String answer1question = "";
    int progress;
    String toast_text;
    int toast_no;
    EditText answer1;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer1 = findViewById(R.id.et_answer); // EditText for name and first question answers
        res = getResources();

        // Apply animation to animeView (arrow).
        ImageView animeView = (ImageView) findViewById(R.id.arrow);
        Animation pulsingArrow = AnimationUtils.loadAnimation(this, R.anim.pulse);
        animeView.startAnimation(pulsingArrow);

        // Set on click listener to navigation button.
        ImageView navigation = (ImageView) findViewById(R.id.navigation);
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == 0) {
                    userName = answer1.getText().toString();
                    if (userName.matches("")) {
                        toast_text = getText(R.string.toastNoName).toString();
                        toast_no = 1; // number to pick image shown on toast (used in CustomToast.java)
                        toast(toast_text, toast_no); // Toast message, if user didn't enter name.
                        return;
                    } else {
                        displayName(userName); // Show users name in Welcome picture.
                        // Add points for first answer.
                    }
                    // Set scrollview that after pushing button the layout top is visible.
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
                    message = getString(R.string.firstQuestionAnswered1);
                    displayMessage(message); // Show reaction to name answer.
                    TextView instructions = findViewById(R.id.et_instructions);
                    instructions.setVisibility(View.INVISIBLE);
                    instructions.setText("");// Set textView et_instructions invisible.
                    TextView question1 = findViewById(R.id.et_question);
                    question1.setText(R.string.question1);
                    answer1.setText("");
                    clicked++;
                } else if (clicked == 1) {
                    answer1question = answer1.getText().toString();
                    if (answer1question.matches("")) {
                        toast_text = res.getString(R.string.toastNoAnswer, userName);
                        toast_no = 1; // number to pick image shown on toast (used in CustomToast.java)
                        toast(toast_text, toast_no); // Toast message, if user didn't enter name.
                        return;
                    } else if (getString(R.string.question1_answer).equalsIgnoreCase(answer1question)) {
                        points++; // Add points for first answer.
                        answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
                        toast_text = res.getString(R.string.toastCorrectAnswer, userName);
                        toast_no = 2;
                        toast(toast_text, toast_no); // Toast message, when the answer is correct.
                    } else {
                        answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
                        toast_text = res.getString(R.string.toastWrongAnswer, userName);
                        toast_no = 3;
                        toast(toast_text, toast_no); // Toast message, when the answer is wrong.
                    }
                    message = "";
                    displayMessage(message);
                    ImageView picture = findViewById(R.id.picture_question1);
                    picture.setImageResource(R.drawable.android);
                    displayName("");
                    progress += 10; // Calculate progress and show it on progress bar.
                    ProgressBar progressBar = (ProgressBar) findViewById(R.id.determinateBar);
                    progressBar.setProgress(progress);
                    clicked++;
                } else if (clicked == 2) {
                    Intent myIntent = new Intent(MainActivity.this, CheckboxActivity.class);
                    myIntent.putExtra("points", points);
                    myIntent.putExtra("name", userName);
                    myIntent.putExtra("progress", progress);
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });
    }

    // Save the user's current app state (remember to put static final strings!).
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_MESSAGE, message);
        savedInstanceState.putInt(STATE_SCORE, points);
        savedInstanceState.putInt(STATE_CLICKED, clicked);
        savedInstanceState.putString(STATE_NAME, userName);
        savedInstanceState.putInt(STATE_PROGRESS, progress);
        super.onSaveInstanceState(savedInstanceState);
    }

    // Always call the superclass so it can restore the view hierarchy.
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance.
        points = savedInstanceState.getInt(STATE_SCORE);
        message = savedInstanceState.getString(STATE_MESSAGE);
        clicked = savedInstanceState.getInt(STATE_CLICKED);
        userName = savedInstanceState.getString(STATE_NAME);
        progress = savedInstanceState.getInt(STATE_PROGRESS);
        if (clicked == 1) {
            //after pushing button go to the screen top.
            final ScrollView scrollView = findViewById(R.id.mainScrollView);
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
            displayName(userName);
            displayMessage(message); // Show reaction to name answer.
            TextView instructions = findViewById(R.id.et_instructions);
            instructions.setVisibility(View.INVISIBLE);
            instructions.setText("");// Set textView et_instructions invisible.
            TextView question1 = findViewById(R.id.et_question);
            question1.setText(R.string.question1);
            answer1.setText("");
        } else if (clicked == 2) {
            //after pushing button go to the screen top.
            final ScrollView scrollView = findViewById(R.id.mainScrollView);
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
            displayMessage(message);//Do not show description.
            ImageView picture = findViewById(R.id.picture_question1);
            picture.setImageResource(R.drawable.android);
            picture.setContentDescription(getText(R.string.android_picture).toString());
            TextView instructions = findViewById(R.id.et_instructions);
            instructions.setVisibility(View.INVISIBLE);
            instructions.setText("");// Set textView et_instructions invisible.
            displayName("");
            TextView question1 = findViewById(R.id.et_question);
            question1.setText(R.string.question1);
            progress += 10;
            ProgressBar progressBar = findViewById(R.id.determinateBar);
            progressBar.setProgress(progress);
            answer1question = answer1.getText().toString();
            if (getString(R.string.question1_answer).equalsIgnoreCase(answer1question)) {
                answer1.setTextColor(getResources().getColor(R.color.colorCorrectAnswer));
            } else answer1.setTextColor(getResources().getColor(R.color.colorWrongAnswer));
        }
    }

    // Method for displaying answer message with points.
    public void displayMessage(String message) {
        TextView description = findViewById(R.id.description);
        description.setText(message);
        description.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_answer));
    }

    // Method for displaying user name in Welcome picture.
    public void displayName(String userName) {
        TextView name = findViewById(R.id.name);
        name.setText(userName);
    }
}

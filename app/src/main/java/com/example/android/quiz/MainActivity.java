package com.example.android.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    static final String STATE_BOOLEAN = "boolean";
    static final String STATE_NAME = "name";
    static final String STATE_PROGRESS = "progress";

    // Declare variables.
    int points;
    String message = "";
    boolean clicked = false;
    String userName = "";
    int progress;
    String toast_text;
    int toast_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Apply animation to animeView (arrow).
        ImageView animeView = (ImageView) findViewById(R.id.arrow);
        Animation pulsingArrow = AnimationUtils.loadAnimation(this, R.anim.pulse);
        animeView.startAnimation(pulsingArrow);

        // Set on click listener to navigation button.
        ImageView navigation = (ImageView) findViewById(R.id.navigation);
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicked) {
                    EditText answer1 = (EditText) findViewById(R.id.et_answer);
                    userName = answer1.getText().toString();
                    if (userName.matches("")) {
                        toast_text = getText(R.string.toastNoName).toString();
                        toast_no = 1;
                        toast(toast_text, toast_no); // Toast message, if user didn't enter name.
                        return;
                    } else {
                        displayName(userName); // Show users name in Welcome picture.
                        points++; // Add points for first answer.
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
                    // Calculate progress and show it on progress bar.
                    progress += 10;
                    ProgressBar progressBar = (ProgressBar) findViewById(R.id.determinateBar);
                    progressBar.setProgress(progress);
                    displayMessage(message); // Show points and answer.
                    TextView instructions = (TextView) findViewById(R.id.et_instructions);
                    instructions.setVisibility(View.INVISIBLE);
                    instructions.setText("");// Set textView et_instructions invisible.
                    CardView question1 = (CardView) findViewById(R.id.cw_question1);
                    question1.setVisibility(View.INVISIBLE); // Set textView cw_question1 invisible.
                    clicked = true;
                } else {
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
        savedInstanceState.putBoolean(STATE_BOOLEAN, clicked);
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
        clicked = savedInstanceState.getBoolean(STATE_BOOLEAN);
        userName = savedInstanceState.getString(STATE_NAME);
        progress = savedInstanceState.getInt(STATE_PROGRESS);
        if (clicked) {
            //after pushing button go to the screen top.
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
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.determinateBar);
            progressBar.setProgress(progress);
            displayName(userName);
            TextView instructions = (TextView) findViewById(R.id.et_instructions);
            instructions.setText("");
            instructions.setVisibility(View.INVISIBLE); // Set textView et_instructions invisible.
            CardView question1 = (CardView) findViewById(R.id.cw_question1);
            question1.setVisibility(View.INVISIBLE); // Set textView cw_question1 invisible.
            displayMessage(message);
        }
    }

    // Method for displaying answer message with points.
    public void displayMessage(String message) {
        TextView description = (TextView) findViewById(R.id.description);
        message = getString(R.string.firstQuestionAnswered1) + points + getString(R.string.firstQuestionAnswered2);
        description.setText(message);
        description.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_answer));
    }

    // Method for displaying user name in Welcome picture.
    public void displayName(String userName) {
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(userName);
    }
}

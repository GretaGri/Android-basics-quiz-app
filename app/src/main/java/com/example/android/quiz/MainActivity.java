package com.example.android.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

int points = 0;
String message = "";
boolean clicked = false;
String studentName = "";

    // Saves the message in case of changing activity
    static final String STATE_MESSAGE = "results";
    static final String STATE_SCORE = "score";
    static final String STATE_BOOLEAN = "boolean";
    static final String STATE_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Apply animation to animeView
        ImageView animeView = (ImageView) findViewById(R.id.arrow);
        Animation pulsingArrow = AnimationUtils.loadAnimation(this, R.anim.pulse);
        animeView.startAnimation(pulsingArrow);

        //Set navigation
        ImageView navigation = (ImageView) findViewById(R.id.navigation);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicked) {
                    EditText answer1 = (EditText) findViewById(R.id.et_answer);
                    studentName = answer1.getText().toString();
                    if (studentName.matches("")) {
                        Toast.makeText(MainActivity.this, R.string.toastNoName, Toast.LENGTH_SHORT).show(); //Toast message, if user didn't enter name.
                        return;
                    } else {
                        displayName(studentName); //show users name in Welcome picture
                        points++; //add points for first answer
                    }
                    displayMessage(message);
                    //answer1.setVisibility(View.INVISIBLE); //set editText et_answer invisible
                    TextView instructions = (TextView) findViewById(R.id.et_instructions);
                    instructions.setVisibility(View.INVISIBLE); //set textView et_instructions invisible
                    //TextView question = (TextView) findViewById(R.id.et_question);
                    //question.setVisibility(View.INVISIBLE); //set textView et_question invisible
                    CardView question1 = (CardView) findViewById(R.id.cw_question1);
                    question1.setVisibility(View.INVISIBLE); //set textView cw_question1 invisible
                    clicked = true;
                }
                else {Intent myIntent = new Intent(MainActivity.this, CheckboxActivity.class);
                        myIntent.putExtra("points", points); //Optional parameters
                        MainActivity.this.startActivity(myIntent);
                     }
            }
        });
    }
    // Save the user's current app state
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_MESSAGE, message);
        savedInstanceState.putInt(STATE_SCORE,points);
        savedInstanceState.putBoolean (STATE_BOOLEAN,clicked);
        savedInstanceState.putString(STATE_NAME, studentName);
        super.onSaveInstanceState(savedInstanceState);
    }

    // Always call the superclass so it can restore the view hierarchy
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        points = savedInstanceState.getInt(STATE_SCORE);
        message = savedInstanceState.getString(STATE_MESSAGE);
        clicked = savedInstanceState.getBoolean(STATE_BOOLEAN);
        studentName = savedInstanceState.getString(STATE_NAME);
        if (clicked) {
            displayName(studentName);
          //  EditText answer1 = (EditText) findViewById(R.id.et_answer);
           // answer1.setVisibility(View.INVISIBLE); //set editText et_answer invisible
            TextView instructions = (TextView) findViewById(R.id.et_instructions);
            instructions.setVisibility(View.INVISIBLE); //set textView et_instructions invisible
           // TextView question = (TextView) findViewById(R.id.et_question);
           // question.setVisibility(View.INVISIBLE); //set textView et_question invisible
            CardView question1 = (CardView) findViewById(R.id.cw_question1);
            question1.setVisibility(View.INVISIBLE); //set textView cw_question1 invisible
            displayMessage(message);
        }

    }
    public void displayMessage (String message) {
        TextView description = (TextView) findViewById(R.id.description);
        message = getString(R.string.firstQuestionAnswered1) + " " + points + " " + getString(R.string.firstQuestionAnswered2);
        description.setText(message);
        description.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_size_answer));
        }

    public void displayName (String studentName) {
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(studentName);
    }
}

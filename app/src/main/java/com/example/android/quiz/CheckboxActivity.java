package com.example.android.quiz;

/**
 * Created by Greta on 2018-01-07.
 */

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class CheckboxActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_checkbox);
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
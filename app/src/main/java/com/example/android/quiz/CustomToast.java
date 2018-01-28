package com.example.android.quiz;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Greta on 2018-01-22.
 */

public class CustomToast extends AppCompatActivity {

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

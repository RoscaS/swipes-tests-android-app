package com.example.swipetests.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.swipetests.R;

public class MainActivity extends AppCompatActivity {

    private final String NONE = "null";

    private TextView current;
    private TextView touchDown;
    private TextView touchUp;
    private TextView length;

    private float x0;
    private float y0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        current = findViewById(R.id.activity_main_position_value);
        touchDown = findViewById(R.id.activity_main_touch_down_value);
        touchUp = findViewById(R.id.activity_main_touch_up_value);
        length = findViewById(R.id.activity_main_touch_length_value);
    }

    private String pointString(MotionEvent event) {
        return "(" + event.getX() + ", " + event.getY() + ")";
    }


    private double computeDistance(MotionEvent event) {
        float x1 = event.getX();
        float y1 = event.getY();
        return Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG", "touched down");
                x0 = event.getX();
                y0 = event.getY();
                touchDown.setText(pointString(event));
                touchUp.setText(NONE);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", "moving: (" + event.getX() + ", " + event.getY() + ")");
                current.setText(pointString(event));
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG", "touched up");
                touchUp.setText(pointString(event));
                current.setText(NONE);
                length.setText(String.format("%f", computeDistance(event)));
                break;
        }

        return super.onTouchEvent(event);
    }

}

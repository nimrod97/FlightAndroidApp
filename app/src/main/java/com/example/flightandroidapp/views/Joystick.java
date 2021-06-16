package com.example.flightandroidapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.flightandroidapp.R;

import java.util.jar.Attributes;

public class Joystick extends AppCompatActivity{

    private double elevator;
    private double ailerone;
    private float dx;
    private float dy;
    private View.OnTouchListener touchListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        touchListener=new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                        dx=view.getX()-motionEvent.getRawX();
                        dy= view.getY()-motionEvent.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.setX(motionEvent.getRawX()+dx);
                        view.setY(motionEvent.getRawY()+dy);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    default:
                        return false;
                }
                return true;
            }

        };
        final View joystick=findViewById(R.id.circle);
        joystick.setOnTouchListener(this.touchListener);

    }

}
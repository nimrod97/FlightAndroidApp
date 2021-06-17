package com.example.flightandroidapp.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.databinding.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Joystick extends View {

    private float x;
    private float y;
    private float defaultX;
    private float defaultY;
    private float dx;
    private float dy;
    private float radius;
    private Paint paint;
    private boolean moveJoystick;
    public OnJoystickChange onChange;


    public Joystick(Context context) {
        super(context);
//        paint = new Paint();
//        paint.setColor(Color.BLUE);
//        this.moveJoystick = false;
//        this.radius = 150;
//        this.defaultX=0;
//        this.defaultY=0;
    }

    public Joystick(Context context, AttributeSet attrs){
        super(context,attrs);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        this.moveJoystick = false;
        this.radius = 150;
        this.defaultX=0;
        this.defaultY=0;
        this.x=defaultX;
        this.y=defaultY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(defaultX==0&&defaultY==0){
            defaultX=getWidth()/2;
            defaultY=getHeight()/2+300;
            backToDefaultAxes();
        }
        canvas.drawCircle(x,y,this.radius,paint);
    }
    private void backToDefaultAxes(){
        this.x=defaultX;
        this.y=defaultY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float newX=event.getX();
        float newY=event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if(isInside(newX,newY))
                    moveJoystick=true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(!moveJoystick)
                    return true;
                dx=newX-x;
                dy=newY-y;
                if(dx>100)
                    dx=100;
                else if(dx<-100)
                    dx=-100;
                if(dy>100)
                    dy=100;
                else if(dy<-100)
                    dy=-100;
                this.x+=dx;
                this.y+=dy;
                onChange=(dx,dy)->{
                  dx=dx/100;
                  dy=dy/100;
                };
                invalidate();

                break;
            case MotionEvent.ACTION_UP:
                moveJoystick=false;
                backToDefaultAxes();
                invalidate();
                break;
            default:
                return false;
        }
        return true;
    }
    private boolean isInside(float xVal,float yVal){
        return Math.sqrt((this.x-xVal)*(this.x-xVal) + (this.y-yVal)*(this.y-yVal))<=this.radius;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }


}
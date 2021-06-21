package com.example.flightandroidapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Joystick extends View {

    private float x;
    private float y;
    private float defaultX;
    private float defaultY;
    private float dx;
    private float dy;
    private float innerRadius;
    private float outerRadius;
    private Paint paint;
    private Paint paint2;
    private Paint paint3;
    private boolean moveJoystick;


    public Joystick(Context context) {
        super(context);
        initializeJoystickAttributes();
    }

    public Joystick(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeJoystickAttributes();
    }

    private void initializeJoystickAttributes() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint2 = new Paint();
        paint2.setColor(Color.GRAY);
        paint2.setStyle(Paint.Style.FILL);
        paint3 = new Paint();
        paint3.setColor(Color.BLACK);
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(15F);
        this.moveJoystick = false;
        this.innerRadius = 100;
        this.outerRadius = 350;
        this.defaultX = 0;
        this.defaultY = 0;
        this.x = defaultX;
        this.y = defaultY;
        this.dx = 0;
        this.dy = 0;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (defaultX == 0 && defaultY == 0) {
            defaultX = getWidth() / 2;
            defaultY = getHeight() / 2;
            backToDefaultAxes();
        }
        canvas.drawCircle(defaultX, defaultY, outerRadius, paint2);
        canvas.drawCircle(defaultX, defaultY, outerRadius, paint3);
        canvas.drawCircle(x, y, this.innerRadius, paint);
    }

    private void backToDefaultAxes() {
        this.x = defaultX;
        this.y = defaultY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float newX = event.getX();
        float newY = event.getY();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (isInside(newX, newY, innerRadius))
                    moveJoystick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!moveJoystick) {
                    dx = 0;
                    dy = 0;
                    return true;
                }
                float boundX = 0;
                float boundY = 0;
                if (!isInside(newX, newY, outerRadius)) {
                    float displacement = findDisplacement(newX, newY);
                    float ratio = outerRadius / displacement;
                    boundX = defaultX + (newX - defaultX) * ratio;
                    boundY = defaultY + (newY - defaultY) * ratio;
                } else {
                    boundX = newX;
                    boundY = newY;
                }
                dx = boundX - defaultX;
                dy = boundY - defaultY;
                this.x = boundX;
                this.y = boundY;
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                moveJoystick = false;
                backToDefaultAxes();
                dx = 0;
                dy = 0;
                invalidate();
                break;
            default:
                return false;
        }
        return true;
    }

    private boolean isInside(float xVal, float yVal, float radius) {
        return findDisplacement(xVal, yVal) <= radius;
    }

    private float findDisplacement(float xVal, float yVal) {
        return (float) Math.sqrt((this.defaultX - xVal) * (this.defaultX - xVal) + (this.defaultY - yVal) * (this.defaultY - yVal));
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public float getOuterRadius() {
        return this.outerRadius;
    }

    public float getInnerRadius() {
        return this.innerRadius;
    }

}
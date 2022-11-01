package com.example.mygraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public class TheGraphics extends View implements View.OnTouchListener, Runnable{

    int xPos=200, yPos= 300;
    int xLast, yLast;
    int radius=100;
    boolean isMoving=false;
    Drawable imgButterfly;
    int xButterfly = 200, yButterfly= 200;
    int widthBut, heightBut;
    int xSpeed=10, ySpeed=10;

    int screenWidth, screenHeight;

    public TheGraphics(Context context) {
        super(context);
        setBackgroundColor(Color.LTGRAY);
        setOnTouchListener(this);
        imgButterfly= getResources().getDrawable(R.drawable.butterfly1, null);
        widthBut=imgButterfly.getMinimumWidth()/10;
        heightBut=imgButterfly.getMinimumHeight()/10;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { //eventhandler
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth= w;
        screenHeight= h;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(xPos,yPos,radius, paint);
        paint.setColor(Color.BLUE);
        paint.setTextSize(60);
        canvas.drawText("Hej fra mig", 300,400,paint);
        imgButterfly.setBounds(xButterfly,yButterfly,xButterfly+widthBut,yButterfly+heightBut);
        imgButterfly.draw(canvas);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int xNew = (int) event.getX();
        int yNew = (int) event.getY();

        //making two pointers (here circle is being bigger and smaller with ctrl button of the keyboard)
        if(event.getPointerCount() == 2)
        {
            int x1 = (int)event.getX(0);
            int x2 = (int)event.getX(1);
            radius= Math.abs(x2- x1) ;
            invalidate();
        }

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //

                if(Math.sqrt((xNew-xPos) *(xNew - xPos) +( yNew -yPos)* (yNew -yPos))<radius)
                {
                    isMoving=true;
                    xLast = xNew;
                    yLast=yNew;
                }
                invalidate();  //calling draw method again.
                break;
            case MotionEvent.ACTION_MOVE:
                //
                if(isMoving) {
                    xPos += xNew - xLast;
                    yPos += yNew - yLast;
                    xLast = xNew;
                    yLast = yNew;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                //
                isMoving =false;
                break;
        }
        return true;

    }


    @Override
    public void run() {

        while(true)
        {
            xButterfly +=xSpeed;
            yButterfly +=ySpeed;
            //test
            if((xButterfly +widthBut)> screenWidth || xButterfly < 1)
            {
                xSpeed= xSpeed * (-1);
            }
            if((yButterfly+ heightBut) > screenHeight || yButterfly <1)
            {
                ySpeed= ySpeed * (-1);
            }
            postInvalidate();
            try{
                Thread.sleep(50);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

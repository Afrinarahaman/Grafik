package com.example.mygraphics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        LinearLayout layout= new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        Button btn=new Button(this);
        btn.setText("OK");

        layout.addView(btn);
        setContentView(layout);*/

        LinearLayout layout= findViewById(R.id.theLayout);
        Button btn=new Button(this);
        btn.setText("OK");
        layout.addView(btn);
        //setContentView(layout);
        //layout.addView(new TheGraphics(this));
        TheGraphics g= new TheGraphics(this);
        Thread t =new Thread(g);
        t.start();
        layout.addView(g);

    }
}
package com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lazylee.apiguidedemo.coretopics.ui_navigation.custom.MyCanvasView;

public class CanvasTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyCanvasView myCanvasView;
        myCanvasView = new MyCanvasView(this);
        myCanvasView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(myCanvasView);
    }
}

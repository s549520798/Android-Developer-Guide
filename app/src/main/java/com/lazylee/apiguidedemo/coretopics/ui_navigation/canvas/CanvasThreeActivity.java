package com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.lazylee.apiguidedemo.coretopics.ui_navigation.custom.ClippedView;

public class CanvasThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ClippedView(this));
    }
}

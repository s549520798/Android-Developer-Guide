package com.lazylee.apiguidedemo.coretopics.ui_navigation;


import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.lazylee.apiguidedemo.R;
import com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas.CanvasOneActivity;
import com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas.CanvasThreeActivity;
import com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas.CanvasTwoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasActivity extends AppCompatActivity {

    private static final String TAG = "CanvasActivity";
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.canvas_one) Button mCanvasOneBtn;
    @BindView(R.id.canvas_two) Button mCanvasTwoBtn;
    @BindView(R.id.canvas_three) Button mCanvasThreeBtn;
    @BindView(R.id.canvas_four) Button mCanvasFourBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @OnClick(R.id.canvas_one)
    public void jumpToCanvasOneActivity(View view) {
        startActivity(new Intent(view.getContext(), CanvasOneActivity.class));
    }

    @OnClick(R.id.canvas_two)
    public void jumpToCanvasTwoActivity(View view) {
        startActivity(new Intent(view.getContext(), CanvasTwoActivity.class));
    }

    @OnClick(R.id.canvas_three)
    public void junpToCanvasThreeActivity(View view) {
        startActivity(new Intent(view.getContext(), CanvasThreeActivity.class));
    }

}

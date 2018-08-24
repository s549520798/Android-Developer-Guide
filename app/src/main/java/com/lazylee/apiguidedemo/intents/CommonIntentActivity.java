package com.lazylee.apiguidedemo.intents;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lazylee.apiguidedemo.R;

public class CommonIntentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CommonIntentActivity";
    public static final int MY_PERMISSIONS_REQUEST_RET_ALARM = 1;
    private Toolbar toolbar;
    private Button btnSetAlarm;
    private Button btnSetTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_intent_activity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(TAG);
        }
        btnSetAlarm = findViewById(R.id.button_alarm);
        btnSetTimer = findViewById(R.id.button_teimer);
        findViewById(R.id.button_show_alarm).setOnClickListener(this);
        btnSetAlarm.setOnClickListener(this);
        btnSetTimer.setOnClickListener(this);
        findViewById(R.id.button_insert).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_alarm:
                createAlarm();
                break;
            case R.id.button_teimer:
                setTimer("测试Intent", 200);
                break;
            case R.id.button_show_alarm:
                showAlarms();
                break;
            case R.id.button_insert:
                break;
        }
    }


    private void createAlarm() {
        Intent intent = new Intent();
        intent.setAction(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "测试用闹钟");
        intent.putExtra(AlarmClock.EXTRA_HOUR, 5);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "没有设置闹钟的应用", Toast.LENGTH_LONG).show();
        }
    }

    private void setTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void showAlarms() {
        Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void insertDate() {

    }
}

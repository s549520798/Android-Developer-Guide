package com.lazylee.apiguidedemo.intents;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lazylee.apiguidedemo.R;

public class CommonIntentActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "CommonIntentActivity";
    public static final int MY_PERMISSIONS_REQUEST_RET_ALARM = 1;
    private Button btnSetAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_intent_activity);
        btnSetAlarm = findViewById(R.id.button_alarm);
        btnSetAlarm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_alarm:

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.SET_ALARM)
                        != PackageManager.PERMISSION_GRANTED) {
                    //请求权限
                    // Should we show an explanation?
                    Log.d(TAG, "onClick: 没有权限，开始请求权限");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.SET_ALARM)) {
                        AlertDialog builder = new AlertDialog.Builder(this)
                                .setTitle("权限提示")
                                .setMessage("需要允许权限，才能使用设置闹钟功能")
                                .setPositiveButton("授权", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(CommonIntentActivity.this,
                                                new String[]{Manifest.permission.SET_ALARM},
                                                MY_PERMISSIONS_REQUEST_RET_ALARM);
                                    }
                                }).setNegativeButton("取消", null)
                                .create();
                        builder.show();
                        // Show an expanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {

                        // No explanation needed, we can request the permission.
                        Log.d(TAG, "onClick: 没有提示用处就开始请求权限");
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.SET_ALARM},
                                MY_PERMISSIONS_REQUEST_RET_ALARM);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    Log.d(TAG, "onClick: 已经有权限，设置闹钟");
                    createAlarm();
                }


                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RET_ALARM:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: 获取到了权限");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    createAlarm();

                } else {
                    Log.d(TAG, "onRequestPermissionsResult: 没有获取到权限");
                    createAlarm();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
            default:
                break;
        }
    }

    private void createAlarm() {
        Intent intent = new Intent();
        intent.setAction(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "测试用闹钟");
        intent.putExtra(AlarmClock.EXTRA_HOUR, 5);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "没有设置闹钟的应用", Toast.LENGTH_LONG).show();
        }
    }
}

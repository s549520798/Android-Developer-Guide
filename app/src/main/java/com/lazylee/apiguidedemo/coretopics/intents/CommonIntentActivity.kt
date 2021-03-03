package com.lazylee.apiguidedemo.coretopics.intents

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.lazylee.apiguidedemo.R

class CommonIntentActivity : AppCompatActivity(), View.OnClickListener {
    private var toolbar: Toolbar? = null
    private var btnSetAlarm: Button? = null
    private var btnSetTimer: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_intent_activity)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle(TAG)
        }
        btnSetAlarm = findViewById(R.id.button_alarm)
        btnSetTimer = findViewById(R.id.button_teimer)
        findViewById<View>(R.id.button_show_alarm).setOnClickListener(this)
        btnSetAlarm?.setOnClickListener(this)
        btnSetTimer?.setOnClickListener(this)
        findViewById<View>(R.id.button_insert).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_alarm -> createAlarm()
            R.id.button_teimer -> setTimer("测试Intent", 200)
            R.id.button_show_alarm -> showAlarms()
            R.id.button_insert -> {
            }
        }
    }

    private fun createAlarm() {
        val intent = Intent()
        intent.action = AlarmClock.ACTION_SET_ALARM
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "测试用闹钟")
        intent.putExtra(AlarmClock.EXTRA_HOUR, 5)
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30)
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, false)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "没有设置闹钟的应用", Toast.LENGTH_LONG).show()
        }
    }

    private fun setTimer(message: String, seconds: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, false)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun showAlarms() {
        val intent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun insertDate() {}

    companion object {
        private const val TAG = "CommonIntentActivity"
        const val MY_PERMISSIONS_REQUEST_RET_ALARM = 1
    }
}
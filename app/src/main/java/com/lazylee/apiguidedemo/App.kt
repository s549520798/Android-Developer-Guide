package com.lazylee.apiguidedemo

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.Instrumentation
import android.app.UiAutomation
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.os.*
import android.view.KeyEvent
import android.view.MotionEvent
import com.lazylee.apiguidedemo.tools.LogUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class App : Application() {

    val mTag = this.javaClass.simpleName

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}
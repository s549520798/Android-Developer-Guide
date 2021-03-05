package com.lazylee.apiguidedemo

import android.app.Application
import android.content.Context
import android.util.Log
import com.lazylee.apiguidedemo.tools.LogUtils
import java.lang.reflect.Method

class App : Application() {

    val mTag = this.javaClass.simpleName

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        var cls = Class.forName("android.app.ActivityThread")
        if (cls.simpleName.equals("ActivityThread")) {
            LogUtils.d(mTag, cls.fields.toString())
            for (method: Method in cls.methods) {
                Log.d(mTag, "Activity'method: $method")
            }
        } else {
            LogUtils.e(mTag, "hook ActivityThread error")
        }

    }
}
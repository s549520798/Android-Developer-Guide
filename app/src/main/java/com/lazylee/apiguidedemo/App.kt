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
        hookInstrumentation()
    }

    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    private fun hookInstrumentation() {
        val cls = Class.forName("android.app.ActivityThread")
        if (cls.simpleName.equals("ActivityThread")) {
            // get activityThread instance
            val activityThreadMethod = cls.getMethod("currentActivityThread")
            activityThreadMethod.isAccessible = true
            val activityThread  = activityThreadMethod.invoke(null)

            // get mInstrumentation
            val instrumentationMethod = cls.getDeclaredMethod("getInstrumentation")
            instrumentationMethod.isAccessible = true
            val instrumentation = instrumentationMethod.invoke(activityThread)
            val mInstrumentationDelegate = getInstrumentationDelegate(instrumentation as Instrumentation)

            // set instrumentation field
            val field = cls.getDeclaredField("mInstrumentation")
            field.isAccessible = true
            field.set(activityThread, mInstrumentationDelegate)
            LogUtils.d(mTag, cls.fields.toString())
        } else {
            LogUtils.e(mTag, "hook ActivityThread error")
        }
    }

    private fun getInstrumentationDelegate(instrumentation: Instrumentation) : Instrumentation {
        val invokeHandler = object : InvocationHandler {
            override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
                LogUtils.d(mTag, "${proxy.toString()} ${method?.name.toString()} , ${args.toString()}")
                return method?.invoke(instrumentation, args);
            }
        }
        val proxy = Proxy.newProxyInstance(instrumentation.javaClass.classLoader, instrumentation.javaClass.interfaces, invokeHandler)
        return proxy as Instrumentation
    }
}
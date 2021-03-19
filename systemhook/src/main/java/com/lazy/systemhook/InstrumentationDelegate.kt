package com.lazy.systemhook

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.RequiresApi

class InstrumentationDelegate(private val instrumentation: Instrumentation) : Instrumentation() {
    val mTag = this.javaClass.simpleName

    override fun newActivity(cl: ClassLoader?, className: String?, intent: Intent?): Activity {
        return instrumentation.newActivity(cl, className, intent)
    }

    override fun invokeContextMenuAction(targetActivity: Activity?, id: Int, flag: Int): Boolean {
        Log.i(mTag, "${targetActivity?.localClassName}  invokeContextMenuAction")
        return instrumentation.invokeContextMenuAction(targetActivity, id, flag)
    }

    override fun callActivityOnCreate(activity: Activity?, icicle: Bundle?) {
        Log.i(mTag, "${activity?.localClassName}  callActivityOnCreate")
        instrumentation.callActivityOnCreate(activity, icicle)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun callActivityOnCreate(activity: Activity?, icicle: Bundle?, persistentState: PersistableBundle?) {
        Log.i(mTag, "${activity?.localClassName}  callActivityOnCreate")
        instrumentation.callActivityOnCreate(activity, icicle, persistentState)
    }

    override fun callActivityOnDestroy(activity: Activity?) {
        Log.i(mTag, "${activity?.localClassName}  callActivityOnDestroy")
        instrumentation.callActivityOnDestroy(activity)
    }

    override fun callActivityOnPostCreate(activity: Activity, savedInstanceState: Bundle?) {
        Log.i(mTag, "${activity.localClassName}  callActivityOnPostCreate")
        instrumentation.callActivityOnPostCreate(activity, savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun callActivityOnPostCreate(activity: Activity, savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Log.i(mTag, "${activity.localClassName}  callActivityOnPostCreate")
        instrumentation.callActivityOnPostCreate(activity, savedInstanceState, persistentState)
    }

    override fun callActivityOnStart(activity: Activity?) {
        Log.i(mTag, "${activity?.localClassName}  callActivityOnStart")
        instrumentation.callActivityOnStart(activity)
    }

    override fun callActivityOnRestart(activity: Activity?) {
        Log.i(mTag, "${activity?.localClassName}  callActivityOnRestart")
        instrumentation.callActivityOnRestart(activity)
    }

    override fun callActivityOnResume(activity: Activity?) {
        Log.i(mTag, "${activity?.localClassName}  callActivityOnResume")
        instrumentation.callActivityOnResume(activity)
    }

    override fun callActivityOnStop(activity: Activity?) {
        Log.i(mTag, "${activity?.localClassName}  callActivityOnStop")
        instrumentation.callActivityOnStop(activity)
    }

    override fun callActivityOnSaveInstanceState(activity: Activity, outState: Bundle) {
        Log.i(mTag, "${activity.localClassName}  callActivityOnSaveInstanceState")
        instrumentation.callActivityOnSaveInstanceState(activity, outState)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun callActivityOnSaveInstanceState(activity: Activity, outState: Bundle, outPersistentState: PersistableBundle) {
        Log.i(mTag, "${activity.localClassName}  callActivityOnSaveInstanceState")
        instrumentation.callActivityOnSaveInstanceState(activity, outState, outPersistentState)
    }

    override fun callActivityOnPause(activity: Activity?) {
        Log.i(mTag, "${activity?.localClassName}  callActivityOnPause")
        instrumentation.callActivityOnPause(activity)
    }
}
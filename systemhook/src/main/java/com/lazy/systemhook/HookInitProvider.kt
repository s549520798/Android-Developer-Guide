package com.lazy.systemhook

import android.annotation.SuppressLint
import android.app.Instrumentation
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

class HookInitProvider : ContentProvider() {

    val mTag = this.javaClass.simpleName

    override fun onCreate(): Boolean {
        Log.i(mTag, "current thread ${Thread.currentThread().name}")
        hookInstrumentation()
        return false
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
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
            Log.d(mTag, cls.fields.toString())
        } else {
            Log.e(mTag, "hook ActivityThread error")
        }
    }

    private fun getInstrumentationDelegate(instrumentation: Instrumentation) : Instrumentation {
        //动态代理只能用于 接口。
//        val invokeHandler = object : InvocationHandler {
//            override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
//                Log.d(mTag, "${proxy.toString()} ${method?.name.toString()} , ${args.toString()}")
//                return method?.invoke(instrumentation, args);
//            }
//        }
//        val proxy = Proxy.newProxyInstance(instrumentation.javaClass.classLoader, instrumentation.javaClass.interfaces, invokeHandler)
        return InstrumentationDelegate(instrumentation)
    }
}
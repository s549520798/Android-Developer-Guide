package com.lazylee.apiguidedemo.coretopics.activities.fragment

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lazylee.apiguidedemo.R

class FragmentListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_list)
        Log.d(TAG, "onCreate: is called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: is called !")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: is called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: is called")
    }

    companion object {
        private const val TAG = "FragmentListActivity"
    }
}
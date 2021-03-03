package com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lazylee.apiguidedemo.coretopics.ui_navigation.custom.ClippedView

class CanvasThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ClippedView(this))
    }
}
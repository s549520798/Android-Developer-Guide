package com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lazylee.apiguidedemo.coretopics.ui_navigation.custom.MyCanvasView

class CanvasTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myCanvasView: MyCanvasView = MyCanvasView(this)
        myCanvasView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(myCanvasView)
    }
}
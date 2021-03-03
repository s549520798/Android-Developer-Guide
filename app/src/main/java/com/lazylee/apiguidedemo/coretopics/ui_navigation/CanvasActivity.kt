package com.lazylee.apiguidedemo.coretopics.ui_navigation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.lazylee.apiguidedemo.R
import com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas.CanvasOneActivity
import com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas.CanvasThreeActivity
import com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas.CanvasTwoActivity

class CanvasActivity : AppCompatActivity() {
    @BindView(R.id.toolbar)
    var mToolbar: Toolbar? = null

    @BindView(R.id.canvas_one)
    var mCanvasOneBtn: Button? = null

    @BindView(R.id.canvas_two)
    var mCanvasTwoBtn: Button? = null

    @BindView(R.id.canvas_three)
    var mCanvasThreeBtn: Button? = null

    @BindView(R.id.canvas_four)
    var mCanvasFourBtn: Button? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas)
        ButterKnife.bind(this)
        setSupportActionBar(mToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    @OnClick(R.id.canvas_one)
    fun jumpToCanvasOneActivity(view: View) {
        startActivity(Intent(view.context, CanvasOneActivity::class.java))
    }

    @OnClick(R.id.canvas_two)
    fun jumpToCanvasTwoActivity(view: View) {
        startActivity(Intent(view.context, CanvasTwoActivity::class.java))
    }

    @OnClick(R.id.canvas_three)
    fun junpToCanvasThreeActivity(view: View) {
        startActivity(Intent(view.context, CanvasThreeActivity::class.java))
    }

    companion object {
        private const val TAG = "CanvasActivity"
    }
}
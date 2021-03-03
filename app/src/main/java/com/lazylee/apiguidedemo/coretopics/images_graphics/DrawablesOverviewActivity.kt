package com.lazylee.apiguidedemo.coretopics.images_graphics

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Gallery
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.lazylee.apiguidedemo.R

class DrawablesOverviewActivity : AppCompatActivity() {
    @BindView(R.id.linear)
    var mLinearLayout: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawables_overview)
        ButterKnife.bind(this)
        // Create a LinearLayout in which to add the ImageView
        mLinearLayout = LinearLayout(this)
        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.android)
        // set the ImageView bounds to match the Drawable's dimensions
        imageView.adjustViewBounds = true
        imageView.layoutParams = Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        mLinearLayout?.addView(imageView)
    }
}
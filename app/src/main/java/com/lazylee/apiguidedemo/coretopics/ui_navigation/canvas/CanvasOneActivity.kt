package com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.lazylee.apiguidedemo.R

class CanvasOneActivity : AppCompatActivity() {
    private var mCanvas: Canvas? = null
    private val mPaint = Paint()
    private val mTextPaint = Paint(Paint.UNDERLINE_TEXT_FLAG)
    private var mBitmap: Bitmap? = null
    private val mRect = Rect()
    private val mBounds = Rect()
    private var mOffset = OFFSET
    private var mColorBackground = 0
    private var mColorRectangle = 0
    private var mColorAccent = 0

    @BindView(R.id.imageView)
    var mImageView: ImageView? = null

    @BindView(R.id.toolbar)
    var mToolBar: Toolbar? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_one)
        ButterKnife.bind(this)
        setSupportActionBar(mToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        init()
    }

    private fun init() {
        mColorBackground = ResourcesCompat.getColor(resources,
                R.color.colorBackground, null)
        mColorRectangle = ResourcesCompat.getColor(resources,
                R.color.colorRectangle, null)
        mColorAccent = ResourcesCompat.getColor(resources,
                R.color.colorAccent, null)
        mPaint.color = mColorBackground
        mTextPaint.color = ResourcesCompat.getColor(resources,
                R.color.colorPrimaryDark, null)
        mTextPaint.textSize = 70f
    }

    @OnClick(R.id.imageView)
    fun imageViewClick(view: View) {
        val vWidth = view.width
        val vHeight = view.height
        val halfWidth = vWidth / 2
        val halfHeight = vHeight / 2
        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888)
            mImageView!!.setImageBitmap(mBitmap)
            mCanvas = Canvas(mBitmap!!)
            mCanvas!!.drawColor(mColorBackground)
            mCanvas!!.drawText(getString(R.string.keep_tapping), 100f, 100f, mTextPaint)
            mOffset += OFFSET
        } else {
            if (mOffset < halfWidth && mOffset < halfHeight) {
                mPaint.color = mColorRectangle - MULTIPLIER * mOffset
                mRect[mOffset, mOffset, vWidth - mOffset] = vHeight - mOffset
                mCanvas!!.drawRect(mRect, mPaint)
                // Increase the indent.
                mOffset += OFFSET
            } else {
                mPaint.color = mColorAccent
                mCanvas!!.drawCircle(halfWidth.toFloat(), halfHeight.toFloat(), (halfWidth / 3).toFloat(), mPaint)
                val text: String = getString(R.string.done)
                // Get bounding box for text to calculate where to draw it.
                mTextPaint.getTextBounds(text, 0, text.length, mBounds)
                // Calculate x and y for text so it's centered.
                val x = halfWidth - mBounds.centerX()
                val y = halfHeight - mBounds.centerY()
                mCanvas!!.drawText(text, x.toFloat(), y.toFloat(), mTextPaint)
            }
        }
        view.invalidate()
    }

    companion object {
        const val OFFSET = 120
        const val MULTIPLIER = 100
    }
}
package com.lazylee.apiguidedemo.coretopics.ui_navigation.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.lazylee.apiguidedemo.R

class MyCanvasView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) : View(context) {
    private val mPaint: Paint
    private val mPath: Path
    private var mExtraCanvas: Canvas? = null
    private var mExtraBitmap: Bitmap? = null
    private var mFrame: Rect? = null
    private val mDrawColor: Int
    private val mBackgroundColor: Int

    //hold the latest x and y values, which are the starting point for the next path
    private var mX = 0f
    private var mY = 0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Create bitmap, create canvas with bitmap, fill canvas with color.
        mExtraBitmap = Bitmap.createBitmap(w, h,
                Bitmap.Config.ARGB_8888)
        mExtraCanvas = Canvas(mExtraBitmap!!)
        // Fill the Bitmap with the background color.
        mExtraCanvas!!.drawColor(mBackgroundColor)

        // Calculate the rect a frame around the picture.
        val inset = 40
        mFrame = Rect(inset, inset, w - inset, h - inset)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the bitmap that stores the path the user has drawn.
        // Initially the user has not drawn anything
        // so we see only the colored bitmap.
        canvas.drawBitmap(mExtraBitmap!!, 0f, 0f, null)
        // Draw a frame around the picture.
        canvas.drawRect(mFrame!!, mPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart(x, y)
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> touchUp()
            else -> {
            }
        }
        return true
    }

    private fun touchStart(x: Float, y: Float) {
        mPath.moveTo(x, y)
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float) {
        val dx = Math.abs(x - mX)
        val dy = Math.abs(y - mY)
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            // Reset mX and mY to the last drawn point.
            mX = x
            mY = y
            // Save the path in the extra bitmap,
            // which we access through its canvas.
            mExtraCanvas!!.drawPath(mPath, mPaint)
        }
    }

    private fun touchUp() {
        // Reset the path so it doesn't get drawn again.
        mPath.reset()
    }

    companion object {
        private const val TOUCH_TOLERANCE = 4f
    }

    init {
        mBackgroundColor = ResourcesCompat.getColor(resources,
                R.color.opaque_orange, null)
        mDrawColor = ResourcesCompat.getColor(resources,
                R.color.opaque_yellow, null)

        // Holds the path we are currently drawing.
        mPath = Path()
        // Set up the paint with which to draw.
        mPaint = Paint()
        mPaint.color = mDrawColor
        // Smoothes out edges of what is drawn without affecting shape.
        mPaint.isAntiAlias = true
        // Dithering affects how colors with higher-precision device
        // than the are down-sampled.
        mPaint.isDither = true
        mPaint.style = Paint.Style.STROKE // default: FILL
        mPaint.strokeJoin = Paint.Join.ROUND // default: MITER
        mPaint.strokeCap = Paint.Cap.ROUND // default: BUTT
        mPaint.strokeWidth = 12f // default: Hairline-width (really thin)
    }
}
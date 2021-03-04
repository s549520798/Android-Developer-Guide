package com.lazylee.apiguidedemo.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import com.lazylee.apiguidedemo.tools.LogUtils

class SimpleFrameLayout : FrameLayout {

    private val mTag: String = this.javaClass.simpleName

    constructor(context: Context) : super(context) {
        LogUtils.d(mTag, "create SimpleTextView context: $context")
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        LogUtils.d(mTag, "create SimpleTextView context: $context, attrs: ${attrs.toString()}")
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LogUtils.d(mTag, "create SimpleTextView context: $context, attrs: ${attrs.toString()}, defStyleAttr: $defStyleAttr")
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        LogUtils.d(mTag, "create SimpleTextView context: $context, attrs: ${attrs.toString()}, defStyleAttr: $defStyleAttr, " +
                "defStyleRes: $defStyleRes")
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        LogUtils.d(mTag, "omMeasure")
        LogUtils.d(mTag, "widthMeasureSpec: $widthMeasureSpec, heightMeasureSpec: $heightMeasureSpec")
        LogUtils.d(mTag, "WidthMeasureSpecMode = ${MeasureSpec.getMode(widthMeasureSpec)}")
        LogUtils.d(mTag, "HeightMeasureSpecMod = ${MeasureSpec.getMode(heightMeasureSpec)}")
        LogUtils.d(mTag, "WidthMeasureSpec value = ${MeasureSpec.getSize(widthMeasureSpec)}")
        LogUtils.d(mTag, "HeightMeasureSpec value = ${MeasureSpec.getSize(heightMeasureSpec)}")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        LogUtils.d(mTag, "onLayout, changed: $changed, left: $left, top: $top, right: $right, bottom: $bottom")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        LogUtils.d(mTag, "onDraw")
        super.onDraw(canvas)
    }
}
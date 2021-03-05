package com.lazylee.apiguidedemo.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.lazylee.apiguidedemo.tools.LogUtils

class SimpleTextView : AppCompatTextView {

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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        LogUtils.d(mTag, "omMeasure")
        LogUtils.d(mTag, "widthMeasureSpec: $widthMeasureSpec, heightMeasureSpec: $heightMeasureSpec")
        LogUtils.d(mTag, "WidthMeasureSpecMode = ${MeasureSpec.getMode(widthMeasureSpec)}")
        LogUtils.d(mTag, "HeightMeasureSpecMod = ${MeasureSpec.getMode(heightMeasureSpec)}")
        LogUtils.d(mTag, "WidthMeasureSpec value = ${MeasureSpec.getSize(widthMeasureSpec)}")
        LogUtils.d(mTag, "HeightMeasureSpec value = ${MeasureSpec.getSize(heightMeasureSpec)}")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        LogUtils.d(mTag, "onDraw")
        super.onDraw(canvas)
    }
}
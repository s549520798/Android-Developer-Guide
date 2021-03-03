package com.lazylee.apiguidedemo.coretopics.ui_navigation.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.lazylee.apiguidedemo.R

class DialView : View {
    private var mSelectCount = 4
    private var mTextPaint //paint for draw text
            : Paint? = null
    private var mDialPaint //paint for draw circle background
            : Paint? = null
    private var mWidth //宽
            = 0f
    private var mHeight //高
            = 0f
    private var mRadius //半径
            = 0f
    private var mActiveSelection //当前选择的挡位
            = 0
    private var mFanOnColor = Color.CYAN
    private var mFanOffColor = Color.GREEN

    // String buffer for dial labels and float for ComputeXY result.
    private val mTempLabel = StringBuffer(8)
    private val mTempResult = FloatArray(2)

    constructor(context: Context?) : super(context) {
        init()
    }

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        if (attrs != null) {
            val typedArray = getContext().obtainStyledAttributes(attrs,
                    R.styleable.DialView,
                    0,
                    0)
            mFanOnColor = typedArray.getColor(R.styleable.DialView_fanOnColor,
                    mFanOnColor)
            mFanOffColor = typedArray.getColor(R.styleable.DialView_fanOffColor,
                    mFanOffColor)
            mSelectCount = typedArray.getInt(R.styleable.DialView_selectCount, DEFAULT_SELECTION_COUNT)
            typedArray.recycle()
        }
        init()
    }

    private fun init() {
        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextPaint!!.color = Color.BLACK
        mTextPaint!!.style = Paint.Style.FILL_AND_STROKE
        mTextPaint!!.textAlign = Paint.Align.CENTER
        mTextPaint!!.textSize = 40f
        mDialPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mDialPaint!!.color = mFanOffColor
        // Initialize current selection.
        mActiveSelection = 0
        // Set up onClick listener for this view.
        setOnClickListener { v: View? ->
            mActiveSelection = (mActiveSelection + 1) % mSelectCount
            if (mActiveSelection >= 1) {
                mDialPaint!!.color = mFanOnColor
            } else {
                mDialPaint!!.color = mFanOffColor
            }
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the dial.
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mDialPaint!!)
        // Draw the text labels.
        val labelRadius = mRadius + 20
        val label = mTempLabel
        for (i in 0 until mSelectCount) {
            val xyData = computeXYForPosition(i, labelRadius, true)
            val x = xyData[0]
            val y = xyData[1]
            label.setLength(0)
            label.append(i)
            canvas.drawText(label, 0, label.length, x, y, mTextPaint!!)
        }
        // Draw the indicator mark.
        val markerRadius = mRadius - 35
        val xyData = computeXYForPosition(mActiveSelection,
                markerRadius, false)
        val x = xyData[0]
        val y = xyData[1]
        canvas.drawCircle(x, y, 20f, mTextPaint!!)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        // Calculate the radius from the width and height.
        mWidth = w.toFloat()
        mHeight = h.toFloat()
        mRadius = (Math.min(mWidth, mHeight) / 2 * 0.8).toFloat()
    }

    private fun computeXYForPosition(pos: Int, radius: Float, isLabel: Boolean): FloatArray {
        val result = mTempResult
        val startAngle: Double
        val angle: Double
        if (mSelectCount > 4) {
            startAngle = Math.PI * (3 / 2.0)
            angle = startAngle + pos * (Math.PI / mSelectCount)
            result[0] = ((radius * Math.cos(angle * 2)).toFloat()
                    + mWidth / 2)
            result[1] = ((radius * Math.sin(angle * 2)).toFloat()
                    + mHeight / 2)
            if (angle > Math.toRadians(360.0) && isLabel) {
                result[1] = result[1] + 20
            }
        } else {
            startAngle = Math.PI * (9 / 8.0)
            angle = startAngle + pos * (Math.PI / mSelectCount)
            result[0] = ((radius * Math.cos(angle)).toFloat()
                    + mWidth / 2)
            result[1] = ((radius * Math.sin(angle)).toFloat()
                    + mHeight / 2)
        }
        return result
    }

    //调用invalidate 重新绘制view
    fun setSelectCount(count: Int){
        mSelectCount = count
        mActiveSelection = 0
        mDialPaint!!.color = mFanOffColor
        invalidate() //调用invalidate 重新绘制view
    }

    companion object {
        private const val DEFAULT_SELECTION_COUNT = 4
    }
}
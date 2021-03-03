package com.lazylee.apiguidedemo.coretopics.ui_navigation.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import com.lazylee.apiguidedemo.R

class CustomEditView : AppCompatEditText {
    private var mClearButtonImage //drawable 对象并没有 setOnTouchListener
            : Drawable? = null

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        mClearButtonImage = ResourcesCompat.getDrawable(resources,
                R.drawable.ic_close_opaque_24dp, null)
        // If the clear (X) button is tapped, clear the text.
        setOnTouchListener { v: View?, event: MotionEvent ->
            val singleClick = performClick()
            if (compoundDrawablesRelative[2] != null) {
                val clearButtonStart: Float // Used for LTR languages
                val clearButtonEnd: Float // Used for RTL languages
                var isClearButtonClicked = false
                // Detect the touch in RTL or LTR layout direction.
                if (layoutDirection == LAYOUT_DIRECTION_RTL) {
                    // If RTL, get the end of the button on the left side.
                    clearButtonEnd = (mClearButtonImage!!.intrinsicWidth.toFloat() + paddingStart).toFloat()
                    // If the touch occurred before the end of the button,
                    // set isClearButtonClicked to true.
                    if (event.x < clearButtonEnd) {
                        isClearButtonClicked = true
                    }
                } else {
                    // LTR
                    // Get the start of the button on the right side.
                    clearButtonStart = (width - paddingEnd
                            - mClearButtonImage!!.intrinsicWidth).toFloat()
                    // If the touch occurred after the start of the button,
                    // set isClearButtonClicked to true.
                    if (event.x > clearButtonStart) {
                        isClearButtonClicked = true
                    }
                }
                // Check for actions if the button is tapped.
                if (isClearButtonClicked && singleClick) {
                    text!!.clear()
                    hideClearButton()
                    return@setOnTouchListener true
                } else if (isClearButtonClicked) {
                    // Check for ACTION_DOWN (always occurs before ACTION_UP).
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        // Switch to the black version of clear button.
                        mClearButtonImage = ResourcesCompat.getDrawable(resources,
                                R.drawable.ic_close_black_24dp, null)
                        showClearButton()
                    }
                    // Check for ACTION_UP.
                    if (event.action == MotionEvent.ACTION_UP) {
                        // Switch to the opaque version of clear button.
                        mClearButtonImage = ResourcesCompat.getDrawable(resources,
                                R.drawable.ic_close_opaque_24dp, null)
                        // Clear the text and hide the clear button.
                        text!!.clear()
                        hideClearButton()
                    }
                    return@setOnTouchListener true
                } else {
                    return@setOnTouchListener false
                }
            } else {
                //没有 clear button 显示，所以不处理touch 事件
                return@setOnTouchListener false
            }
        }

        // If the text changes, show or hide the clear (X) button.
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    hideClearButton()
                } else {
                    showClearButton()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun showClearButton() {
        //将drawables设置在text末尾
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                null,
                mClearButtonImage,
                null)
    }

    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                null,
                null,
                null)
    }
}
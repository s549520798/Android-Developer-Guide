package com.lazylee.apiguidedemo.coretopics.ui_navigation.custom;


import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.lazylee.apiguidedemo.R;


public class CustomEditView extends AppCompatEditText {

    private Drawable mClearButtonImage;    //drawable 对象并没有 setOnTouchListener

    public CustomEditView(Context context) {
        super(context);
        init();
    }

    public CustomEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearButtonImage = ResourcesCompat.getDrawable(getResources(),
                R.drawable.ic_close_opaque_24dp, null);
        // If the clear (X) button is tapped, clear the text.
        setOnTouchListener((v, event) -> {
            boolean singleClick = performClick();
            if ((getCompoundDrawablesRelative()[2] != null)) {
                float clearButtonStart; // Used for LTR languages
                float clearButtonEnd;   // Used for RTL languages
                boolean isClearButtonClicked = false;
                // Detect the touch in RTL or LTR layout direction.
                if (getLayoutDirection() == LAYOUT_DIRECTION_RTL) {
                    // If RTL, get the end of the button on the left side.
                    clearButtonEnd = mClearButtonImage
                            .getIntrinsicWidth() + getPaddingStart();
                    // If the touch occurred before the end of the button,
                    // set isClearButtonClicked to true.
                    if (event.getX() < clearButtonEnd) {
                        isClearButtonClicked = true;
                    }
                } else {
                    // LTR
                    // Get the start of the button on the right side.
                    clearButtonStart = (getWidth() - getPaddingEnd()
                            - mClearButtonImage.getIntrinsicWidth());
                    // If the touch occurred after the start of the button,
                    // set isClearButtonClicked to true.
                    if (event.getX() > clearButtonStart) {
                        isClearButtonClicked = true;
                    }
                }
                // Check for actions if the button is tapped.
                if (isClearButtonClicked && singleClick) {
                    getText().clear();
                    hideClearButton();
                    return true;
                } else if (isClearButtonClicked) {
                    // Check for ACTION_DOWN (always occurs before ACTION_UP).
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        // Switch to the black version of clear button.
                        mClearButtonImage =
                                ResourcesCompat.getDrawable(getResources(),
                                        R.drawable.ic_close_black_24dp, null);
                        showClearButton();
                    }
                    // Check for ACTION_UP.
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        // Switch to the opaque version of clear button.
                        mClearButtonImage =
                                ResourcesCompat.getDrawable(getResources(),
                                        R.drawable.ic_close_opaque_24dp, null);
                        // Clear the text and hide the clear button.
                        getText().clear();
                        hideClearButton();
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                //没有 clear button 显示，所以不处理touch 事件
                return false;
            }


        });

        // If the text changes, show or hide the clear (X) button.
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    hideClearButton();
                } else {
                    showClearButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void showClearButton() {
        //将drawables设置在text末尾
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                null,
                mClearButtonImage,
                null);
    }

    private void hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,
                null,
                null,
                null);
    }


}

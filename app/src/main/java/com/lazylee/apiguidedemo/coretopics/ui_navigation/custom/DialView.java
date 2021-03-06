package com.lazylee.apiguidedemo.coretopics.ui_navigation.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import android.view.View;

import com.lazylee.apiguidedemo.R;

public class DialView extends View {

    private static final int DEFAULT_SELECTION_COUNT = 4;

    private int mSelectCount = 4;
    private Paint mTextPaint;    //paint for draw text
    private Paint mDialPaint;    //paint for draw circle background

    private float mWidth;   //宽
    private float mHeight;  //高
    private float mRadius;  //半径

    private int mActiveSelection; //当前选择的挡位

    private int mFanOnColor = Color.CYAN;
    private int mFanOffColor = Color.GREEN;

    // String buffer for dial labels and float for ComputeXY result.
    private final StringBuffer mTempLabel = new StringBuffer(8);
    private final float[] mTempResult = new float[2];

    public DialView(Context context) {
        super(context);
        init();
    }

    public DialView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DialView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs,
                    R.styleable.DialView,
                    0,
                    0);
            mFanOnColor = typedArray.getColor(R.styleable.DialView_fanOnColor,
                    mFanOnColor);
            mFanOffColor = typedArray.getColor(R.styleable.DialView_fanOffColor,
                    mFanOffColor);
            mSelectCount = typedArray.getInt(R.styleable.DialView_selectCount, DEFAULT_SELECTION_COUNT);
            typedArray.recycle();
        }
        init();

    }


    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(40f);
        mDialPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDialPaint.setColor(mFanOffColor);
        // Initialize current selection.
        mActiveSelection = 0;
        // Set up onClick listener for this view.
        setOnClickListener(v -> {
            mActiveSelection = (mActiveSelection + 1) % mSelectCount;
            if (mActiveSelection >= 1) {
                mDialPaint.setColor(mFanOnColor);
            } else {
                mDialPaint.setColor(mFanOffColor);
            }
            invalidate();
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw the dial.
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mDialPaint);
        // Draw the text labels.
        final float labelRadius = mRadius + 20;
        StringBuffer label = mTempLabel;
        for (int i = 0; i < mSelectCount; i++) {
            float[] xyData = computeXYForPosition(i, labelRadius, true);
            float x = xyData[0];
            float y = xyData[1];
            label.setLength(0);
            label.append(i);
            canvas.drawText(label, 0, label.length(), x, y, mTextPaint);
        }
        // Draw the indicator mark.
        final float markerRadius = mRadius - 35;
        float[] xyData = computeXYForPosition(mActiveSelection,
                markerRadius, false);
        float x = xyData[0];
        float y = xyData[1];
        canvas.drawCircle(x, y, 20, mTextPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // Calculate the radius from the width and height.
        mWidth = w;
        mHeight = h;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
    }

    private float[] computeXYForPosition(final int pos, final float radius, boolean isLabel) {
        float[] result = mTempResult;
        Double startAngle;
        Double angle;
        if (mSelectCount > 4) {
            startAngle = Math.PI * (3 / 2d);
            angle = startAngle + (pos * (Math.PI / mSelectCount));
            result[0] = (float) (radius * Math.cos(angle * 2))
                    + (mWidth / 2);
            result[1] = (float) (radius * Math.sin(angle * 2))
                    + (mHeight / 2);
            if ((angle > Math.toRadians(360)) && isLabel) {
                result[1] += 20;
            }
        } else {
            startAngle = Math.PI * (9 / 8d);
            angle = startAngle + (pos * (Math.PI / mSelectCount));
            result[0] = (float) (radius * Math.cos(angle))
                    + (mWidth / 2);
            result[1] = (float) (radius * Math.sin(angle))
                    + (mHeight / 2);
        }
        return result;

    }

    public int getSelectCount() {
        return mSelectCount;
    }

    public void setSelectCount(int count) {
        this.mSelectCount = count;
        this.mActiveSelection = 0;
        mDialPaint.setColor(mFanOffColor);
        invalidate();       //调用invalidate 重新绘制view
    }


}

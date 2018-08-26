package com.lazylee.apiguidedemo.ui_navigation.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lazylee.apiguidedemo.R;

public class ClippedView extends View {

    private static final String TAG = "ClippedView";

    private Paint mPaint;
    private Path mPath;
    private final RectF mRectF;

    private int mClipRectRight =
            (int) getResources().getDimension(R.dimen.clipRectRight);
    private int mClipRectBottom =
            (int) getResources().getDimension(R.dimen.clipRectBottom);
    private int mClipRectTop =
            (int) getResources().getDimension(R.dimen.clipRectTop);
    private int mClipRectLeft =
            (int) getResources().getDimension(R.dimen.clipRectLeft);
    private int mRectInset =
            (int) getResources().getDimension(R.dimen.rectInset);
    private int mSmallRectOffset =
            (int) getResources().getDimension(R.dimen.smallRectOffset);

    private int mCircleRadius =
            (int) getResources().getDimension(R.dimen.circleRadius);

    private int mTextOffset =
            (int) getResources().getDimension(R.dimen.textOffset);
    private int mTextSize =
            (int) getResources().getDimension(R.dimen.textSize);
    private int mColumnOne = mRectInset;
    private int mColumnnTwo = mColumnOne + mRectInset + mClipRectRight;

    private int mRowOne = mRectInset;
    private int mRowTwo = mRowOne + mRectInset + mClipRectBottom;
    private int mRowThree = mRowTwo + mRectInset + mClipRectBottom;
    private int mRowFour = mRowThree + mRectInset + mClipRectBottom;
    private int mTextRow = mRowFour + (int)(1.5 * mClipRectBottom);


    public ClippedView(Context context) {
        this(context,null);
    }

    public ClippedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        mPaint = new Paint();
        // Smooth out edges of what is drawn without affecting shape.
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(
                (int) getResources().getDimension(R.dimen.strokeWidth));
        mPaint.setTextSize((int) getResources().getDimension(R.dimen.textSize));
        mPath = new Path();

        mRectF = new RectF(new Rect(mRectInset, mRectInset,
                mClipRectRight-mRectInset, mClipRectBottom-mRectInset));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        canvas.save();
        canvas.translate(mColumnOne, mRowOne);
        drawClippedRectangle(canvas);
        canvas.restore();
    }

    private void drawClippedRectangle(Canvas canvas){
        // Set the boundaries of the clipping rectangle for whole picture.
        canvas.clipRect(mClipRectLeft, mClipRectTop,
                mClipRectRight, mClipRectBottom);

        // Fill the canvas with white.
        // With the clipped rectangle, this only draws
        // inside the clipping rectangle.
        // The rest of the surface remains gray.
        canvas.drawColor(Color.WHITE);

        // Change the color to red and
        // draw a line inside the clipping rectangle.
        mPaint.setColor(Color.RED);
        canvas.drawLine(mClipRectLeft, mClipRectTop,
                mClipRectRight, mClipRectBottom, mPaint);

        // Set the color to green and
        // draw a circle inside the clipping rectangle.
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(mCircleRadius, mClipRectBottom - mCircleRadius,
                mCircleRadius, mPaint);

        // Set the color to blue and draw text aligned with the right edge
        // of the clipping rectangle.
        mPaint.setColor(Color.BLUE);
        // Align the RIGHT side of the text with the origin.
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(getContext().getString(R.string.clipping),
                mClipRectRight, mTextOffset, mPaint);
    }

}

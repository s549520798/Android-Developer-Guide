package com.lazylee.apiguidedemo.coretopics.ui_navigation.canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.lazylee.apiguidedemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CanvasOneActivity extends AppCompatActivity {

    public static final int OFFSET = 120;
    public static final int MULTIPLIER = 100;
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mTextPaint = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    private int mOffset = OFFSET;
    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;

    @BindView(R.id.imageView) ImageView mImageView;
    @BindView(R.id.toolbar) Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_one);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        init();
    }

    private void init() {
        mColorBackground = ResourcesCompat.getColor(getResources(),
                R.color.colorBackground, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(),
                R.color.colorRectangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(),
                R.color.colorAccent, null);
        mPaint.setColor(mColorBackground);
        mTextPaint.setColor(
                ResourcesCompat.getColor(getResources(),
                        R.color.colorPrimaryDark, null)
        );
        mTextPaint.setTextSize(70);
    }

    @OnClick(R.id.imageView)
    public void imageViewClick(View view) {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;
        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);
            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mTextPaint);
            mOffset += OFFSET;
        } else {
            if (mOffset < halfWidth && mOffset < halfHeight) {
                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
                mRect.set(
                        mOffset, mOffset, vWidth - mOffset, vHeight - mOffset);
                mCanvas.drawRect(mRect, mPaint);
                // Increase the indent.
                mOffset += OFFSET;
            } else {
                mPaint.setColor(mColorAccent);
                mCanvas.drawCircle(halfWidth, halfHeight, halfWidth / 3, mPaint);
                String text = getString(R.string.done);
                // Get bounding box for text to calculate where to draw it.
                mTextPaint.getTextBounds(text, 0, text.length(), mBounds);
                // Calculate x and y for text so it's centered.
                int x = halfWidth - mBounds.centerX();
                int y = halfHeight - mBounds.centerY();
                mCanvas.drawText(text, x, y, mTextPaint);
            }
        }
        view.invalidate();
    }


}

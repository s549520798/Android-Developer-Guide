package com.lazylee.apiguidedemo.coretopics.ui_navigation.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.lazylee.apiguidedemo.R

class ClippedView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {
    private val mPaint: Paint
    private val mPath: Path
    private val mRectF: RectF
    private val mClipRectRight = resources.getDimension(R.dimen.clipRectRight).toInt()
    private val mClipRectBottom = resources.getDimension(R.dimen.clipRectBottom).toInt()
    private val mClipRectTop = resources.getDimension(R.dimen.clipRectTop).toInt()
    private val mClipRectLeft = resources.getDimension(R.dimen.clipRectLeft).toInt()
    private val mRectInset = resources.getDimension(R.dimen.rectInset).toInt()
    private val mSmallRectOffset = resources.getDimension(R.dimen.smallRectOffset).toInt()
    private val mCircleRadius = resources.getDimension(R.dimen.circleRadius).toInt()
    private val mTextOffset = resources.getDimension(R.dimen.textOffset).toInt()
    private val mTextSize = resources.getDimension(R.dimen.textSize).toInt()
    private val mColumnOne = mRectInset
    private val mColumnnTwo = mColumnOne + mRectInset + mClipRectRight
    private val mRowOne = mRectInset
    private val mRowTwo = mRowOne + mRectInset + mClipRectBottom
    private val mRowThree = mRowTwo + mRectInset + mClipRectBottom
    private val mRowFour = mRowThree + mRectInset + mClipRectBottom
    private val mTextRow = mRowFour + (1.5 * mClipRectBottom).toInt()
    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.GRAY)
        canvas.save()
        canvas.translate(mColumnOne.toFloat(), mRowOne.toFloat())
        drawClippedRectangle(canvas)
        canvas.restore()
        // 画第二个 长方形
        // Draw a rectangle that uses the difference between two
        // clipping rectangles to create a picture frame effect.
        canvas.save()
        // Move the origin to the right for the next rectangle.
        canvas.translate(mColumnnTwo.toFloat(), mRowOne.toFloat())
        // Use the subtraction of two clipping rectangles to create a frame.
        canvas.clipRect(2 * mRectInset, 2 * mRectInset,
                mClipRectRight - 2 * mRectInset, mClipRectBottom - 2 * mRectInset)
        canvas.clipRect((4 * mRectInset).toFloat(), (4 * mRectInset).toFloat(), (
                mClipRectRight - 4 * mRectInset).toFloat(), (mClipRectBottom - 4 * mRectInset).toFloat(),
                Region.Op.DIFFERENCE)
        drawClippedRectangle(canvas)
        canvas.restore()
        // 第三个
        // Draw a rectangle that uses a circular clipping region
        // created from a circular path.
        canvas.save()
        canvas.translate(mColumnOne.toFloat(), mRowTwo.toFloat())
        // Clears any lines and curves from the path but unlike reset(),
        // keeps the internal data structure for faster reuse.
        mPath.rewind()
        mPath.addCircle(mCircleRadius.toFloat(), (mClipRectBottom - mCircleRadius).toFloat(),
                mCircleRadius.toFloat(), Path.Direction.CCW)
        canvas.clipPath(mPath, Region.Op.DIFFERENCE)
        drawClippedRectangle(canvas)
        canvas.restore()
        //第四个
        // Use the intersection of two rectangles as the clipping region.
        canvas.save()
        canvas.translate(mColumnnTwo.toFloat(), mRowTwo.toFloat())
        canvas.clipRect(mClipRectLeft, mClipRectTop,
                mClipRectRight - mSmallRectOffset,
                mClipRectBottom - mSmallRectOffset)
        canvas.clipRect((mClipRectLeft + mSmallRectOffset).toFloat(), (
                mClipRectTop + mSmallRectOffset).toFloat(),
                mClipRectRight.toFloat(), mClipRectBottom.toFloat(), Region.Op.INTERSECT)
        drawClippedRectangle(canvas)
        canvas.restore()
        //第五个
        // You can combine shapes and draw any path to define a clipping region.
        canvas.save()
        canvas.translate(mColumnOne.toFloat(), mRowThree.toFloat())
        mPath.rewind()
        mPath.addCircle((mClipRectLeft + mRectInset + mCircleRadius).toFloat(), (
                mClipRectTop + mCircleRadius + mRectInset).toFloat(),
                mCircleRadius.toFloat(), Path.Direction.CCW)
        mPath.addRect((mClipRectRight / 2 - mCircleRadius).toFloat(), (
                mClipRectTop + mCircleRadius + mRectInset).toFloat(), (
                mClipRectRight / 2 + mCircleRadius).toFloat(), (
                mClipRectBottom - mRectInset).toFloat(), Path.Direction.CCW)
        canvas.clipPath(mPath)
        drawClippedRectangle(canvas)
        canvas.restore()
        //第六个
        // Use a rounded rectangle. Use mClipRectRight/4 to draw a circle.
        canvas.save()
        canvas.translate(mColumnnTwo.toFloat(), mRowThree.toFloat())
        mPath.rewind()
        mPath.addRoundRect(mRectF, mClipRectRight.toFloat() / 4,
                mClipRectRight.toFloat() / 4, Path.Direction.CCW)
        canvas.clipPath(mPath)
        drawClippedRectangle(canvas)
        canvas.restore()

        // Clip the outside around the rectangle.
        canvas.save()
        // Move the origin to the right for the next rectangle.
        canvas.translate(mColumnOne.toFloat(), mRowFour.toFloat())
        canvas.clipRect(2 * mRectInset, 2 * mRectInset,
                mClipRectRight - 2 * mRectInset,
                mClipRectBottom - 2 * mRectInset)
        drawClippedRectangle(canvas)
        canvas.restore()

        // Draw text with a translate transformation applied.
        canvas.save()
        mPaint.color = Color.CYAN
        // Align the RIGHT side of the text with the origin.
        mPaint.textAlign = Paint.Align.LEFT
        // Apply transformation to canvas.
        canvas.translate(mColumnnTwo.toFloat(), mTextRow.toFloat())
        // Draw text.
        canvas.drawText(
                context.getString(R.string.translated), 0f, 0f, mPaint)
        canvas.restore()

        // Draw text with a translate and skew transformations applied.
        canvas.save()
        mPaint.textSize = mTextSize.toFloat()
        mPaint.textAlign = Paint.Align.RIGHT
        // Position text.
        canvas.translate(mColumnnTwo.toFloat(), mTextRow.toFloat())
        // Apply skew transformation.
        canvas.skew(0.2f, 0.3f)
        canvas.drawText(
                context.getString(R.string.skewed), 0f, 0f, mPaint)
        canvas.restore()
    }

    private fun drawClippedRectangle(canvas: Canvas) {
        // Set the boundaries of the clipping rectangle for whole picture.
        canvas.clipRect(mClipRectLeft, mClipRectTop,
                mClipRectRight, mClipRectBottom)

        // Fill the canvas with white.
        // With the clipped rectangle, this only draws
        // inside the clipping rectangle.
        // The rest of the surface remains gray.
        canvas.drawColor(Color.WHITE)

        // Change the color to red and
        // draw a line inside the clipping rectangle.
        mPaint.color = Color.RED
        canvas.drawLine(mClipRectLeft.toFloat(), mClipRectTop.toFloat(),
                mClipRectRight.toFloat(), mClipRectBottom.toFloat(), mPaint)

        // Set the color to green and
        // draw a circle inside the clipping rectangle.
        mPaint.color = Color.GREEN
        canvas.drawCircle(mCircleRadius.toFloat(), (mClipRectBottom - mCircleRadius).toFloat(),
                mCircleRadius.toFloat(), mPaint)

        // Set the color to blue and draw text aligned with the right edge
        // of the clipping rectangle.
        mPaint.color = Color.BLUE
        // Align the RIGHT side of the text with the origin.
        mPaint.textAlign = Paint.Align.RIGHT
        canvas.drawText(context.getString(R.string.clipping),
                mClipRectRight.toFloat(), mTextOffset.toFloat(), mPaint)
    }

    companion object {
        private const val TAG = "ClippedView"
    }

    init {
        isFocusable = true
        mPaint = Paint()
        // Smooth out edges of what is drawn without affecting shape.
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        mPaint.textSize = resources.getDimension(R.dimen.textSize)
        mPath = Path()
        mRectF = RectF(Rect(mRectInset, mRectInset,
                mClipRectRight - mRectInset, mClipRectBottom - mRectInset))
    }
}
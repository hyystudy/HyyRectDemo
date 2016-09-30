package com.example.sks.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by sks on 2016/9/30.
 */
public class PartlyRoundRect extends FrameLayout {

    private Rect mBoundRect = new Rect();
    private Path mPath = new Path();
    private RectF mTopLeftArcBound = new RectF();
    private RectF mTopRightArcBound = new RectF();
    private RectF mBottomLeftArcBound = new RectF();
    private RectF mBottomRightArcBound = new RectF();
    private int mArcRadius = 27;
    private Paint mRectPaint;

    public PartlyRoundRect(Context context) {
        this(context, null);
    }

    public PartlyRoundRect(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public PartlyRoundRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PartlyRoundRect);


        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setColor(typedArray.getColor(R.styleable.PartlyRoundRect_rect_color, Color.TRANSPARENT));

        mArcRadius = (int) typedArray.getDimension(R.styleable.PartlyRoundRect_radius, 18);

        setBackgroundColor(Color.TRANSPARENT);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mBoundRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        mTopRightArcBound.set(mBoundRect.right - mArcRadius * 2, mBoundRect.top, mBoundRect.right, mBoundRect.top + mArcRadius * 2);
        mTopLeftArcBound.set(mBoundRect.left, mBoundRect.top, mBoundRect.left + mArcRadius * 2, mBoundRect.top + mArcRadius * 2);
        mBottomRightArcBound.set(mBoundRect.right - mArcRadius * 2, mBoundRect.bottom - mArcRadius * 2, mBoundRect.right, mBoundRect.bottom);
        mBottomLeftArcBound.set(mBoundRect.left, mBoundRect.bottom - mArcRadius * 2, mBoundRect.left + mArcRadius * 2, mBoundRect.bottom);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        //mPath.reset();
        mPath.moveTo(mBoundRect.left + mArcRadius, mBoundRect.top);
        //draw top horizontal line
        mPath.lineTo(mBoundRect.right - mArcRadius, mBoundRect.top);

        //draw top-right corner
        mPath.arcTo(mTopRightArcBound, -90, 90);

        //draw right vertical line
        mPath.lineTo(mBoundRect.right, mBoundRect.bottom );

        //draw bottom-right corner
        //mPath.arcTo(mBottomRightArcBound, 0, 90);

        //draw bottom horizontal line
        mPath.lineTo(mBoundRect.left - mArcRadius, mBoundRect.bottom);

        //draw bottom-left corner
        //mPath.arcTo(mBottomLeftArcBound, 90, 90);

        //draw left vertical line
        mPath.lineTo(mBoundRect.left, mBoundRect.top + mArcRadius);

        //draw top-left corner
        mPath.arcTo(mTopLeftArcBound, 180, 90);

        mPath.close();

        mRectPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath, mRectPaint);
        //super.onDraw(canvas);
    }

}

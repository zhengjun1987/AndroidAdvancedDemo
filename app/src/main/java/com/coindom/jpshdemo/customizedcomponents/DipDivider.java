package com.coindom.jpshdemo.customizedcomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.coindom.jpshdemo.R;
import com.socks.library.KLog;

public class DipDivider extends View {
    private static final String TAG = "DipDivider";

    private Paint mPaint;
    private int bgColor;
    private Rect skyRect;
    private Paint skyPaint;
    private Path path;

    public DipDivider(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setColor(bgColor);
        skyRect = new Rect();
        skyPaint = new Paint();
        skyPaint.setStyle(Paint.Style.FILL);
        skyPaint.setColor(Color.BLUE);
        // 初始化 Path 对象
        path = new Path();
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(400, 542);
    }

    public DipDivider(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DipDivider);
        bgColor = typedArray.getColor(R.styleable.DipDivider_bgColor, Color.GRAY);
        typedArray.recycle();
        init();
    }

    public DipDivider(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DipDivider(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        KLog.d(TAG, "changed = [" + changed + "], left = [" + left + "], top = [" + top + "], right = [" + right + "], bottom = [" + bottom + "]");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        int defaultSizeWidth = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec);
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        int defaultSizeHeight = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec);
        setMeasuredDimension(defaultSizeWidth, defaultSizeHeight);
        KLog.d(TAG, "widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
        KLog.d(TAG, "MeasureSpec.getMode(widthMeasureSpec) = " + (MeasureSpec.getMode(widthMeasureSpec) >> 30));
        KLog.d(TAG, "MeasureSpec.getSize(widthMeasureSpec) = " + MeasureSpec.getSize(widthMeasureSpec));
        KLog.d(TAG, "MeasureSpec.getMode(heightMeasureSpec) = " + (MeasureSpec.getMode(heightMeasureSpec) >> 30));
        KLog.d(TAG, "MeasureSpec.getSize(heightMeasureSpec) = " + MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        KLog.d(TAG, "canvas = [" + canvas + "]");
        super.onDraw(canvas);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        KLog.d(TAG, "measuredWidth = " + measuredWidth);
        KLog.d(TAG, "measuredHeight = " + measuredHeight);
        KLog.d(TAG, "getHeight() = " + getHeight());
        KLog.d(TAG, "getWidth() = " + getWidth());
        canvas.drawColor(Color.RED);
        skyRect.set(measuredWidth / 2, 0, measuredWidth, measuredHeight / 2);
        canvas.drawRect(skyRect, skyPaint);
        float cycleX = measuredWidth * 3 / 4.0f;
        float cycleY = measuredHeight / 4.0f;
        float radius = Math.min(measuredWidth, measuredHeight) / 8.0f;
//        canvas.drawArc(new RectF(skyRect), 60, 60, true, mPaint);
        canvas.drawCircle(cycleX, cycleY, radius, mPaint);

//        path.addCircle(200,200,180, Path.Direction.CW);
//        path.addCircle(200,380,180, Path.Direction.CCW);
        mPaint.reset();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20);
        path.moveTo(200,200);
        path.rQuadTo(100,100,100,-100);

        canvas.drawPath(path, mPaint); // 绘制出 path 描述的图形（心形），大功告成
//        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
//        canvas.drawLines(points, mPaint);
    }

    @Override
    public void invalidateOutline() {
        super.invalidateOutline();
    }
}

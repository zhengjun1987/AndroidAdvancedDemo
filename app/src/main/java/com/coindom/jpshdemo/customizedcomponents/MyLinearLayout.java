package com.coindom.jpshdemo.customizedcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.socks.library.KLog;

public class MyLinearLayout extends LinearLayout {
    private static final String TAG = "MyLinearLayout";
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        KLog.d(TAG, "widthMeasureSpec = [" + widthMeasureSpec + "], heightMeasureSpec = [" + heightMeasureSpec + "]");
        KLog.d(TAG, "MeasureSpec.getMode(widthMeasureSpec) = " + (MeasureSpec.getMode(widthMeasureSpec) >> 30));
        KLog.d(TAG, "MeasureSpec.getSize(widthMeasureSpec) = " + MeasureSpec.getSize(widthMeasureSpec));
        KLog.d(TAG, "MeasureSpec.getMode(heightMeasureSpec) = " + (MeasureSpec.getMode(heightMeasureSpec) >> 30));
        KLog.d(TAG, "MeasureSpec.getSize(heightMeasureSpec) = " + MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        KLog.d(TAG, "changed = [" + changed + "], l = [" + l + "], t = [" + t + "], r = [" + r + "], b = [" + b + "]");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        KLog.d(TAG, "canvas = [" + canvas + "]");
    }
}

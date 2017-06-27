package com.jiae.herbs.baselib.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jiae.herbs.baselib.R;

/**
 * Created by chenshouyin on 17/3/9.
 */

@SuppressLint("AppCompatCustomView")
public class MiddleHorizontalLineView extends TextView {

    //获取atts里面定义的属性
    private String mTextViewString;
    private int mTextViewColor;
    private int mTextViewSize;

    //设置画笔和画布大小
    private Paint mTextViewPaint;
    private Rect mTextViewBound;

    public MiddleHorizontalLineView(Context context) {
        this(context, null);
    }

    public MiddleHorizontalLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MiddleHorizontalLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MiddleHorizontalLineView, defStyleAttr, 0);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int atts = array.getIndex(i);
            if (atts == R.styleable.MiddleHorizontalLineView_titleText) {
                mTextViewString = array.getString(atts);//注意是getString(i)

            } else if (atts == R.styleable.MiddleHorizontalLineView_titleSize) {
                mTextViewSize = (int) array.getDimension(atts, mTextViewSize);//默认16sp

            } else if (atts == R.styleable.MiddleHorizontalLineView_titleColor) {
                mTextViewColor = array.getInteger(atts, Color.BLACK); //默认颜色设置为黑色

            }
        }

        array.recycle();//回收

        // 设置画笔属性
        mTextViewPaint = new Paint();
        mTextViewPaint.setStrokeWidth(5);
        mTextViewPaint.setAntiAlias(true);
        mTextViewPaint.setTextSize(mTextViewSize);
        // 设置画布的宽高
        mTextViewBound = new Rect();
        mTextViewPaint.getTextBounds(mTextViewString, 0, mTextViewString.length(), mTextViewBound);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int myWidth, myHeight;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        // 宽度
        if (widthMode == MeasureSpec.EXACTLY) {
            myWidth = widthMeasureSpec;
        } else {
            mTextViewPaint.setTextSize(mTextViewSize);
            mTextViewPaint.getTextBounds(mTextViewString, 0, mTextViewString.length(), mTextViewBound);
            float textWidth = mTextViewBound.width();
            myWidth = (int) (textWidth + getPaddingLeft() + getPaddingRight());
        }

        //高度
        if (heightMode == MeasureSpec.EXACTLY) {
            myHeight = heightMeasureSpec;
        } else {
            mTextViewPaint.setTextSize(mTextViewSize);
            mTextViewPaint.getTextBounds(mTextViewString, 0, mTextViewString.length(), mTextViewBound);
            float textHeight = mTextViewBound.height();
            myHeight = (int) (textHeight + getPaddingTop() + getPaddingBottom());
        }

        setMeasuredDimension(myWidth, myHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制文字位置,颜色
        mTextViewPaint.setColor(mTextViewColor);

        Paint.FontMetricsInt fontMetrics = mTextViewPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText(mTextViewString, getMeasuredWidth() / 2 - mTextViewBound.width() / 2, baseline, mTextViewPaint);
        canvas.drawLine(0,
                (mTextViewBound.height() + getPaddingTop() + getPaddingBottom()) / 2,
                (mTextViewBound.width() + getPaddingLeft() + getPaddingRight()),
                (mTextViewBound.height() + getPaddingTop() + getPaddingBottom()) / 2,
                mTextViewPaint);
    }


}

package com.jiae.herbs.baselib.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.jiae.herbs.baselib.R;

/**
 * 标题：可自定义下划线
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/7 18:10.
 */
public class LineEditText extends AppCompatEditText {

    private final Paint mBottomLinePaint = new Paint();
    private static final int DEFAULT_LINE_HEIGHT = 0;
    private static final int DEFAULT_LINE_COLOR = -1;
    private ColorStateList mLineColor;
    private int mLineHeight;
    private int mLineCurrentColor;

    public LineEditText(Context context) {
        this(context, null);
    }

    public LineEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public LineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LineEditText, defStyleAttr, 0);
        mLineHeight = a.getDimensionPixelSize(R.styleable.LineEditText_lineHeight, DEFAULT_LINE_HEIGHT);
        mLineColor = a.getColorStateList(R.styleable.LineEditText_lineColor);
        if (mLineColor == null)
            mLineColor = ColorStateList.valueOf(DEFAULT_LINE_COLOR);
        mLineCurrentColor = mLineColor.getDefaultColor();
        a.recycle();
        setup();
    }

    public void setLineHeight(int heightPx) {
        mLineHeight = heightPx;
        invalidate();
    }

    public void setLineColor(int color) {
        mLineColor = ColorStateList.valueOf(color);
        invalidate();
    }

    public void setLineColor(ColorStateList color) {
        mLineColor = color;
        invalidate();
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int color = mLineColor.getColorForState(getDrawableState(), 0);
        if (color != mLineCurrentColor) {
            mLineCurrentColor = color;
            mBottomLinePaint.setColor(color);
            invalidate();
        }
    }

    private void setup() {
        setBackgroundDrawable(null);
        mBottomLinePaint.setAntiAlias(true);
        mBottomLinePaint.setColor(mLineCurrentColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight() - mLineHeight,
                getWidth(), getHeight() - mLineHeight, mBottomLinePaint);
    }
}

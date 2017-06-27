package com.jiae.herbs.baselib.mvp.progress;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 加载动画
 * Created by huang on 2017/1/17.
 */
public abstract class LoadingProgress extends FrameLayout {

    private AnimationDrawable mAnimDrawable;

    public LoadingProgress(Context context) {
        this(context, null);
    }

    public LoadingProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mAnimDrawable = setupAnimDrawable();
        if (mAnimDrawable == null) {
            return;
        }

        ImageView progress = new ImageView(context, attrs);
        progress.setImageResource(getDefImage());
        progress.setBackgroundDrawable(mAnimDrawable);
        progress.setContentDescription(null);
        addView(progress, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        setClickable(true);
        setVisibility(GONE);
    }

    public void show() {
        if (mAnimDrawable != null) {
            mAnimDrawable.start();
        }
        setVisibility(VISIBLE);
    }

    public void dismiss() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_out));
        if (mAnimDrawable != null) {
            mAnimDrawable.stop();
        }
        setVisibility(GONE);
    }

    protected abstract AnimationDrawable setupAnimDrawable();

    protected abstract int getDefImage();
}


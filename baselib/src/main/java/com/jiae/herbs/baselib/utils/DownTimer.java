package com.jiae.herbs.baselib.utils;

import android.os.CountDownTimer;

/**
 * 标题：时间计时器
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/7 15:56.
 */
public class DownTimer extends CountDownTimer {

    private OnTimerListener listener;

    public DownTimer(long millisInFuture, long countDownInterval, OnTimerListener listener) {
        super(millisInFuture, countDownInterval);
        this.listener = listener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (listener != null)
            listener.onTick(millisUntilFinished);
    }

    @Override
    public void onFinish() {
        if (listener != null)
            listener.onFinish();
    }

    public interface OnTimerListener {

        void onTick(long millisUntilFinished);

        void onFinish();
    }
}

package com.jiae.herbs.baselib.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题：时间触发器
 * 每隔一段时间出发一次
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/7 16:16.
 */
public class TimeTrigger {

    private final long mCountdownInterval;

    private final boolean mFirstTrigger;

    private boolean mCancelled = false;

    private static final int MSG = 1;

    private List<OnTriggerListener> triggerListeners = new ArrayList<>();

    public TimeTrigger(long countDownInterval, boolean firstTrigger) {
        mCountdownInterval = countDownInterval;
        mFirstTrigger = firstTrigger;
    }

    public void addOnTriggerListeners(OnTriggerListener listener) {
        if (listener != null)
            triggerListeners.add(listener);
    }

    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(MSG);
    }

    public synchronized final TimeTrigger start() {
        mCancelled = false;
        if (mFirstTrigger) {
            onTrigger();
        }
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG), mCountdownInterval);
        return this;
    }

    private void onTrigger() {
        for (OnTriggerListener listener : triggerListeners) {
            listener.onTimeTrigger();
        }
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            synchronized (TimeTrigger.this) {
                if (mCancelled) {
                    return;
                }
                if (triggerListeners.size() == 0)
                    return;
                // 获取出发前的时间
                long lastTickStart = SystemClock.elapsedRealtime();

                onTrigger();

                // 触发时间间隔 - onTrigger()方法的耗时
                // 这个方式为了保证每次调用onTrigger的时间间隔为 mCountdownInterval
                long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                //如果超过 mCountdownInterval触发时间间隔 的话，延后一个时间间隔
                while (delay < 0) delay += mCountdownInterval;

                sendMessageDelayed(obtainMessage(MSG), delay);
            }
        }
    };

    public interface OnTriggerListener {
        void onTimeTrigger();
    }

}

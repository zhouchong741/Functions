package com.jiae.herbs.baselib.view.recyclerhelper.touchhelper;

import android.content.Context;

import com.jiae.herbs.baselib.view.recyclerhelper.touchhelper.swipemenu.SwipeMenuHelper;
import com.jiae.herbs.baselib.view.recyclerhelper.touchhelper.widget.SwipeLog;

import java.lang.ref.WeakReference;

/**
 * 提供对RecyclerView的个性化设置
 * Created by huang on 2017/4/28.
 */
public class RecyclerHelper {

    private final WeakReference<Context> context;
    private SwipeMenuHelper mSwipeMenuHelper;
    private static RecyclerHelper helper;

    /**
     * 获取Helper对象
     *
     * @param context 上下文对象，并作为标记使用
     * @return 如果传入context和本类context相同，返回静态实例,否则创建新的实例
     */
    public static RecyclerHelper get(Context context) {
        if (helper != null && helper.context.get() == context) {
            return helper;
        } else {
            helper = new RecyclerHelper(context);
            return helper;
        }
    }

    private RecyclerHelper(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    public SwipeMenuHelper getSwipeMenuHelper() {
        if (mSwipeMenuHelper == null) {
            mSwipeMenuHelper = new SwipeMenuHelper(context);
        }
        return mSwipeMenuHelper;
    }

    /**
     * 设置调试模式
     *
     * @param debug 是否调试
     */
    public RecyclerHelper setDebug(boolean debug) {
        SwipeLog.setDebug(debug);
        return this;
    }

}

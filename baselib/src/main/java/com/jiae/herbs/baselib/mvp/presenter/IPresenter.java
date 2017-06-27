package com.jiae.herbs.baselib.mvp.presenter;

import android.app.Activity;

import com.jiae.herbs.baselib.mvp.view.BaseView;

/**
 * 页面数据处理逻辑
 * Created by huang on 2017/2/7.
 */

public interface IPresenter {

    /**
     * Presenter被初始化时调用
     *
     * @param view Activity对象
     */
    void attachView(BaseView view);

    /**
     * Activity被销毁时调用
     * {@link Activity#onDestroy()}
     */
    void detachView();
}

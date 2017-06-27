package com.jiae.herbs.baselib.mvp.view;


import android.content.Context;

import com.jiae.herbs.baselib.mvp.presenter.MvpPresenter;
import com.jiae.herbs.baselib.utils.http.NetWorkCallback;

/**
 * Activity接口
 * Created by huang on 2017/2/7.
 */
public interface BaseView<P extends MvpPresenter> extends NetWorkCallback<String>{

    /**
     * 返回上下文
     */
    Context getContext();
    /**
     * 返回布局
     */
    int getLayoutId();
    /**
     * 初始化视图
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化监听
     */
    void initListener();

    /**
     * 创建Presenter
     *
     * @return IPresenter实现类
     */
    P newPresenter();

    /**
     * 打开加载动画
     */
    void openLoadingAnim();

    /**
     * 关闭加载动画
     */
    void closeLoadingAnim();

    /**
     * 打开进度条
     */
    void showProgressDialog(String msg, boolean cancelable);

    /**
     * 关闭进度条
     */
    void dismissProgressDialog();
}

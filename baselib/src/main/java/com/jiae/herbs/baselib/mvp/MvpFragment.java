package com.jiae.herbs.baselib.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jiae.herbs.baselib.R;
import com.jiae.herbs.baselib.mvp.presenter.MvpPresenter;
import com.jiae.herbs.baselib.mvp.progress.LoadingProgress;
import com.jiae.herbs.baselib.mvp.progress.TemplateHelper;
import com.jiae.herbs.baselib.mvp.view.BaseView;

/**
 * 标题：Mvp模式View模板Fragment基类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/15 15:45.
 */
public abstract class MvpFragment<P extends MvpPresenter> extends Fragment implements BaseView<P> {

    private P presenter;
    protected Context mContext;
    protected TemplateHelper templateHelper;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout fragContainer = new FrameLayout(mContext);
        setupTemplate(fragContainer);
        View content = inflater.inflate(getLayoutId(), fragContainer, false);
        fragContainer.addView(content);
        return fragContainer;
    }

    private void setupTemplate(ViewGroup contentView) {
        templateHelper = new TemplateHelper(contentView);
        templateHelper.setTitleHeight(getTitleHeight());
        templateHelper.setLoadProgress(getLoadProgress());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 初始化Presenter并关联Activity
     *
     * @return presenter
     */
    protected P getPresenter() {
        if (presenter == null) {
            presenter = newPresenter();
            if (presenter != null) {
                presenter.attachView(this);
            }
        }
        return presenter;
    }

    @Override
    public void success(String result, int id) {
        handleSuccess(id, result);
    }

    @Override
    public void fail(Exception e, int id) {
        handleError(id, e);
    }

    @Override
    public void finish(int id) {
        handleFinish(id);
    }

    @Override
    public void openLoadingAnim() {
        templateHelper.openLoadAnim();
    }

    @Override
    public void closeLoadingAnim() {
        templateHelper.closeLoadAnim();
    }

    /**
     * Model错误处理
     *
     * @param reqCode 请求码
     * @param args    返回数据
     */
    protected void handleSuccess(int reqCode, String args) {
    }

    /**
     * Model错误处理
     *
     * @param reqCode 请求码
     * @param e       异常
     */
    protected void handleError(int reqCode, Exception e) {
    }

    /**
     * Model错误处理
     *
     * @param reqCode 请求码
     */
    protected void handleFinish(int reqCode) {
    }

    /**
     * 设置Toolbar高度
     * 显示加载动画时不遮挡Toolbar
     */
    protected int getTitleHeight() {
        return (int) getResources().getDimension(R.dimen.title_height);
    }

    protected LoadingProgress getLoadProgress() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        templateHelper.destroy();
        mContext = null;
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
}

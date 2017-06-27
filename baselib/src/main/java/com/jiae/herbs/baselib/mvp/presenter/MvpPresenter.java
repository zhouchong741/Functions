package com.jiae.herbs.baselib.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.jiae.herbs.baselib.mvp.model.MvpModel;
import com.jiae.herbs.baselib.mvp.view.BaseView;

/**
 * 普通页面使用的Presenter
 * Created by huang on 2017/2/7.
 */
public abstract class MvpPresenter<M extends MvpModel> implements IPresenter {

    private BaseView view;
    protected M model;
    protected Context mContext;

    public BaseView getView() {
        return view;
    }

    protected void showMsg(String msg) {
        Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void showMsg(int resId) {
        Toast.makeText(view.getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void attachView(BaseView view) {
        this.view = view;
        model = setupModel();
        mContext = view.getContext();
    }

    protected abstract M setupModel();

    @Override
    public void detachView() {
        view = null;
        if (model != null) {
            model.clear();
            model = null;
        }
    }
}

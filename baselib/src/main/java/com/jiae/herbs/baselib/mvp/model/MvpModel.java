package com.jiae.herbs.baselib.mvp.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.jiae.herbs.baselib.mvp.presenter.MvpPresenter;
import com.jiae.herbs.baselib.utils.LogUtil;
import com.jiae.herbs.baselib.utils.http.HttpClient;
import com.jiae.herbs.baselib.utils.http.HttpMediaType;
import com.jiae.herbs.baselib.utils.http.NetWorkCallback;
import com.jiae.herbs.baselib.utils.http.RequestParam;

import java.io.File;

/**
 * 标题：MVP 模式下 Model基类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/18 11:42.
 * <p>
 * <h>主要负责网络请求</h>
 * 封装okHttp的get、post请求
 * <h>通知BaseView</h>
 * {@link MvpModel#notifyCallback(NetWorkCallback)}方法，将callback进行封装，并通知BaseView网络请求状态
 * </p>
 */
public abstract class MvpModel {

    protected MvpPresenter presenter;
    protected Context context;
    private static final String TAG = "MvpModel";
    private InterceptNetCall<String> interceptNetCall;

    public MvpModel(MvpPresenter presenter) {
        this.presenter = presenter;
        context = presenter.getView().getContext();
    }

    public void setInterceptNetCall(InterceptNetCall<String> interceptNetCall) {
        this.interceptNetCall = interceptNetCall;
    }

    /**
     * post String 请求
     *
     * @param url      请求url
     * @param content  body
     * @param reqCode  请求码
     * @param callback 请求回调
     */
    protected void postString(String url, String content, @HttpMediaType.ContentType String contentType,
                              int reqCode, final NetWorkCallback<String> callback) {
        LogUtil.d(TAG, "postString reqCode :" + reqCode + " URL：" + url + content);
        HttpClient.postString(context, url, content, contentType, reqCode, interceptCallback(notifyCallback(callback)));
    }

    protected void postFile(String url, File file, int reqCode, final NetWorkCallback<String> callback) {
        LogUtil.d(TAG, "postFile reqCode :" + reqCode + " URL：" + url);
        HttpClient.postFile(context, url, file, reqCode, interceptCallback(notifyCallback(callback)));
    }

    protected void postFrom(String url, File[] files, int reqCode, final NetWorkCallback<String> callback) {
        LogUtil.d(TAG, "postFrom reqCode :" + reqCode + " URL：" + url);
        HttpClient.postFrom(context, url, files, reqCode, interceptCallback(notifyCallback(callback)));
    }

    /**
     * post请求
     *
     * @param url      请求url
     * @param param    请求参数
     * @param reqCode  请求码
     * @param callback 请求回调
     */
    protected void post(String url, RequestParam param, int reqCode, final NetWorkCallback<String> callback) {
        LogUtil.d(TAG, "post reqCode :" + reqCode + " URL：" + url + (param == null ? "" : param.toParamString()));
        HttpClient.post(context, url, param, reqCode, interceptCallback(notifyCallback(callback)));
    }

    /**
     * get请求
     *
     * @param url      请求url
     * @param param    请求参数
     * @param reqCode  请求码
     * @param callback 请求回调
     */
    protected void get(String url, RequestParam param, int reqCode, final NetWorkCallback<String> callback) {
        LogUtil.d(TAG, "get reqCode :" + reqCode + " URL：" + url + (param == null ? "" : param.toParamString()));
        HttpClient.get(context, url, param, reqCode, interceptCallback(notifyCallback(callback)));
    }

    /**
     * put请求
     *
     * @param url      请求url
     * @param content  body
     * @param reqCode  请求码
     * @param callback 请求回调
     */
    protected void put(String url, String content, @HttpMediaType.ContentType String contentType,
                       int reqCode, final NetWorkCallback<String> callback) {
        LogUtil.d(TAG, "put type: " + contentType + " reqCode :" + reqCode + " URL：" + url + "/" + content);
        HttpClient.put(context, url, content, contentType, reqCode, interceptCallback(notifyCallback(callback)));
    }

    /**
     * delete请求
     *
     * @param url      请求url
     * @param content  body
     * @param reqCode  请求码
     * @param callback 请求回调
     */
    protected void delete(String url, String content, @HttpMediaType.ContentType String contentType,
                          int reqCode, final NetWorkCallback<String> callback) {
        LogUtil.d(TAG, "delete type: " + contentType + " reqCode :" + reqCode + " URL：" + url + "/" + content);
        HttpClient.delete(context, url, content, contentType, reqCode, interceptCallback(notifyCallback(callback)));
    }

    /**
     * 封装callback
     * 所有请求的回调必须经过这一层封装
     * 它会通知BaseView的请求回调
     *
     * @param callback 返回的数据
     * @return 封装后的callback
     */
    @NonNull
    protected NetWorkCallback<String> notifyCallback(final NetWorkCallback<String> callback) {
        return new NetWorkCallback<String>() {
            @Override
            @SuppressWarnings("unchecked")
            public void success(String result, int id) {
                LogUtil.d(TAG, "reqCode :" + id + "result: " + result);
                if (presenter != null && presenter.getView() != null)
                    presenter.getView().success(result, id);
                if (callback != null)
                    callback.success(result, id);
            }

            @Override
            public void fail(Exception e, int id) {
                LogUtil.d(TAG, "reqCode :" + id + "result: " + (e == null ? "" : e.toString()));
                if (presenter != null && presenter.getView() != null)
                    presenter.getView().fail(e, id);
                if (callback != null)
                    callback.fail(e, id);
            }

            @Override
            public void finish(int id) {
                if (presenter != null && presenter.getView() != null)
                    presenter.getView().finish(id);
                if (callback != null)
                    callback.finish(id);
            }
        };
    }

    private NetWorkCallback<String> interceptCallback(final NetWorkCallback<String> callback) {
        return new NetWorkCallback<String>() {
            @Override
            public void success(String result, int id) {
                if (interceptNetCall == null || !interceptNetCall.interceptSuccess(callback, result, id)) {
                    callback.success(result, id);
                }
            }

            @Override
            public void fail(Exception e, int id) {
                if (interceptNetCall == null || !interceptNetCall.interceptFail(callback, e, id)) {
                    callback.fail(e, id);
                }
            }

            @Override
            public void finish(int id) {
                if (interceptNetCall == null || !interceptNetCall.interceptFinish(callback, id)) {
                    callback.finish(id);
                }
            }
        };
    }

    public abstract void clear();

    public static abstract class MvpNetWorkCallBack<D> implements NetWorkCallback<D> {

        private final NetWorkCallback callback;

        protected MvpNetWorkCallBack(NetWorkCallback callback) {
            this.callback = callback;
        }

        @Override
        public void fail(Exception e, int id) {
            if (callback == null)
                return;
            callback.fail(e, id);
        }

        @Override
        public void finish(int id) {
            if (callback == null)
                return;
            callback.finish(id);
        }
    }

    public interface InterceptNetCall<D> {

        boolean interceptSuccess(NetWorkCallback<D> callback, D result, int id);

        boolean interceptFail(NetWorkCallback<D> callback, Exception e, int id);

        boolean interceptFinish(NetWorkCallback<D> callback, int id);

    }

    public static class MvpInterceptNetCall<D> implements InterceptNetCall<D> {

        @Override
        public boolean interceptSuccess(NetWorkCallback<D> callback, D result, int id) {
            return false;
        }

        @Override
        public boolean interceptFail(NetWorkCallback<D> callback, Exception e, int id) {
            return false;
        }

        @Override
        public boolean interceptFinish(NetWorkCallback<D> callback, int id) {
            return false;
        }
    }

}

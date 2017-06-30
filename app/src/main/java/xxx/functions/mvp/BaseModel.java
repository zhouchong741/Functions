package xxx.functions.mvp;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.jiae.herbs.baselib.mvp.model.MvpModel;
import com.jiae.herbs.baselib.mvp.presenter.MvpPresenter;
import com.jiae.herbs.baselib.utils.LogUtil;
import com.jiae.herbs.baselib.utils.http.HttpMediaType;
import com.jiae.herbs.baselib.utils.http.NetWorkCallback;
import com.jiae.herbs.baselib.utils.http.RequestParam;

import java.io.File;

import xxx.functions.data.RespondData;
import xxx.functions.https.HttpUrl;
import xxx.functions.https.HttpUtil;

/**
 * 标题：app 封装BaseModel
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/22 16:57.
 * <p>
 * <h>Url拼装</h>
 * {@link HttpUrl#makeUrl(String, String)}，拼装请求地址
 * <h>参数拼装</h>
 * {@link BaseModel#getRequestParam(RequestParam)}，拼装设备参数
 * <h>请求回调拼装</h>
 * {@link BaseModel#getRespondCallback(NetWorkCallback)}，对请求结果进行分类处理
 * </p>
 */
public class BaseModel extends MvpModel {

    public BaseModel(final MvpPresenter mvpPresenter) {
        super(mvpPresenter);
        setupIntercept();
    }

    private void setupIntercept() {
        setInterceptNetCall(new MvpInterceptNetCall<String>() {
            @Override
            @SuppressWarnings("unchecked")
            public boolean interceptSuccess(NetWorkCallback<String> callback, String result, int id) {
                try {
                    RespondData respondData = JSON.parseObject(result, RespondData.class);
                    String statusCode = respondData.getCode();
                    switch (statusCode) {
                        case "200"://请求成功
                            callback.success(result, id);
                            break;
                        case "202"://重复账号 已经注册
                            callback.success(result, id);
                            break;
                        case "500"://服务器错误
                            callback.success(result, id);
                            break;
                        case "5000"://授权失败
                            callback.success(result,id);
                            break;
                        default://从服务器拿到的数据，但是出现了问题
                            throw new ErrorCodeException(respondData.getMsg());
                    }
                } catch (JSONException | ErrorCodeException e) {
                    LogUtil.d("MvpModel", "reqCode :" + id + "result: " + result);
                    e.printStackTrace();
                    callback.fail(e, id);
                }
                return true;
            }
        });
    }

    @Override
    public void clear() {
        presenter = null;
        context = null;
    }

    /**
     * 封装的默认返回RespondData的get请求
     *
     * @param url       请求url
     * @param serverTag 请求服务器类型
     * @param param     请求参数
     * @param reqCode   请求码
     * @param callback  请求回调
     */
    public void getRespondData(String url, String serverTag, RequestParam param, int reqCode,
                               final NetWorkCallback<RespondData> callback) {
        get(HttpUrl.makeUrl(url, serverTag), getRequestParam(param), reqCode, getRespondCallback(callback));
    }

    public void getRespondData(String url, String serverTag, RequestParam param,
                               String progressMsg, boolean cancel,
                               int reqCode, final NetWorkCallback<RespondData> callback) {
        if (presenter != null && presenter.getView() != null)
            presenter.getView().showProgressDialog(progressMsg, cancel);
        getRespondData(url, serverTag, param, reqCode, callback);
    }

    /**
     * 封装的默认返回RespondData的post请求
     */
    public void postRespondData(String url, String serverTag, RequestParam param, int reqCode,
                                final NetWorkCallback<RespondData> callback) {
        post(HttpUrl.makeUrl(url, serverTag), getRequestParam(param), reqCode, getRespondCallback(callback));
    }

    public void postRespondData(String url, String serverTag, RequestParam param,
                                String progressMsg, boolean cancel,
                                int reqCode, final NetWorkCallback<RespondData> callback) {
        if (presenter != null && presenter.getView() != null)
            presenter.getView().showProgressDialog(progressMsg, cancel);
        postRespondData(url, serverTag, param, reqCode, callback);
    }

    public void postStringRespondData(String url, String serverTag, RequestParam param, String content,
                                      int reqCode, final NetWorkCallback<RespondData> callback) {
        postStringRespondData(url, serverTag, param, content, HttpMediaType.TYPE_APP_JSON, reqCode, callback);
    }

    public void postStringRespondData(String url, String serverTag, RequestParam param, String content,
                                      String progressMsg, boolean cancel,
                                      int reqCode, final NetWorkCallback<RespondData> callback) {
        postStringRespondData(url, serverTag, param, content, HttpMediaType.TYPE_APP_JSON,
                progressMsg, cancel, reqCode, callback);
    }

    public void postFileRespondData(String url, String serverTag, RequestParam param, File file,
                                    int reqCode, final NetWorkCallback<RespondData> callback) {
        url = url + getRequestParam(param).toParamString();
        postFile(HttpUrl.makeUrl(url, serverTag), file, reqCode, getRespondCallback(callback));
    }

    public void postFileRespondData(String url, String serverTag, RequestParam param, File file,
                                    String progressMsg, boolean cancel,
                                    int reqCode, final NetWorkCallback<RespondData> callback) {
        if (presenter != null)
            presenter.getView().showProgressDialog(progressMsg, cancel);
        postFileRespondData(url, serverTag, param, file, reqCode, callback);
    }


    public void postFromRespondData(String url, String serverTag, RequestParam param, File[] files,
                                    int reqCode, final NetWorkCallback<RespondData> callback) {
        url = url + getRequestParam(param).toParamString();
        postFrom(HttpUrl.makeUrl(url, serverTag), files, reqCode, getRespondCallback(callback));
    }

    public void postFromRespondData(String url, String serverTag, RequestParam param, File[] files,
                                    String progressMsg, boolean cancel,
                                    int reqCode, final NetWorkCallback<RespondData> callback) {
        if (presenter != null)
            presenter.getView().showProgressDialog(progressMsg, cancel);
        postFromRespondData(url, serverTag, param, files, reqCode, callback);
    }

    /**
     * 封装的默认返回RespondData的postString请求
     *
     * @param contentType content 文本的编码类型
     */
    public void postStringRespondData(String url, String serverTag, RequestParam param, String content,
                                      @HttpMediaType.ContentType String contentType, int reqCode,
                                      final NetWorkCallback<RespondData> callback) {
        url = url + getRequestParam(param).toParamString();
        postString(HttpUrl.makeUrl(url, serverTag), content, contentType, reqCode, getRespondCallback(callback));
    }

    public void postStringRespondData(String url, String serverTag, RequestParam param, String content,
                                      @HttpMediaType.ContentType String contentType,
                                      String progressMsg, boolean cancel,
                                      int reqCode, final NetWorkCallback<RespondData> callback) {
        if (presenter != null && presenter.getView() != null)
            presenter.getView().showProgressDialog(progressMsg, cancel);
        url = url + getRequestParam(param).toParamString();
        postString(HttpUrl.makeUrl(url, serverTag), content, contentType, reqCode, getRespondCallback(callback));
    }

    /**
     * 封装的默认返回RespondData的put请求
     *
     * @param contentType content 文本的编码类型
     */
    public void putRespondData(String url, String serverTag, RequestParam param, String content,
                               @HttpMediaType.ContentType String contentType, int reqCode,
                               final NetWorkCallback<RespondData> callback) {
        url = url + getRequestParam(param).toParamString();
        put(HttpUrl.makeUrl(url, serverTag), content, contentType, reqCode, getRespondCallback(callback));
    }

    public void putRespondData(String url, String serverTag, RequestParam param, String content,
                               @HttpMediaType.ContentType String contentType,
                               String progressMsg, boolean cancel,
                               int reqCode, final NetWorkCallback<RespondData> callback) {
        if (presenter != null)
            presenter.getView().showProgressDialog(progressMsg, cancel);
        url = url + getRequestParam(param).toParamString();
        put(HttpUrl.makeUrl(url, serverTag), content, contentType, reqCode, getRespondCallback(callback));
    }

    public void putRespondData(String url, String serverTag, RequestParam param, String content, int reqCode,
                               final NetWorkCallback<RespondData> callback) {
        putRespondData(url, serverTag, param, content, HttpMediaType.TYPE_APP_JSON, reqCode, callback);
    }

    public void putRespondData(String url, String serverTag, RequestParam param, String content,
                               String progressMsg, boolean cancel,
                               int reqCode, final NetWorkCallback<RespondData> callback) {
        putRespondData(url, serverTag, param, content, HttpMediaType.TYPE_APP_JSON,
                progressMsg, cancel, reqCode, callback);
    }

    /**
     * 封装的默认返回RespondData的delete请求
     *
     * @param contentType content 文本的编码类型
     */
    public void deleteRespondData(String url, String serverTag, RequestParam param, String content,
                                  @HttpMediaType.ContentType String contentType, int reqCode,
                                  final NetWorkCallback<RespondData> callback) {
        url = url + getRequestParam(param).toParamString();
        delete(HttpUrl.makeUrl(url, serverTag), content, contentType, reqCode, getRespondCallback(callback));
    }

    public void deleteRespondData(String url, String serverTag, RequestParam param, String content,
                                  @HttpMediaType.ContentType String contentType,
                                  String progressMsg, boolean cancel,
                                  int reqCode, final NetWorkCallback<RespondData> callback) {
        if (presenter != null && presenter.getView() != null)
            presenter.getView().showProgressDialog(progressMsg, cancel);
        url = url + getRequestParam(param).toParamString();
        delete(HttpUrl.makeUrl(url, serverTag), content, contentType, reqCode, getRespondCallback(callback));
    }

    public void deleteRespondData(String url, String serverTag, RequestParam param, String content,
                                  int reqCode, final NetWorkCallback<RespondData> callback) {
        deleteRespondData(url, serverTag, param, content, HttpMediaType.TYPE_APP_JSON, reqCode, callback);
    }

    public void deleteRespondData(String url, String serverTag, RequestParam param, String content,
                                  String progressMsg, boolean cancel,
                                  int reqCode, final NetWorkCallback<RespondData> callback) {
        deleteRespondData(url, serverTag, param, content, HttpMediaType.TYPE_APP_JSON,
                progressMsg, cancel, reqCode, callback);
    }

    @NonNull
    protected NetWorkCallback<String> getRespondCallback(final NetWorkCallback<RespondData> callback) {
        return new MvpNetWorkCallBack<String>(callback) {
            @Override
            public void success(String result, int id) {
                if (callback == null)
                    return;
                RespondData respondData = JSON.parseObject(result, RespondData.class);
                respondData.setData(JsonFormat.delete("null", respondData.getData()));
                callback.success(respondData, id);
            }
        };
    }

    private RequestParam getRequestParam(RequestParam param) {
        return HttpUtil.packPostParams(context, param);
    }

}

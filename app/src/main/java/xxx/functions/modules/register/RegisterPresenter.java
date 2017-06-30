package xxx.functions.modules.register;

import com.jiae.herbs.baselib.utils.http.NetWorkCallback;

import xxx.functions.data.RespondData;
import xxx.functions.https.HttpUrl;
import xxx.functions.mvp.BasePresenter;

/**
 * 作者:zc741
 * 描述:
 * 时间:2017/6/29
 */

public class RegisterPresenter extends BasePresenter implements NetWorkCallback<RespondData> {

    public void register(String url, String tag, String content, String msg, int reqCode) {
        model.postStringRespondData(url, tag, null, content, msg, false, reqCode, this);
    }

    @Override
    public void success(RespondData result, int id) {
        switch (id){
            case HttpUrl.REQ_CODE_REGISTER:
                registerCallBack(result);
                break;
        }
    }

    private void registerCallBack(RespondData data) {
        // 解析注册时返回的数据
        switch (Integer.parseInt(data.getCode())){
            case HttpUrl.REQ_CODE_REGISTER:
                showMsg("注册好了===将要登录");
                break;
            case HttpUrl.REQ_CODE_REPEAT:
                showMsg("您已经注册了，去登录吧");
                break;
            default:
                showMsg("其他原因错误码=" + data.getCode() + "; 错误信息=" + data.getMsg());
                break;
        }
    }

    @Override
    public void fail(Exception e, int id) {

    }

    @Override
    public void finish(int id) {

    }
}

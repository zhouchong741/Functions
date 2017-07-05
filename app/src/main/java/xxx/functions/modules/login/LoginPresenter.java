package xxx.functions.modules.login;

import com.alibaba.fastjson.JSON;
import com.jiae.herbs.baselib.utils.http.NetWorkCallback;

import xxx.functions.data.RespondData;
import xxx.functions.https.HttpUrl;
import xxx.functions.modules.login.data.ImUserData;
import xxx.functions.modules.login.data.LoginResultData;
import xxx.functions.mvp.BasePresenter;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/6/29 13:13
 */

public class LoginPresenter extends BasePresenter implements NetWorkCallback<RespondData> {

    public void login(String url, String tag, String content, String msg, int reqCode) {
        model.postStringRespondData(url, tag, null, content, msg, false, reqCode, this);
    }

    @Override
    public void success(RespondData result, int id) {
        switch (id) {
            case HttpUrl.REQ_CODE_LOGIN:
                loginCallBack(result);
                break;
        }
    }

    private void loginCallBack(RespondData data) {
        // 解析登录获取的数据
        switch (Integer.parseInt(data.getCode())) {
            case 200:
                showMsg("登录码==登录了");
                // 进入主界面
                LoginResultData loginResultData = JSON.parseObject(data.getData(),LoginResultData.class);
                ImUserData imData = loginResultData.getImuser();
                System.out.println(imData);
                break;
            case HttpUrl.REQ_CODE_SERVER_ERROR:
                showMsg("错误码==服务器错误");
                break;
            case HttpUrl.REQ_CODE_FAIL:
                showMsg(data.getMsg());
                break;
            default:
                showMsg("其他原因错误码=" + data.getCode() + "; 错误信息=" + data.getMsg());
                break;
        }
    }

    @Override
    public void fail(Exception e, int id) {
        System.out.println(e);
    }

    @Override
    public void finish(int id) {

    }
}

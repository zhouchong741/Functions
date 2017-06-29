package xxx.functions.modules.login;

import com.jiae.herbs.baselib.utils.http.NetWorkCallback;

import xxx.functions.data.RespondData;
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
        System.out.println(result + ";===id====" + id);
    }

    @Override
    public void fail(Exception e, int id) {
        System.out.println(e);
    }

    @Override
    public void finish(int id) {

    }
}

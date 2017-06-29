package xxx.functions.modules.register;

import com.jiae.herbs.baselib.utils.http.NetWorkCallback;

import xxx.functions.data.RespondData;
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

    }

    @Override
    public void fail(Exception e, int id) {

    }

    @Override
    public void finish(int id) {

    }
}

package xxx.functions.modules.home;

import com.jiae.herbs.baselib.mvp.listhelper.Item;
import com.jiae.herbs.baselib.utils.http.NetWorkCallback;

import xxx.functions.data.RespondData;
import xxx.functions.mvp.BaseListPresenter;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/7/5 15:30
 */

public class HomeContainerFragPresenter extends BaseListPresenter implements NetWorkCallback<RespondData> {

    @Override
    public void success(RespondData result, int id) {

    }

    @Override
    public void fail(Exception e, int id) {

    }

    @Override
    public void finish(int id) {

    }

    @Override
    protected Item setupItemTemplate() {
        return null;
    }
}

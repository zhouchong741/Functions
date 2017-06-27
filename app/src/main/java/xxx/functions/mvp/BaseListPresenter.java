package xxx.functions.mvp;

import com.jiae.herbs.baselib.mvp.bean.Data;
import com.jiae.herbs.baselib.mvp.presenter.MvpListPresenter;

/**
 * 标题：
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/1 13:04.
 */
public abstract class BaseListPresenter<D extends Data> extends MvpListPresenter<D, BaseModel> {
    @Override
    protected BaseModel setupModel() {
        return new BaseModel(this);
    }

}

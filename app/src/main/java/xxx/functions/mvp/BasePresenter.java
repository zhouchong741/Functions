package xxx.functions.mvp;

import com.jiae.herbs.baselib.mvp.presenter.MvpPresenter;

/**
 * 标题：
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/1 13:03.
 */
public class BasePresenter extends MvpPresenter<BaseModel> {
    @Override
    protected BaseModel setupModel() {
        return new BaseModel(this);
    }
}

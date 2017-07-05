package xxx.functions.modules.home;

import com.jiae.herbs.baselib.view.recyclerhelper.decoration.DividerItemDecoration;

import xxx.functions.BaseListFragment;

/**
 * 标题：
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/5 11:08.
 */
public class HomeContainerFragment extends BaseListFragment<HomeContainerFragPresenter> {

    @Override
    public void initView() {
        super.initView();
        DividerItemDecoration decoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);
        decoration.setDividerSpace(1);
    }

    @Override
    public HomeContainerFragPresenter newPresenter() {
        return new HomeContainerFragPresenter();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {
    }

    @Override
    public int getPageSize() {
        return 0;
    }
}

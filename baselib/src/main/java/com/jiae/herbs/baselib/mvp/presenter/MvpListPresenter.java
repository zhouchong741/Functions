package com.jiae.herbs.baselib.mvp.presenter;

import com.jiae.herbs.baselib.mvp.bean.Data;
import com.jiae.herbs.baselib.mvp.listhelper.Logic;
import com.jiae.herbs.baselib.mvp.listhelper.QuickAdapter;
import com.jiae.herbs.baselib.mvp.listhelper.Item;
import com.jiae.herbs.baselib.mvp.listhelper.ItemFactory;
import com.jiae.herbs.baselib.mvp.listhelper.SelectLogic;
import com.jiae.herbs.baselib.mvp.model.MvpModel;
import com.jiae.herbs.baselib.mvp.view.BaseView;

import java.util.List;


/**
 * 列表Presenter
 * <p>
 * 绑定{@link SelectLogic}列表逻辑处理
 * 生成默认BaseAdapter
 * 使用ItemFactory生成Item列表
 * </p>
 * Created by huang on 2017/2/7.
 */
public abstract class MvpListPresenter<D extends Data,M extends MvpModel> extends MvpPresenter<M> {

    private Item<D> mItemTemplate;
    private ItemFactory<D> factory;
    private Logic itemLogic;
    private QuickAdapter<Item<D>> adapter;

    @Override
    public void attachView(BaseView view) {
        super.attachView(view);
        mItemTemplate = setupItemTemplate();
        adapter = new QuickAdapter<>();
        factory = new ItemFactory<>(view.getContext(), mItemTemplate, adapter);
    }

    public void setAdapter(QuickAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 在父类中注册ItemLogic，
     * 主要是在创建Item时传给所有Item，保持所有Item都持有一个ItemLogic对象
     * {@link ItemFactory#makeItems(List, Logic)}
     *
     * @param logic 在父类中注册的ItemLogic
     */
    protected void setItemLogic(Logic logic) {
        itemLogic = logic;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (itemLogic != null) {
            itemLogic.clear();
            itemLogic = null;
        }
        mItemTemplate = null;
        factory = null;
        adapter.clear();
    }

    /**
     * 通过list生产出Item列表
     * {@link ItemFactory#makeItems(List, Logic)}
     *
     * @param list 生产Item所需数据源
     */
    public void notifyAfterLoad(List<D> list) {
        List<Item<D>> items = factory.makeItems(list, itemLogic);
        adapter.addData(items);
    }

    public void notifyAfterLoad(List<D> list, boolean hasMoreItems) {
        List<Item<D>> items = factory.makeItems(list, itemLogic);
        adapter.notifyDataChangedAfterLoadMore(items, hasMoreItems);
    }

    public QuickAdapter<Item<D>> getAdapter() {
        return adapter;
    }

    /**
     * 设置Item模板用于生产列表
     * {@link Item#newSelf(D d)}
     *
     * @return 一个Item模板
     */
    protected abstract Item<D> setupItemTemplate();
}

package com.jiae.herbs.baselib.mvp.listhelper;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jiae.herbs.baselib.mvp.bean.Data;


/**
 * 自定义Item控制类
 * <p>
 * 将Adapter分成Item+Logic：
 * Item表示View，Logic表示Item里的逻辑处理，数据源由Model获取
 * </p>
 * <p>
 * {@link Item#itemEnable()} 如果开启Item点击事件
 * {@link IAdapter#getItemResId()} Item的布局中最好不要出现Button类控件
 * 可能会出现一些bug
 * </p>
 */
public abstract class Item<D extends Data> implements IAdapter, Interact<D> {

    protected D data;
    protected QuickAdapter adapter;
    protected int position;
    protected Context mContext;

    @Override
    public void onBindViewHolder(BaseViewHolder helper, int adapterPosition) {
        position = adapterPosition;
        setViewData(helper);
        helper.itemView.setEnabled(itemEnable());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v);
            }
        });
        onRefreshViewStyle();
    }

    @Override
    public int getItemType() {
        //默认返回 0，可重写
        return 0;
    }

    @Override
    public int getItemPosition() {
        return position;
    }

    /**
     * 返回Item持有数据
     */
    public D getData() {
        return data;
    }

    /**
     * 给Item设置数据
     */
    public void setData(D data) {
        this.data = data;
    }

    /**
     * 设置Adapter
     */
    public void setAdapter(QuickAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * 设置上下文
     */
    public void setContext(Context context) {
        mContext = context;
    }
}

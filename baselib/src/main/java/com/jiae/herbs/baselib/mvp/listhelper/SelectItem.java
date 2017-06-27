package com.jiae.herbs.baselib.mvp.listhelper;

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
public abstract class SelectItem<D extends Data> extends Item<D>  implements SetLogic{

    protected SelectLogic logic;

    @Override
    public void onBindViewHolder(BaseViewHolder helper, int adapterPosition) {
        position = adapterPosition;
        setViewData(helper);
        helper.itemView.setEnabled(itemEnable());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logic != null && logic.isReady()) {
                    logic.onItemClick(adapter, SelectItem.this);
                }
                onItemClick(v);
            }
        });
        onRefreshViewStyle();
    }


    /**
     * 设置处理逻辑
     */
    @Override
    public void setLogic(Logic logic) {
        if (logic instanceof SelectLogic) {
            this.logic = (SelectLogic) logic;
        } else {
            throw new ClassCastException("logic must extents SelectLogic!");
        }
    }
}

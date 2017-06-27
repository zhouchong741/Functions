package com.jiae.herbs.baselib.view.recyclerhelper.touchhelper.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jiae.herbs.baselib.view.recyclerhelper.touchhelper.swipemenu.OnItemClickListener;

/**
 * ViewHolder 嵌套容器
 * Created by huang on 2017/5/4.
 */
public abstract class NestingViewHolder extends BaseViewHolder {

    protected OnItemClickListener itemClickListener;

    public NestingViewHolder(View itemView) {
        super(itemView);
    }

    public abstract RecyclerView.ViewHolder getHolder();

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

}

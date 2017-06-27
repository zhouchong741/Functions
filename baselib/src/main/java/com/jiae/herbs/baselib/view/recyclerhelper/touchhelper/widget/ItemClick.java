package com.jiae.herbs.baselib.view.recyclerhelper.touchhelper.widget;

import android.support.v7.widget.RecyclerView;

import com.jiae.herbs.baselib.view.recyclerhelper.touchhelper.swipemenu.OnItemClickListener;


/**
 * 设置条目点击事件
 * Created by hwyMi on 2017/5/11.
 */
public interface ItemClick<T> {

    T setOnItemClickListener(OnItemClickListener listener);

    T setAdapter(RecyclerView.Adapter adapter);
}

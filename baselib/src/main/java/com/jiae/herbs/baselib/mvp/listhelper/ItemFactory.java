package com.jiae.herbs.baselib.mvp.listhelper;

import android.content.Context;
import android.support.annotation.Nullable;


import com.jiae.herbs.baselib.mvp.bean.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * item工厂类
 * Created by huang on 2017/3/3.
 */

public class ItemFactory<D extends Data> {

    private Item<D> template;
    private QuickAdapter<Item<D>> adapter;
    private Context context;

    public ItemFactory(Context context, Item<D> template, QuickAdapter<Item<D>> adapter) {
        this.template = template;
        this.adapter = adapter;
        this.context = context;
    }

    @Nullable
    public List<Item<D>> makeItems(List<D> list, Logic logic) {
        List<Item<D>> items = new ArrayList<>();
        if (list == null)
            return items;
        for (D d : list) {
            Item<D> item = template.newSelf(d);
            if (item instanceof SetLogic) {
                ((SetLogic) item).setLogic(logic);
            }
            item.setContext(context);
            item.setAdapter(adapter);
            item.setData(d);
            item.readyTodo();
            items.add(item);
        }
        return items;
    }
}

package xxx.functions;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * 标题：列表界面模版
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/17 11:01.
 */
public interface IBaseList {

    /**
     * 获取刷新控件
     */
    SwipeRefreshLayout getRefreshLayout();

    /**
     * 获取RecyclerView控件
     */
    RecyclerView getRecyclerView();

    /**
     * 设置列表空试图图标
     */
    void setEmptyImage(Drawable drawable);

    /**
     * 设置列表空试图图标
     */
    void setEmptyImage(int resId);

    /**
     * 设置空试图文本
     */
    void setEmptyText(CharSequence c);

    /**
     * 设置空试图文本
     */
    void setEmptyText(int resId);

    /**
     * 设置页面背景色
     */
    void setBackgroundColor(@ColorInt int color);

    /**
     * 是否显示SwipeRefreshLayout刷新动画
     */
    void setRefreshing(boolean refresh);

    /**
     * 返回列表的长度
     * 用于判断是否开启加载更多功能
     */
    int getPageSize();

    /**
     * 加载数据
     */
    void loadData();
}

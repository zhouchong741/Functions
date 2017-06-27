package com.jiae.herbs.baselib.view.group;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Observable;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 标题：
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/15 16:36.
 */

public class SelectViewGroup extends ViewGroup {

    private static final String TAG = "SelectViewGroup";
    private int sizeWidth;
    private int sizeHeight;
    private final SelectViewDataObserver mObserver = new SelectViewDataObserver();
    private Adapter mAdapter;
    private List<DataBinder> childEntities = new ArrayList<>();

    public SelectViewGroup(Context context) {
        this(context, null);
    }

    public SelectViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }

    /**
     * 计算 ChildView 宽高
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*
         * 计算 ViewGroup 上级容器为其推荐的宽高
         */
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        //计算 childView 宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int totalWidth = 0;
        int totalHeight = getPaddingTop();

        if (mAdapter != null) {
            totalHeight += mAdapter.getVerticalSpacing();
            totalHeight = getMultiTotalHeight(totalWidth, totalHeight);
        } else {
            Log.d(TAG, "SelectViewGroup do not have Adapter!");
        }
        totalHeight += getPaddingBottom();

        /*
         * 高度根据设置改变
         * 如果为 MATCH_PARENT 则充满父窗体，否则根据内容自定义高度
         */
        setMeasuredDimension(sizeWidth, (heightMode == MeasureSpec.EXACTLY ? sizeHeight : totalHeight));
    }

    /**
     * 为 multiLine 模式布局，并计算视图高度
     */
    private int getMultiTotalHeight(int totalWidth, int totalHeight) {
        int childWidth;
        int childHeight;
        int column = mAdapter.getColumn();
        int horizontalSpacing = mAdapter.getHorizontalSpacing();
        int verticalSpacing = mAdapter.getVerticalSpacing();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            // column 为 0， 自由排布
            if (column == 0) {
                childWidth = child.getMeasuredWidth();
            } else {
                childWidth = (sizeWidth - getPaddingLeft() - getPaddingRight()) / column - horizontalSpacing;
            }
            childHeight = child.getMeasuredHeight();

            totalWidth += childWidth + horizontalSpacing;

            if (i == 0) {
                totalHeight = childHeight + getPaddingTop();
                totalWidth += getPaddingLeft();
            }

            if (totalWidth - horizontalSpacing + getPaddingRight() > sizeWidth) {
                totalWidth = getPaddingLeft();
                totalHeight += childHeight + verticalSpacing;
                child.layout(totalWidth, totalHeight - childHeight, totalWidth + childWidth, totalHeight);
                totalWidth += childWidth + horizontalSpacing;
            } else {
                child.layout(totalWidth - childWidth - horizontalSpacing, totalHeight - childHeight, totalWidth - horizontalSpacing, totalHeight);
            }

        }
        return totalHeight;
    }

    public void setAdapter(Adapter adapter) {
        setAdapterInternal(adapter);
        requestLayout();
    }

    private void setAdapterInternal(Adapter adapter) {
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(mObserver);
            mAdapter.onDetachedFromRecyclerView(this);
        }
        mAdapter = adapter;
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mObserver);
            adapter.onAttachedToRecyclerView(this);
        }
    }

    /**
     * 更新Group child方法
     */
    private void notifyDataChanged() {
        childEntities.clear();
        removeAllViews();
        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            addChildView(i);
        }
        postInvalidate();
    }

    private void addChildView(final int position) {

        int layoutResId = mAdapter.getLayoutResId(position);
        View child = LayoutInflater.from(getContext()).inflate(layoutResId, this, false);

        DataBinder item = mAdapter.bindChildEntity(child, position, mAdapter.getItemType(position));
        childEntities.add(item);
        child.setSelected(item.isSelected);

        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        child.setLayoutParams(layoutParams);
        child.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBinder item = mAdapter.getItem(position);
                mAdapter.itemStateChanged(position);
                v.setSelected(item.isSelected);
            }
        });

        addView(child);
    }

    private class SelectViewDataObserver implements AdapterDataObserver {
        @Override
        public void onChanged() {
            notifyDataChanged();
//                    postInvalidate();
//            requestLayout();
        }

    }

    public static abstract class Adapter<CH extends DataBinder> {

        private final AdapterDataObservable mObservable = new AdapterDataObservable();
        private int mColumn = 1;
        private int mResId = -1;
        private int mHorizontalSpacing;
        private int mVerticalSpacing;
        private int mSelectMode;
        private boolean mCancel;
        private int mLastPosition = -1;
        private OnChildSelectedListener childSelectedListener;
        private List<CH> mChs = new ArrayList<>();

        public Adapter(int column, GroupConfig config) {
            mColumn = column;
            if (config == null)
                throw new NullPointerException("GroupConfig can not be null!");
            mResId = config.mResId;
            mHorizontalSpacing = config.mHorizontalSpacing;
            mVerticalSpacing = config.mVerticalSpacing;
            mSelectMode = config.mSelectMode;
            mCancel = config.mCancel;
        }

        public abstract int getItemCount();

        public abstract int getItemType(int position);

        protected abstract CH onBindChildEntity(int position, int itemType);

        public CH getItem(int position) {
            return mChs.get(position);
        }

        private CH bindChildEntity(View child, int position, int itemType) {
            CH ch = onBindChildEntity(position, itemType);
            ch.convert(new ViewHelper(child));
            mChs.add(ch);
            return ch;
        }

        void itemStateChanged(int position) {
            final CH newItem = getItem(position);
            if (!newItem.isSelected) {//如果点击Item未选中
                //单选模式 取消上一个的选中状态
                if (mSelectMode == GroupConfig.MODE_SINGLE && mLastPosition != -1) {
                    CH item = getItem(mLastPosition);
                    item.isSelected = false;
                    item.selectedStateChange();
                    if (childSelectedListener != null)
                        childSelectedListener.onSGChildSelected(position, false);
                }
                mLastPosition = position;
                newItem.isSelected = false;
                newItem.selectedStateChange();
                if (childSelectedListener != null)
                    childSelectedListener.onSGChildSelected(position, true);
            } else if (mCancel) {//如果可以取消选中效果
                newItem.isSelected = false;
                newItem.selectedStateChange();
            }
        }

        public List<CH> getSelectedEntities() {
            List<CH> list = new ArrayList<>();
            if (mSelectMode == GroupConfig.MODE_SINGLE) {
                if (mLastPosition == -1)
                    return null;
                list.add(getItem(mLastPosition));
                return list;
            } else {

                return list;
            }
        }

        public void setChildSelectedListener(OnChildSelectedListener listener) {
            childSelectedListener = listener;
        }

        protected int getLayoutResId(int position) {
            return mResId;
        }

        protected int getColumn() {
            return mColumn;
        }

        protected int getHorizontalSpacing() {
            return mHorizontalSpacing;
        }

        protected int getVerticalSpacing() {
            return mVerticalSpacing;
        }

        public void notifyChanged() {
            mObservable.notifyChanged();
        }

        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            mObservable.registerObserver(observer);
        }

        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            mObservable.unregisterObserver(observer);
        }

        public void onDetachedFromRecyclerView(SelectViewGroup selectViewGroup) {
        }

        public void onAttachedToRecyclerView(SelectViewGroup selectViewGroup) {
        }
    }

    public static class GroupConfig {

        public static final int MODE_SINGLE = 1;
        public static final int MODE_MULTI = 2;

        private int mSelectMode;
        private int mHorizontalSpacing;
        private int mVerticalSpacing;
        private int mResId;
        private boolean mCancel;

        public GroupConfig(int selectMode, int horizontalSpacing, int verticalSpacing,
                           int resId, boolean cancel) {
            this.mSelectMode = selectMode;
            this.mHorizontalSpacing = horizontalSpacing;
            this.mVerticalSpacing = verticalSpacing;
            this.mResId = resId;
            this.mCancel = cancel;
        }
    }

    public static abstract class DataBinder<D> {

        protected boolean isSelected;
        protected D data;

        protected DataBinder(D d) {
            this.data = d;
        }

        public D getData() {
            return data;
        }

        protected abstract void convert(ViewHelper helper);

        protected abstract void selectedStateChange();

    }

    static class AdapterDataObservable extends Observable<AdapterDataObserver> {

        public void notifyChanged() {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }
    }

    public interface AdapterDataObserver {
        void onChanged();
    }

    /**
     * 通知调用者child被点击，进行数据操作，通知UI刷新
     */
    public interface OnChildSelectedListener {
        void onSGChildSelected(int position, boolean select);
    }

    public static class ViewHelper {
        private final SparseArray<View> views = new SparseArray();
        public View convertView;

        public ViewHelper(View convertView) {
            this.convertView = convertView;
        }

        public <T extends View> T getView(int viewId) {
            View view = (View) this.views.get(viewId);
            if (view == null) {
                view = this.convertView.findViewById(viewId);
                this.views.put(viewId, view);
            }
            return (T) view;
        }

        public ViewHelper setText(int viewId, CharSequence value) {
            TextView view = (TextView) this.getView(viewId);
            view.setText(value);
            return this;
        }

        public ViewHelper setImageResource(int viewId, int imageResId) {
            ImageView view = (ImageView) this.getView(viewId);
            view.setImageResource(imageResId);
            return this;
        }

        public ViewHelper setBackgroundColor(int viewId, int color) {
            View view = this.getView(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        public ViewHelper setBackgroundRes(int viewId, int backgroundRes) {
            View view = this.getView(viewId);
            view.setBackgroundResource(backgroundRes);
            return this;
        }

        public ViewHelper setTextColor(int viewId, int textColor) {
            TextView view = (TextView) this.getView(viewId);
            view.setTextColor(textColor);
            return this;
        }

        public ViewHelper setImageDrawable(int viewId, Drawable drawable) {
            ImageView view = (ImageView) this.getView(viewId);
            view.setImageDrawable(drawable);
            return this;
        }

        public ViewHelper setImageBitmap(int viewId, Bitmap bitmap) {
            ImageView view = (ImageView) this.getView(viewId);
            view.setImageBitmap(bitmap);
            return this;
        }

        public ViewHelper setAlpha(int viewId, float value) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.getView(viewId).setAlpha(value);
            } else {
                AlphaAnimation alpha = new AlphaAnimation(value, value);
                alpha.setDuration(0L);
                alpha.setFillAfter(true);
                this.getView(viewId).startAnimation(alpha);
            }

            return this;
        }

        public ViewHelper setVisible(int viewId, boolean visible) {
            View view = this.getView(viewId);
            view.setVisibility(visible ? VISIBLE : GONE);
            return this;
        }

        public ViewHelper linkify(int viewId) {
            TextView view = (TextView) this.getView(viewId);
            Linkify.addLinks(view, Linkify.MAP_ADDRESSES);
            return this;
        }

        public ViewHelper setTypeface(int viewId, Typeface typeface) {
            TextView view = (TextView) this.getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | 128);
            return this;
        }

        public ViewHelper setTypeface(Typeface typeface, int... viewIds) {
            int[] var3 = viewIds;
            int var4 = viewIds.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                int viewId = var3[var5];
                TextView view = (TextView) this.getView(viewId);
                view.setTypeface(typeface);
                view.setPaintFlags(view.getPaintFlags() | 128);
            }

            return this;
        }

        public ViewHelper setProgress(int viewId, int progress) {
            ProgressBar view = (ProgressBar) this.getView(viewId);
            view.setProgress(progress);
            return this;
        }

        public ViewHelper setProgress(int viewId, int progress, int max) {
            ProgressBar view = (ProgressBar) this.getView(viewId);
            view.setMax(max);
            view.setProgress(progress);
            return this;
        }

        public ViewHelper setMax(int viewId, int max) {
            ProgressBar view = (ProgressBar) this.getView(viewId);
            view.setMax(max);
            return this;
        }

        public ViewHelper setRating(int viewId, float rating) {
            RatingBar view = (RatingBar) this.getView(viewId);
            view.setRating(rating);
            return this;
        }

        public ViewHelper setRating(int viewId, float rating, int max) {
            RatingBar view = (RatingBar) this.getView(viewId);
            view.setMax(max);
            view.setRating(rating);
            return this;
        }

        public ViewHelper setOnClickListener(int viewId, OnClickListener listener) {
            View view = this.getView(viewId);
            view.setOnClickListener(listener);
            return this;
        }

        public ViewHelper setOnTouchListener(int viewId, OnTouchListener listener) {
            View view = this.getView(viewId);
            view.setOnTouchListener(listener);
            return this;
        }

        public ViewHelper setOnLongClickListener(int viewId, OnLongClickListener listener) {
            View view = this.getView(viewId);
            view.setOnLongClickListener(listener);
            return this;
        }

        public ViewHelper setOnItemClickListener(int viewId, AdapterView.OnItemClickListener listener) {
            AdapterView view = (AdapterView) this.getView(viewId);
            view.setOnItemClickListener(listener);
            return this;
        }

        public ViewHelper setOnItemLongClickListener(int viewId, AdapterView.OnItemLongClickListener listener) {
            AdapterView view = (AdapterView) this.getView(viewId);
            view.setOnItemLongClickListener(listener);
            return this;
        }

        public ViewHelper setOnItemSelectedClickListener(int viewId, AdapterView.OnItemSelectedListener listener) {
            AdapterView view = (AdapterView) this.getView(viewId);
            view.setOnItemSelectedListener(listener);
            return this;
        }

        public ViewHelper setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
            CompoundButton view = (CompoundButton) this.getView(viewId);
            view.setOnCheckedChangeListener(listener);
            return this;
        }

        public ViewHelper setTag(int viewId, Object tag) {
            View view = this.getView(viewId);
            view.setTag(tag);
            return this;
        }

        public ViewHelper setTag(int viewId, int key, Object tag) {
            View view = this.getView(viewId);
            view.setTag(key, tag);
            return this;
        }

        public ViewHelper setChecked(int viewId, boolean checked) {
            View view = this.getView(viewId);
            if (view instanceof CompoundButton) {
                ((CompoundButton) view).setChecked(checked);
            } else if (view instanceof CheckedTextView) {
                ((CheckedTextView) view).setChecked(checked);
            }

            return this;
        }

    }

}

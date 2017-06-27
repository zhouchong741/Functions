package xxx.functions;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiae.herbs.baselib.mvp.listhelper.QuickAdapter;
import com.jiae.herbs.baselib.mvp.presenter.MvpListPresenter;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import xxx.functions.https.HttpUrl;

/**
 * 标题：Fragment列表页面父类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/17 09:56.
 */
public abstract class BaseListFragment<P extends MvpListPresenter> extends BaseFragment<P>
        implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, IBaseList {

    @BindView(android.R.id.empty)
    FrameLayout mEmptyView;
    @BindView(R.id.iv_empty)
    ImageView mIvEmpty;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    Unbinder unbinder;
    protected final AtomicInteger mPage = new AtomicInteger(1);
    private QuickAdapter mAdapter;
    private RecyclerView.AdapterDataObserver mEmptyObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            if (mAdapter != null && mEmptyView != null) {
                if (mAdapter.getItemCount() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.common_fragment_list;
    }

    @Override
    public void initView() {
        ensureList();
    }

    @Override
    public void initData() {
        loadData();
        setAdapter();
    }

    private void ensureList() {
        if (mAdapter != null) {
            return;
        }
        if (mRefreshLayout == null)
            return;
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark, R.color.colorAccent);
        if (mEmptyView != null)
            mEmptyView.setVisibility(View.GONE);
        if (mRecyclerView != null)
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        if (getPresenter() != null) {
            mAdapter = getPresenter().getAdapter();
            mAdapter.openLoadMore(getPageSize(), true);
        }
    }

    private void setAdapter() {
        if (mAdapter == null)
            return;
        mAdapter.registerEmptyViewObserver(mEmptyObserver);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mRefreshLayout.setOnRefreshListener(this);
        if (mAdapter != null)
            mAdapter.setOnLoadMoreListener(this);
    }

    @Override
    public SwipeRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public void setEmptyImage(Drawable drawable) {
        if (mIvEmpty != null)
            mIvEmpty.setImageDrawable(drawable);
    }

    @Override
    public void setEmptyImage(int resId) {
        if (mIvEmpty != null)
            mIvEmpty.setImageResource(resId);
    }

    @Override
    public void setEmptyText(CharSequence c) {
        if (mTvEmpty != null)
            mTvEmpty.setText(c);
    }

    @Override
    public void setEmptyText(int resId) {
        if (mTvEmpty != null)
            mTvEmpty.setText(resId);
    }

    @Override
    public void setBackgroundColor(int color) {
        if (mRefreshLayout != null)
            mRefreshLayout.setBackgroundColor(color);
    }

    @Override
    public void setRefreshing(boolean refresh) {
        if (mRefreshLayout != null)
            mRefreshLayout.setRefreshing(refresh);
    }

    @Override
    public void loadData() {
        if (mRefreshLayout != null && mAdapter != null) {
            openLoadingAnim();
//            setRefreshing(true);
            onRefresh();
        }
    }

    @Override
    protected void handleSuccess(int reqCode, String result) {
        super.handleSuccess(reqCode, result);
        switch (reqCode) {
            case HttpUrl.REQ_CODE_REFRESH:
                if (mAdapter != null)
                    mAdapter.clear();
                mPage.set(1);
                break;
            case HttpUrl.REQ_CODE_LOAD:
                mPage.incrementAndGet();
                break;
        }
    }

    @Override
    protected void handleError(int reqCode, Exception e) {
        super.handleError(reqCode, e);
        switch (reqCode) {
            case HttpUrl.REQ_CODE_REFRESH:
            case HttpUrl.REQ_CODE_LOAD:
                if (mAdapter != null) {
                    mAdapter.notifyDataChangedAfterLoadMore(false);
                }
                break;
        }
    }

    @Override
    protected void handleFinish(int reqCode) {
        super.handleFinish(reqCode);
        if (!isAdded()) {
            return;
        }
        switch (reqCode) {
            case HttpUrl.REQ_CODE_REFRESH:
                closeLoadingAnim();
            case HttpUrl.REQ_CODE_LOAD:
                setRefreshing(false);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        mPage.set(1);
        mAdapter.clear();
        mAdapter = null;
        mRecyclerView = null;
        mRefreshLayout = null;
        mEmptyView = null;
    }
}

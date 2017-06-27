package xxx.functions;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jiae.herbs.baselib.mvp.MvpActivity;
import com.jiae.herbs.baselib.mvp.presenter.MvpPresenter;
import com.jiae.herbs.baselib.utils.http.HttpClient;

/**
 * 标题：Activity 基类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/15 16:05.
 * <p>提供对Toolbar处理</p>
 */
public abstract class BaseActivity<P extends MvpPresenter> extends MvpActivity<P> {

    //公共标题栏
    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private View mToolbarDivider;
    private ProgressDialog mProgressDialog;
    protected HApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = HApplication.getInstance();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage(getString(R.string.loading_msg));
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            mToolbar.setSubtitle("");
            mToolbar.setTitle("");
            getSupportActionBar().setTitle("");
            getSupportActionBar().setSubtitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarDivider = findViewById(R.id.toolbar_divider);
    }

    /**
     * 隐藏标题栏
     */
    public void hintTitle() {
        mToolbar.setVisibility(View.GONE);
        mToolbarTitle.setVisibility(View.GONE);
        mToolbarDivider.setVisibility(View.GONE);
    }

    /**
     * 显示标题栏
     */
    public void showTitle() {
        mToolbar.setVisibility(View.VISIBLE);
        mToolbarTitle.setVisibility(View.VISIBLE);
        mToolbarDivider.setVisibility(View.VISIBLE);
    }

    public void lockView() {

    }

    public void unLockView() {

    }

    @Override
    protected void handleFinish(int reqCode) {
        dismissProgressDialog();
    }

    public void showProgressDialog(String message) {
        showProgressDialog(message, false);
    }

    public void showProgressDialog(int resId) {
        showProgressDialog(getStringRes(resId), false);
    }

    public String getStringRes(int resId){
        return getResources().getString(resId);
    }

    @Override
    protected int getTitleHeight() {
        return super.getTitleHeight() + getStatusBarHeight();
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 显示加载进度框
     */
    public void showProgressDialog(String message, boolean cancelable) {
        if (mProgressDialog == null)
            return;
        if (!TextUtils.isEmpty(message)) {
            mProgressDialog.setMessage(message);
        }
        mProgressDialog.setCancelable(cancelable);
        mProgressDialog.setCanceledOnTouchOutside(false);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 取消进度框
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * toast
     */
    public void showMsg(int message) {
        Toast.makeText(this, getResources().getString(message), Toast.LENGTH_SHORT).show();
    }

    /**
     * toast
     */
    public void showMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 设置标题栏背景色
     */
    public void setTitleBackground(int color) {
        mToolbar.setBackgroundColor(color);
    }

    /**
     * 设置标题文字颜色
     */
    @Override
    public void setTitleColor(int color) {
        mToolbarTitle.setText(color);
    }

    /**
     * 设置标题
     */
    @Override
    public void setTitle(CharSequence title) {
        if (mToolbarTitle != null)
            mToolbarTitle.setText(title);
    }

    /**
     * 设置标题
     */
    @Override
    public void setTitle(int titleId) {
        if (mToolbarTitle != null)
            mToolbarTitle.setText(titleId);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissProgressDialog();
        HttpClient.cancelByTag(this);
    }
}

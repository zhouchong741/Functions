package xxx.functions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.jiae.herbs.baselib.mvp.MvpFragment;
import com.jiae.herbs.baselib.mvp.presenter.MvpPresenter;


/**
 * 标题：
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/15 16:05.
 */
public abstract class BaseFragment<P extends MvpPresenter> extends MvpFragment<P> {

    protected BaseActivity activity;
    protected HApplication application;
    private ProgressDialog mProgressDialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (BaseActivity) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = HApplication.getInstance();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage(getString(R.string.loading_msg));
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
        return 0;
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
        Toast.makeText(activity, getResources().getString(message), Toast.LENGTH_SHORT).show();
    }

    /**
     * toast
     */
    public void showMsg(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}

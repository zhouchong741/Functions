package xxx.functions.modules;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.jiae.herbs.baselib.mvp.presenter.MvpPresenter;

import xxx.functions.BaseActivity;
import xxx.functions.R;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/6/27 14:53
 */

public class FunctionActivity extends BaseActivity {
    @Override
    public void success(Object result, int id) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_functions;
    }

    @Override
    public void initView() {
        setTitle("应用");

        initAnim();
    }

    private void initAnim() {
        ImageView imageView = (ImageView) findViewById(R.id.img_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public MvpPresenter newPresenter() {
        return null;
    }
}

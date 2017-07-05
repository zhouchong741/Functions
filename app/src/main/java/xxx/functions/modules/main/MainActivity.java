package xxx.functions.modules.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.jiae.herbs.baselib.view.KFragmentTabHost;

import butterknife.BindView;
import butterknife.ButterKnife;
import xxx.functions.BaseActivity;
import xxx.functions.R;
import xxx.functions.modules.home.HomeContainerFragment;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/7/5 15:11
 */

public class MainActivity extends BaseActivity<MainPresenter> implements TabHost.OnTabChangeListener {
    @BindView(android.R.id.tabhost)
    KFragmentTabHost mTabHost;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    public void onTabChanged(String tabId) {
        switch (tabId) {
            case "home":
                tvTitle.setText(R.string.home);
                break;
            case "mall":
                tvTitle.setText(R.string.mall);
                break;
            case "article":
                tvTitle.setText(R.string.article);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //底部tab
        mTabHost.setup(mContext, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(createTabItem(R.string.home,
                R.drawable.selector_tab_home)), HomeContainerFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("article").setIndicator(createTabItem(R.string.article,
                R.drawable.selector_tab_article)), HomeContainerFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mall").setIndicator(createTabItem(R.string.mall,
                R.drawable.selector_tab_mall)), HomeContainerFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("personal").setIndicator(createTabItem(R.string.mine,
                R.drawable.selector_tab_personal)), HomeContainerFragment.class, null);
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mTabHost.setOnTabChangedListener(this);
    }

    @Override
    public MainPresenter newPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    // 创建tab
    private View createTabItem(int textRes, int drawableRes) {
        View view = getLayoutInflater().inflate(R.layout.view_tab_item, null);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(textRes);
        textView.setTextColor(ContextCompat.getColorStateList(mContext, R.color.selector_main_tab));
        textView.setCompoundDrawablesWithIntrinsicBounds(0, drawableRes, 0, 0);
        return view;
    }

    @Override
    public void showTitle() {
        title.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintTitle() {
        title.setVisibility(View.GONE);
    }
}

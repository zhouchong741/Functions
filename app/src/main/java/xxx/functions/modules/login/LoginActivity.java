package xxx.functions.modules.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSONObject;
import com.jiae.herbs.baselib.utils.TimeUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import xxx.functions.BaseActivity;
import xxx.functions.Constants;
import xxx.functions.R;
import xxx.functions.https.HttpUrl;
import xxx.functions.https.HttpUtil;

import static xxx.functions.https.HttpUtil.NONCESTR;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/6/29 13:09
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {


    private TextInputEditText mUserName;
    private TextInputEditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void login() {
        long phone = Long.parseLong(mUserName.getText().toString().trim());
        String pass = mPassword.getText().toString().trim();
        JSONObject object = new JSONObject();
        object.put("templeId", 1);
        object.put("mobile", phone);
        object.put("password", pass);
        object.put("sysType", "A");
        object.put("appKey", Constants.APPKEY);
        //object.put("key", Constants.SECRET);
        object.put("timeStamp", TimeUtil.timeStamp() / 1000);
        object.put("deviceId", HttpUtil.getDeviceId(mContext));
        //String randomString = SignUtils.getRandomString(32);
        object.put("nonceStr",NONCESTR);
        object.put("sign", HttpUtil.getSign());
        getPresenter().login(HttpUrl.LOGIN_VALIDATE, HttpUrl.SIYUANZAIXIAN, object.toJSONString(), getStringRes(R.string.waiting_login), HttpUrl.REQ_CODE_LOGIN);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setTitle("登录");
        mUserName = (TextInputEditText) findViewById(R.id.user_name);
        mPassword = (TextInputEditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public LoginPresenter newPresenter() {
        return new LoginPresenter();
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
    }
}

package xxx.functions.modules.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.jiae.herbs.baselib.utils.MD5Utils;
import com.jiae.herbs.baselib.utils.SignUtils;
import com.jiae.herbs.baselib.utils.TimeUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xxx.functions.BaseActivity;
import xxx.functions.Constants;
import xxx.functions.R;
import xxx.functions.https.HttpUrl;
import xxx.functions.https.HttpUtil;
import xxx.functions.modules.register.RegisterActivity;

import static xxx.functions.https.HttpUtil.createParaString;
import static xxx.functions.https.HttpUtil.paraFilter;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/6/29 13:09
 */

public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.user_name)
    TextInputEditText mUserName;
    @BindView(R.id.password)
    TextInputEditText mPassword;
    @BindView(R.id.go_to_register)
    TextView goToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setTitle("登录");

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


    private void login() {
        long phone = Long.parseLong(mUserName.getText().toString().trim());
        String pass = mPassword.getText().toString().trim();
        JSONObject object = new JSONObject();
        object.put("templeId", Constants.TEMPLEID);
        object.put("mobile", phone);
        object.put("password", pass);
        object.put("sysType", Constants.SYSTYPE);
        object.put("appKey", Constants.APPKEY);
        object.put("timeStamp", TimeUtil.timeStamp() / 1000);
        object.put("deviceId", HttpUtil.getDeviceId(mContext));
        String randomString = SignUtils.getRandomString(32);
        object.put("nonceStr", randomString);

        //签名
        Map<String, String> map = new HashMap<>();
        map.put("templeId", String.valueOf(Constants.TEMPLEID));
        map.put("mobile", String.valueOf(phone));
        map.put("password", pass);
        map.put("sysType", Constants.SYSTYPE);
        map.put("appKey", Constants.APPKEY);
        map.put("timeStamp", String.valueOf(TimeUtil.timeStamp() / 1000));
        map.put("nonceStr", randomString);
        map.put("token", "");
        map.put("deviceId", HttpUtil.getDeviceId(mContext));
        String paraTemp = createParaString(paraFilter(map)) + "&key=" + Constants.SECRET;
        String sign = MD5Utils.MD5Encode(paraTemp, "UTF-8").toUpperCase();

        object.put("sign", sign);
        getPresenter().login(HttpUrl.LOGIN_VALIDATE, HttpUrl.SIYUANZAIXIAN, object.toJSONString(), getStringRes(R.string.waiting_login), HttpUrl.REQ_CODE_LOGIN);
    }

    @OnClick({R.id.login, R.id.go_to_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.go_to_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }
    }
}

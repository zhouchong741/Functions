package xxx.functions.modules.register;

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
import xxx.functions.modules.login.LoginActivity;

import static xxx.functions.https.HttpUtil.createParaString;
import static xxx.functions.https.HttpUtil.paraFilter;

/**
 * 作者:
 * 描述:
 * 时间:2017/6/29
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> {
    @BindView(R.id.user_name)
    TextInputEditText mUserName;
    @BindView(R.id.password)
    TextInputEditText mPassword;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.go_to_login)
    TextView goToLogin;
    @BindView(R.id.ver_code)
    TextInputEditText mVerCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        setTitle("注册");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public RegisterPresenter newPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    private void register() {
        long phone = Long.parseLong(mUserName.getText().toString().trim());
        String pass = mPassword.getText().toString().trim();
        String verCode = mVerCode.getText().toString().trim();
        JSONObject object = new JSONObject();
        object.put("templeId", Constants.TEMPLEID);
        object.put("mobile", phone);
        object.put("password", pass);
        object.put("sysType", Constants.SYSTYPE);
        object.put("appKey", Constants.APPKEY);
        //object.put("key", Constants.SECRET);
        object.put("timeStamp", TimeUtil.timeStamp() / 1000);
        object.put("deviceId", HttpUtil.getDeviceId(mContext));
        String randomString = SignUtils.getRandomString(32);
        object.put("nonceStr", randomString);
        object.put("verCode", verCode);

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
        map.put("verCode", verCode);
        map.put("deviceId", HttpUtil.getDeviceId(mContext));
        String paraTemp = createParaString(paraFilter(map)) + "&key=" + Constants.SECRET;
        String sign = MD5Utils.MD5Encode(paraTemp, "UTF-8").toUpperCase();

        object.put("sign", sign);

        getPresenter().register(HttpUrl.REGISTER, HttpUrl.SIYUANZAIXIAN, object.toJSONString(), getStringRes(R.string.waiting_register), HttpUrl.REQ_CODE_REGISTER);
    }

    @OnClick({R.id.register, R.id.go_to_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                register();
                break;
            case R.id.go_to_login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}

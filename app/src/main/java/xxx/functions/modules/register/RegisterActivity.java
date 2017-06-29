package xxx.functions.modules.register;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        register();
    }

    private void register() {
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
        String randomString = SignUtils.getRandomString(32);
        object.put("nonceStr", randomString);
        object.put("verCode","aaa");

        //签名
        Map<String, String> map = new HashMap<>();
        map.put("templeId", "1");
        map.put("mobile", String.valueOf(phone));
        map.put("password", pass);
        map.put("sysType", "A");
        map.put("appKey", Constants.APPKEY);
        map.put("timeStamp", String.valueOf(TimeUtil.timeStamp() / 1000));
        map.put("nonceStr", randomString);
        map.put("token", "");
        map.put("verCode","aaa");
        map.put("deviceId", HttpUtil.getDeviceId(mContext));
        String paraTemp = createParaString(paraFilter(map)) + "&key=" + Constants.SECRET;
        String sign = MD5Utils.MD5Encode(paraTemp, "UTF-8").toUpperCase();

        object.put("sign", sign);

        getPresenter().register(HttpUrl.REGISTER, HttpUrl.SIYUANZAIXIAN, object.toJSONString(), getStringRes(R.string.waiting_register), HttpUrl.REQ_CODE_LOGIN);
    }
}

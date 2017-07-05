package xxx.functions.modules.login.data;

import android.os.Parcel;

import com.jiae.herbs.baselib.mvp.bean.Data;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/7/5 13:36
 */

public class LoginResultData extends Data {


    /**
     * token : e47cb356d7fe40e29892354763b0bb09
     * imuser : {"custid":"21133","nickname":"","realname":"","firstname":"","secondname":"","pinyinname":"","custtype":"1","idenum":"30634","status":"1","sex":"1","birthday":"0000-00-00","age":"0","country":"","zone":"","area":"","address":"","hometown":"","mobile":"13000000001","email":"","username":"","signature":"","description":"","auth":"040f876f07bba71688df22cf125c999f","webauth":"d241ec432cf976b78a2cd29c016311e7","lastloginip":"114.55.53.55","logintimes":"12","isonline":"1","issuper":"0","pid":"0","createtime":"2017-06-29 20:52:15","updatetime":"2017-06-29 20:52:15","msgsetting":{"shock":"0","beep":"0","push":"0"},"dynamicsetting":"","interests":"","custsource":"1","university":"","school":"","majorclass":"","entranceyear":"0000-00-00","sdtno":"","qq":"0","contact":"","contactmobile":"","iskitchener":"0","backgroundimg":[],"ordnum":"0","dishes":"","videourl":"","identitycard":"","kitchencard":"","healthcard":"","token":"dBLMpeLw5c7yBek98xrLfrm3tub-fHhB-F9NI5jeWCvI9eIX_jydHg4TJNA1GGJV","ucrtoken":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhcHBfaWQiOiJxaXl1bnhpbiIsImV4cCI6MTQ5OTQ5MjA3NCwiaWF0IjoxNDk5MjMyODc0LCJyX2lkIjoiMjExMzMiLCJzdWIiOiI5N2ViMmY2MDI2ZTU0NmE0ODhkMThjZDUxZWU4OWY4MiJ9.g95odW2c8ZkqLfbK_TyM_rvuMJshWA_MF0JJKoU6wUJK2SigIIAg9SV-hIghJk56l6SR915vnDG2CRKFsF0_j7cp6l5Obx4XHYl_iNtHBI61qS7DlnDdkoNzgTk_mVmxBwa9OnJ5L0GdVtrUTyJSKSkAx664movMT86lNAzOkqg","ucropenid":"97eb2f6026e546a488d18cd51ee89f82"}
     * templeuser : {"id":10,"createdOn":1498740736000,"appId":1,"wxOpenId":null,"templeId":1,"nickname":null,"updatedOn":null,"mobile":"13000000001"}
     */

    private String token;
    private ImUserData imuser;
    private TempleUserData templeuser;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeParcelable(this.imuser, flags);
        dest.writeParcelable(this.templeuser, flags);
    }

    public LoginResultData() {
    }

    public LoginResultData(Parcel source) {
        this.token = source.readString();
        this.imuser = source.readParcelable(ImUserData.class.getClassLoader());
        this.templeuser = source.readParcelable(TempleUserData.class.getClassLoader());
    }

    public static final Creator<LoginResultData> CREATOR = new Creator<LoginResultData>() {
        @Override
        public LoginResultData createFromParcel(Parcel source) {
            return new LoginResultData(source);
        }

        @Override
        public LoginResultData[] newArray(int size) {
            return new LoginResultData[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ImUserData getImuser() {
        return imuser;
    }

    public void setImuser(ImUserData imuser) {
        this.imuser = imuser;
    }

    public TempleUserData getTempleuser() {
        return templeuser;
    }

    public void setTempleuser(TempleUserData templeuser) {
        this.templeuser = templeuser;
    }
}

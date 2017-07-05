package xxx.functions.modules.login.data;

import android.os.Parcel;

import com.jiae.herbs.baselib.mvp.bean.Data;

/**
 * 标题：
 * 作者：zc741
 * 版本：
 * 创建时间：on 2017/7/5 13:38
 */

public class TempleUserData extends Data {


    /**
     * id : 10
     * createdOn : 1498740736000
     * appId : 1
     * wxOpenId : null
     * templeId : 1
     * nickname : null
     * updatedOn : null
     * mobile : 13000000001
     */

    private int id;
    private long createdOn;
    private int appId;
    private int wxOpenId;
    private int templeId;
    private String nickname;
    private long updatedOn;
    private String mobile;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeLong(this.createdOn);
        dest.writeInt(this.appId);
        dest.writeInt(this.wxOpenId);
        dest.writeInt(this.templeId);
        dest.writeString(this.nickname);
        dest.writeLong(this.updatedOn);
        dest.writeString(this.mobile);
    }

    public TempleUserData() {
    }

    public TempleUserData(Parcel source) {
        this.id = source.readInt();
        this.createdOn = source.readLong();
        this.appId = source.readInt();
        this.wxOpenId = source.readInt();
        this.templeId = source.readInt();
        this.nickname = source.readString();
        this.updatedOn = source.readLong();
        this.mobile = source.readString();
    }

    public static final Creator<TempleUserData> CREATOR = new Creator<TempleUserData>() {
        @Override
        public TempleUserData createFromParcel(Parcel source) {
            return new TempleUserData(source);
        }

        @Override
        public TempleUserData[] newArray(int size) {
            return new TempleUserData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public Object getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(int wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public int getTempleId() {
        return templeId;
    }

    public void setTempleId(int templeId) {
        this.templeId = templeId;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Object getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

package xxx.functions.data;

import android.os.Parcel;

import com.jiae.herbs.baselib.mvp.bean.Data;

/**
 * 标题：网络请求公共模版类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/16 12:26.
 */
public class RespondData extends Data {
    private String data;
    private String code;
    private String msg;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.data);
        dest.writeString(this.code);
        dest.writeString(this.msg);
    }

    public RespondData() {
    }

    protected RespondData(Parcel in) {
        this.data = in.readString();
        this.code = in.readString();
        this.msg = in.readString();
    }

    public static final Creator<RespondData> CREATOR = new Creator<RespondData>() {
        @Override
        public RespondData createFromParcel(Parcel source) {
            return new RespondData(source);
        }

        @Override
        public RespondData[] newArray(int size) {
            return new RespondData[size];
        }
    };

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

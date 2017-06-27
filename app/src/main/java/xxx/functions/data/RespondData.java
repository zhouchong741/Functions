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
    private String statusCode;
    private String message;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.data);
        dest.writeString(this.statusCode);
        dest.writeString(this.message);
    }

    public RespondData() {
    }

    protected RespondData(Parcel in) {
        this.data = in.readString();
        this.statusCode = in.readString();
        this.message = in.readString();
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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

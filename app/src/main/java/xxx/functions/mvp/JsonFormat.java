package xxx.functions.mvp;

import android.text.TextUtils;

/**
 * 标题：服务器返回Json格式化类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/20 11:28.
 */
public class JsonFormat {

    public static String delete(String delete, String json) {
        if (TextUtils.isEmpty(json)) {
            return "";
        }
        if (json.contains(delete)) {
            return json.replaceAll(delete, "");
        }
        return json;
    }

}

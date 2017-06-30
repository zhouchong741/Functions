package xxx.functions.https;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.jiae.herbs.baselib.utils.SignUtils;
import com.jiae.herbs.baselib.utils.UIUtil;
import com.jiae.herbs.baselib.utils.http.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 标题：网络工具类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/22 13:39.
 */
public class HttpUtil {

    private static final String INSTALLATION = "INSTALLATION";
    private static String deviceId;
    private static String sID = null;

    /**
     * 组装请求参数
     */
    public static RequestParam packPostParams(Context context, RequestParam params) {
        if (params == null)
            params = new RequestParam();
        // 用户令牌,登录凭证
        /*UserManager userManager = HApplication.getInstance().getAppConfig().userManager();
        if (userManager.isLogin()) {
            params.put("token", userManager.getUser().getAccessToken());
        } else {
            params.put("token", "1");
        }*/

        //params.put("token", "");

        // 设备类型
        //params.put("sysType", "A");
        // 设备id
        String deviceId = getDeviceId(context);
        //params.put("deviceId", deviceId);
        // 时间戳
        //params.put("timeStamp", TimeUtil.timeStamp() / 1000);
        // templeId
        //params.put("templeId", 5);
        // 应用ID
        //params.put("clientId", Constants.CLIENT_ID);
        // appKey
        //params.put("appKey", Constants.APPKEY);
        // 随机字符串
        String randomString = SignUtils.getRandomString(32);
        //params.put("nonceStr", randomString);
        // 签名
        /*String str = "sysType=A" + "&"
                + "deviceId=" + deviceId + "&"
                + "appKey=" + Constants.APPKEY + "&"
                + "token=" + "&"
                + "nonceStr=" + randomString + "&"
                + "key=" + Constants.SECRET;*/
        // 排序
        /*Map<String, String> map = new HashMap<>();
        map.put("sysType", "A");
        map.put("deviceId", deviceId);
        map.put("appKey", Constants.APPKEY);
        map.put("nonceStr", randomString);
        map.put("mobile", "13000000001");
        map.put("password", "passWd");
        map.put("templeId", "1");
        map.put("timeStamp", String.valueOf(TimeUtil.timeStamp() / 1000));
        //map.put("key", Constants.SECRET);
        map.put("token", "");
        String paraTemp = createParaString(paraFilter(map)) + "&key=" + Constants.SECRET;
        String sign = MD5Utils.MD5Encode(paraTemp, "UTF-8").toUpperCase();*/
        //params.put("sign", sign);
        return params;
    }


    // 排序
    public static String createParaString(Map<String, String> params) {
        java.util.List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    // 除空值
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }


    /**
     * 获取设备id
     */
    public static String getDeviceId(Context context) {
        if (TextUtils.isEmpty(deviceId)) {
            synchronized (HttpUtil.class) {
                try {
                    if (TextUtils.isEmpty(deviceId)) {
                        TelephonyManager telephonyManager = (TelephonyManager) context
                                .getSystemService(Context.TELEPHONY_SERVICE);
                        deviceId = telephonyManager.getDeviceId();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (TextUtils.isEmpty(deviceId))
                        deviceId = getUUId(context);
                }
            }
        }
        return deviceId;
    }

    /**
     * 判断是否有下一页
     *
     * @param list
     * @return
     * @Description:
     */
    public static boolean checkHasMoreItems(List<?> list) {
        return checkHasMoreItems(list, HttpUrl.PAGESIZE);
    }

    public static boolean checkHasMoreItems(List<?> list, int maxItems) {
        return !(list == null || list.isEmpty() || list.size() < maxItems);
    }


    /**
     * 判断网络连接状态
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        if (netInfo != null) {
            return netInfo.isConnected();
        }
        return false;
    }

    /**
     * 获取与设备无关的唯一ID
     * 作为DeviceId的替代识别码
     */
    public synchronized static String getUUId(Context context) {
        if (sID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists())
                    writeInstallationFile(installation);
                sID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }

    public static String makeImageUrl(Context context, String url, int width) {
        if (TextUtils.isEmpty(url) || width == -1)
            return url;
        if (width == 0)
            width = UIUtil.getDisplayScreenWidth(context) / 2;
        if (!checkImageExtension(url.substring(url.length() - 4, url.length())))
            return url;
        String s = url + "@" + width + "w" + "_1l";
        s = url.endsWith(".gif") ? s + ".gif" : s;
        return s;
    }

    private static String[] IMAGE_URL = {"jpg", "jpeg", "gif", "png", "webp"};

    private static boolean checkImageExtension(String substring) {
        substring = substring.replace(".", "");
        StringBuilder builder = new StringBuilder();
        for (char c : substring.toCharArray()) {
            char back = Character.toLowerCase(c);
            builder.append(back);
        }
        String s = builder.toString();
        builder = null;
        for (String url : IMAGE_URL) {
            if (s.contains(url))
                return true;
        }
        return false;
    }

}

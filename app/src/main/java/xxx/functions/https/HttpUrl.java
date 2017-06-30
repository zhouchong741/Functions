package xxx.functions.https;

/**
 * 标题：http请求工具类
 * 作者：
 * 版本：
 * 创建时间：on 2017/5/17 10:18.
 */
public class HttpUrl {

    /*---------------------------------------------服务器地址---------------------------------------*/
    /**
     * 服务器地址
     */
    public static final String SERVER_HOST = "http://114.55.53.55:28080";
//    public static final String SERVER_HOST = "http://10.10.1.130:8080/api/app";
    /*---------------------------------------------v1.0---------------------------------------*/
    /**
     * 登录验证
     */
    public static final String LOGIN_VALIDATE = "/api/app/user/login";
    /**
     * 注册用户
     */
    public static final String REGISTER = "/api/app/user/add";
    /**
     * 发送短信验证码
     */
    public static final String SEND_VALIDATE_CODE_ = "/api/app/user/phoneCode";
    /**
     * 重置密码短信验证码
     */
    public static final String PASSWORD_VALIDATE_CODE = "/rest/verificode/password";
    /**
     * 检测验证码
     */
    public static final String CHECK_VALIDATE_CODE = "/rest/check_verificode";
    /**
     * 重置密码
     */
    public static final String UPDATE_PASSWORD = "/api/app/user/updatePwd";
    /**
     * 登出接口
     */
    public static final String LOGOUT = "/api/app/user/logout";
    /**
     * 用户更新头像
     */
    public static final String AVATAR = "/rest/avatar";

    /*---------------------------------------------网络请求码---------------------------------------*/

    /**
     * 公共刷新请求码
     */
    public static final int REQ_CODE_REFRESH = 200;
    /**
     * 数据重复(注册时)
     */
    public static final int REQ_CODE_REPEAT = 202;
    /**
     * 服务器错误码
     */
    public static final int REQ_CODE_SERVER_ERROR = 500;
    /**
     * 5000 授权失败
     */
    public static final int REQ_CODE_FAIL = 5000;

    /**
     * 公共加载请求码
     */
    public static final int REQ_CODE_LOAD = 201;
    /**
     * 普通码
     */
    public static final int REQ_CODE_GET = 101;
    public static final int REQ_CODE_POST = 102;
    public static final int REQ_CODE_PUT = 110;
    public static final int REQ_CODE_DELETE = 111;
    public static final int REQ_CODE_UPDATE = 112;
    public static final int REQ_CODE_CANCEL = 113;
    public static final int REQ_CODE_SAVE = 103;
    public static final int REQ_CODE_COMMIT = 104;
    public static final int REQ_CODE_LOGIN = 105;
    public static final int REQ_CODE_REGISTER = 106;

    /*---------------------------------------------网络请求码---------------------------------------*/

    /*---------------------------------------------列表长度-----------------------------------------*/

    /**
     * 请求列表长度
     */
    public static final int PAGESIZE = 10;

    /*---------------------------------------------列表长度-----------------------------------------*/

    /**
     * 服务器类型
     */
    public static final String SIYUANZAIXIAN = "gycs";

    public static String makeUrl(String url, String tag) {
        String result = "";
        switch (tag) {
            case SIYUANZAIXIAN:
                result = SERVER_HOST + url;
                break;
        }
        return result;
    }
}

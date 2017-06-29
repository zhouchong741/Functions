package xxx.functions.https;

/**
 * 标题：http请求工具类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/17 10:18.
 */
public class HttpUrl {

    /*---------------------------------------------服务器地址---------------------------------------*/
    /**
     * 服务器地址
     */
//    public static final String SERVER_HOST = "http://test.herbs-mobile.jiae.com";
    //public static final String SERVER_HOST = "http://t.mobile.bfengtang.com";
    public static final String SERVER_HOST = "http://114.55.53.55:28080/api/app";
//    public static final String SERVER_HOST = "http://10.10.1.130:8080/api/app";
    /*---------------------------------------------v1.0---------------------------------------*/

    /**
     * 首页接口
     */
    public static final String HOME = "/rest/home";

    /**
     * 文章列表
     */
    public static final String ARTICLES = "/rest/articles";
    /**
     * 文章分类
     */
    public static final String ARTICLE_CATALOGS = "/rest/article/catalogs";
    /**
     * 文章详情网页
     */
    public static final String WEB_ARTICLE_DETAIL = "/articleDetail";

    /**
     * 商品列表接口
     */
    public static final String PRODUCTS = "/rest/products";

    /**
     * 商品详情
     */
    public static final String PRODUCT_DETAIL = "/rest/product_detail/%s";

    /**
     * 商品详情页网页url
     */
    public static final String PRODUCT_DETAIL_WEB = "/productdetail";

    /**
     * 商品评论
     */
    public static final String PRODUCT_COMMENTS = "/rest/product_comments";

    /**
     * 商品咨询
     */
    public static final String PRODUCT_CONSULTINGS = "/rest/product/consultings";

    /**
     * 购物车列表
     */
    public static final String SHOPPING_CART = "/rest/shoppingcart";

    /**
     * 收货地址列表
     */
    public static final String ADDRESSES = "/rest/addresses";
    /**
     * 更新默认地址
     */
    public static final String DEFAULT_ADDRESS = "/rest/default_address";

    /**
     * 浏览跟踪
     */
    public static final String BROWS_TRACKS = "/rest/browsing_tracks";

    /**
     * 积分
     */
    public static final String INTEGRAL = "/rest/score";

    /**
     * 购物车商品操作接口
     * <p>
     * put 请求 更新购物车中商品 返回更新后商品数据量
     * post 请求 商品加入购物车
     * DELETE 请求 删除购物车中商品
     */
    public static final String OPERATE_CART_ITEM = "/rest/cartitems";

    /**
     * 支付宝支付回调地址
     */
    public static final String ALIPAY_NOTIFY = "alipayNotify";

    /**
     * 第三方登录接口
     */
    public static final String WEIXIN_LOGIN = "/rest/login/weixin";
    /**
     * 微信绑定账号
     */
    public static final String WEIXIN_BIND = "/rest/weixin/binding";
    /**
     * 登录验证
     */
    public static final String LOGIN_VALIDATE = "/user/login";
    /**
     * 注册短信验证码
     */
    public static final String SIGNUP_VALIDATE_CODE = "/rest/verificode/signup";
    /**
     * 重置密码短信验证码
     */
    public static final String PASSWORD_VALIDATE_CODE = "/rest/verificode/password";
    /**
     * 检测验证码
     */
    public static final String CHECK_VALIDATE_CODE = "/rest/check_verificode";
    /**
     * 注册用户
     */
    public static final String REGISTER = "/rest/signup";
    /**
     * 重置密码
     */
    public static final String PASSWORD = "/rest/password/reset";
    /**
     * 登出接口
     */
    public static final String LOGOUT = "/rest/logout";
    /**
     * 优惠券接口
     */
    public static final String USER_COUPONS = "/rest/user_coupons";
    /**
     * 获取用户订单接口
     */
    public static final String ORDERS = "/rest/orders";
    /**
     * 取消订单
     */
    public static final String CANCEL_ORDER = "/rest/cancel_order";
    /**
     * 删除订单
     */
    public static final String DELETE_ORDER = "/rest/delete_order";
    /**
     * 订单确认（结算接口）
     */
    public static final String ORDER_SETTLEMENT = "/rest/settlement";
    /**
     * 三级城市获取
     */
    public static final String DISTRICTS = "/rest/districts";
    /**
     * 获取心愿单
     */
    public static final String WISH_LIST = "/rest/wishlist";
    /**
     * 商品加入心愿单
     * 心愿单中删除商品
     */
    public static final String WISH_LIST_ITEMS = "/rest/wishlistitems";
    /**
     * 热门搜索数据
     */
    public static final String SEARCH_TAGS = "/rest/search/tags";
    /**
     * 搜索接口
     */
    public static final String SEARCH = "/rest/search";
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
    public static final int REQ_CODE_ONE = 106;
    public static final int REQ_CODE_TWO = 107;
    public static final int REQ_CODE_THREE = 108;
    public static final int REQ_CODE_FOUR = 109;

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

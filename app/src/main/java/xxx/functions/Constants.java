package xxx.functions;

/**
 * 标题：常量类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/31 15:03.
 */
public class Constants {

	/**
	 * 接口签名数据
	 */
	public static final String CLIENT_ID = "rps0j922t";
	public static final String CLIENT_SECRET = "2l6ccipguvhan12k23qssku96tl";
	/**
	 * 支付方式
	 */
	//支付宝支付
	public static final int PAYMENT_ALI = 101;
	//微信支付
	public static final int PAYMENT_WX = 102;
	//银联支付
	public static final int PAYMENT_UNION = 103;

	public static final String THREE_SHARE = "share";
	public static final String THREE_LOGIN = "login";

	/** 微信登录 **/
	public static final String WECHAT_APPID = "wx15b660090f78e727";
	public static final String WECHAT_SECRET = "d900c380bd0cb94062d872d727ecedd9";

	/** 支付宝签约者id **/
	public static final String ALIPAY_PARTNER = "2016080600181431";
	/** 支付宝商家帐号 **/
//	public static final String ALIPAY_SELLER = "apple@jiae.com";
	public static final String ALIPAY_SELLER = "xgibvw3024@sandbox.com";
	/**支付宝商户私钥**/
	public static final String ALIPAY_RSA_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCuHDnUJUWtP8wjXWAKt1yZd6OUgKgnWILAO0bMId6qLodDQPbUCHm2ZvIv4VlwaXHipI1GB4oJzTTb7LXrV6fmAgC/O5D10t/uuV4GL1/Wi0PqRaN/MHvuhUVG1TKV8E3jujxHngnK6usWg9K9mvBz1AwW3NPvdD1Ea2FJgsje8YsmwgON5GOCrWH09tju7AY3UzrjiFMZLSKhDXJk2CAiIa27s4tWpe/Ml/mEnv1Ku8oEtv2nI0x5SV7JS4oONel+g7vpeHybMG04ezjLllGDcZYREDftpQ9u+a9fvtAkRVxIjigbUZ0Wnc8WuAHBqzYFW12hY5HIaYzW0dallOMDAgMBAAECggEACCEhQhqLak90RKJzfYyaMkA3zxNCxrOHR1p9L8LVFrUdc7Jw0+2hYTblT/8BohIKUz7DqeVoOJTwRuSCif6JxF+LkWJCnne+JPNaxIcpvma6QRIHVET7RGPMnEYQGpghyuRDNETYyDS3zVFSYa/+UiowdlDRRHj082DgJgKJRn+EpkUXAJMGlO+ljb+9W8yhf6ZLiMnIyD0HEI6nCehtwJAuskqaKtsudFu792LK0M8Mbj8CC9JDa2AVgBSb4pCCskrwBjT6XRgeOHczC5h7tj49boiUy9ihQWf3UYVC94dVvVxXumo0TtIRf7ccJOK0PWRbCqckD+OQjnuZVrpA4QKBgQD99ooINY5Gfw/BV09G2pdxsDykGsEgnMSB2jzkn4g+j8AcsNnaBb+/aFqpH3gjRO/d58NparvVbbyNNtYpaQV4654+Db1KWAPeVHDRBzTDsrlhohsPggjeWCIwQTBnwONmUVuXgyuwzixWyAppu9NWWx1ckOSOntwU52vGdfR/5wKBgQCvgbm3RhicLkNmmzQxvdOijyMJ+jHQ77VRqzsA1xewoZzcISmiIpsesbFoOmvwJG1zMxWwG+sK0bLk962ZmnKX064VBSSPdCiZjOpSHcUACMafHnm7SrIvlJB9/baGGuhhU7f+y4VnAqOjqVCTVpaGTtXlCZ7pJDrGZ4WIy/AQhQKBgDBPnPRw067fbuzVohQVcyfCYdbJ7CE7ETqufFT1cCKOj/olPlv2KgLMZCq1/NGgxIyPESJVloPES1EFtHnOK6dUExc03+Rrl9jMcWWiHEx6DD/haYpqrys00tQhIocqL1gAdA7/eek5Xz/m7+wrOcm8rXo1afUjjTRAzcTEHY3TAoGAcLSX9M/44eL7uZqVrBPBIrVUxQV8lDY7dFTHsURvnChMuTg6qjoa710hFlPeGPrrpnz5wnxLNklv3oYHX4rFaCaxtiH1VoI7yE+r8kvXxKveETTa66GOR+vZ1m7llb0V2dqPX/qGkp4pQ+JpMhT62QkrZsgE2qc/b0XvhOeiJJkCgYEAgdA69qiyoNmG/TPzp9uGXZk85qeCYLmQ58+oBYfNOpumpyehL5e87nlZvWNkyzzwZYySSd+pRBlYY3tjjoE5z8paybwStOrcd0IHS34vGoTxs/cF9ckWHyJTPYWhC1VV8OrCRG/6FqZzpxuzVX4ms5OzdaE2nt3AQBDlU4u78I8=";
	/** 支付宝商户公钥 **/
	public static final String ALIPAY_RSA_PUBLIC = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArhw51CVFrT/MI11gCrdcmXejlICoJ1iCwDtGzCHeqi6HQ0D21Ah5tmbyL+FZcGlx4qSNRgeKCc002+y161en5gIAvzuQ9dLf7rleBi9f1otD6kWjfzB77oVFRtUylfBN47o8R54JyurrFoPSvZrwc9QMFtzT73Q9RGthSYLI3vGLJsIDjeRjgq1h9PbY7uwGN1M644hTGS0ioQ1yZNggIiGtu7OLVqXvzJf5hJ79SrvKBLb9pyNMeUleyUuKDjXpfoO76Xh8mzBtOHs4y5ZRg3GWERA37aUPbvmvX77QJEVcSI4oG1GdFp3PFrgBwas2BVtdoWORyGmM1tHWpZTjAwIDAQAB";

	/** QQ **/
	public static final String QZONE_APPID = "1104792987";
	public static final String QZONE_APPKEY = "vvhkLOx9rAwE5gwl";

	/** 微博 **/
    public static final String SINA_APP_KEY = "3213756066";
	public static final String SINA_APPKEY = "065ad6fad3b13af5ac1f15457bd388c8";
	//默认回调页
	public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    //Scope 是 OAuth2.0 授权机制中 authorize 接口的一个参数。通过 Scope，平台将开放更多的微博
    //核心功能给开发者，同时也加强用户隐私保护，提升了用户体验，用户在新 OAuth2.0 授权页中有权利
    //选择赋予应用的功能。
	public static final String SINA_SCOPE ="";
//			"email,direct_messages_read,direct_messages_write,"
//					+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
//					+ "follow_app_official_microblog," + "invitation_write";

	public static final String SOCIAL_UMENG_SHARE = "com.umeng.share";
	public static final String SOCIAL_UMENG_LOGIN = "com.umeng.login";

	/** channel **/
	public static final String UMENG_CHANNEL_PP = "pp";
	public static final String UMENG_CHANNEL_BAIDU = "baidu";
	public static final String UMENG_CHANNEL_TONGYONG = "tongyong";
	public static final String CHANNEL_LETV = "letv";
	public static final String CHANNEL_YINGYONGBAO = "yingyongbao";
	public static final String CHANNEL_HUAWEI = "huawei";
	public static final String CHANNEL_360 = "c360";

	/** 服务图片压缩质量 **/
	public static final int SERVICE_IMAGE_QUALITY = 70;
	public static final String APK_DOWNLOAD_URL = "";

	/** List列表最大长度 超过此长度才能加载更多**/
	public static final int LIST_MAX_ITEMS = 10;

	/**
	 * google开发者客户端ID
	 */
	public static final String GOOGLE_GCM_APPID = "666104285757-ocmumo3vhk4na35qn0mjigpha33p384f.apps.googleusercontent.com";
	/**
	 * 华为push
	 */
	public static final String HUAWEI_APPID = "10396739";

	/**
	 * 环信Im测试AppKey
	 */
	public static final String EASEMOB_APPKEY_TEST = "1142161213178303#easemobtest";
}

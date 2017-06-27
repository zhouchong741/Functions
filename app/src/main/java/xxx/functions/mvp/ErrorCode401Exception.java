package xxx.functions.mvp;

/**
 * 标题：401：无效令牌或令牌已过期，需要重新登录
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/19 15:38.
 */
public class ErrorCode401Exception extends ErrorCodeException {

    public ErrorCode401Exception(String message) {
        super(message);
    }
}

package xxx.functions.mvp;

/**
 * 标题：自定义错误码异常
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/6/1 17:37.
 * <p>
 *     通过网络向服务器请求数据成功后，返回数据状态码不是200（即服务器返回数据错误），
 *     此时将返回ErrorCodeException异常
 * </p>
 */
public class ErrorCodeException extends Exception {

    public ErrorCodeException(String message) {
        super(message);
    }

}

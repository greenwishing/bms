package cn.greenwishing.bms.exception;

/**
 * @author Wufan
 * @date 2018/12/16
 */
public class BmsException extends RuntimeException {

    public BmsException() {
    }

    public BmsException(String message) {
        super(message);
    }

    public BmsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BmsException(Throwable cause) {
        super(cause);
    }

    public BmsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

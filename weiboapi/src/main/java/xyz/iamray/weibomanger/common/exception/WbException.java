package xyz.iamray.weibomanger.common.exception;

/**
 * @author winray
 * @since v1.0.1
 * 微博机器人异常封装
 */
public class WbException extends RuntimeException{

    public WbException(Exception e){
        super(e);
    }

    public WbException(String exceptionMessage){
        super(exceptionMessage);
    }
}

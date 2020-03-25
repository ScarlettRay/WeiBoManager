package xyz.iamray.weiboapi.common;

/**
 * @author winray
 * @since v1.0.1
 */
public enum Status {

    OK(200,"ok"),
    ERR(500,"fail"),
    NO(600,"传入的参数为空!");

    private Status(int code,String description){
        this.code = code;
        this.description = description;
    }


    public int code;
    public String description;
}

package xyz.iamray.weiboapi.common;

/**
 * @author winray
 * @since v1.0.1
 */
public enum Status {

    OK(200,"ok"),
    ERR(500,"fail");

    private Status(int code,String description){
        this.code = code;
        this.description = description;
    }


    private int code;
    private String description;
}

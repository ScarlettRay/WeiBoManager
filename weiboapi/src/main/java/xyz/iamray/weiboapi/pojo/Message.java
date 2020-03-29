package xyz.iamray.weiboapi.pojo;

import lombok.Data;

/**
 * @author winray
 * @since v1.0.1
 */
@Data
public class Message {

    private String id;
    private String text;
    private Type type;//私信还是群消息
    private Integer unread;//未读消息数

    enum Type{
        U,G;
    }
}

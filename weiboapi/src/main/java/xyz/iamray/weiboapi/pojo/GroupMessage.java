package xyz.iamray.weiboapi.pojo;

import lombok.Data;

/**
 * @author winray
 * @since v1.0.1
 * 群消息
 */
@Data
public class GroupMessage {

    private String gid;

    private String content;
}

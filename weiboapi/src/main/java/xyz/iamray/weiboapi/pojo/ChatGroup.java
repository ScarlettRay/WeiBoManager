package xyz.iamray.weiboapi.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 聊天群
 */
@Data
public class ChatGroup {

    private List<WeiBoer> weiBoers = new ArrayList<>();

    private String gid;

    private String name;

    public ChatGroup(String gid){
        this.gid = gid;
    }
}

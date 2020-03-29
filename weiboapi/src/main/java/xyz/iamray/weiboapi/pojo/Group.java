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
public class Group {

    private List<WeiBoer> weiBoers = new ArrayList<>();

    private String gid;

    public Group(String gid){
        this.gid = gid;
    }
}

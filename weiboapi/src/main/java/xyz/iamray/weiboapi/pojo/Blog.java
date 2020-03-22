package xyz.iamray.weiboapi.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 微博实体
 */
@Data
public class Blog {

    private String reason;

    private String mid;

    private List<String> imagePaths;

    private Date sendTime;

    private int forwardNum;

    private int commentNum;

    private int praiseNum;

    private boolean isforward;//是否转发

    private String omid;//原微博mid

    private String ouid;//原微博博主uid

    private String ocontent;//原内容

    private Date osendTime;//原微博发送时间

    private String oweiboerNickName;//原博主昵称

    private String oweiboerUrl;//原博主主页URL

    private List<Comment> comments;//评论

}

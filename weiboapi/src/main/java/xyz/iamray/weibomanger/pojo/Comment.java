package xyz.iamray.weibomanger.pojo;

import lombok.Data;

/**
 * @author winray
 * @since v1.0.1
 */
@Data
public class Comment {

    private String id;

    private String content;

    private WeiBoer weiBoer;

    private int praiseNum;

    private String moreSmallComment;
}

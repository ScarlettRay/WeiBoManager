package xyz.iamray.weibomanger.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
@Data
public class Comment {

    private String id;

    private String mid;

    private String text;

    private WeiBoer weiBoer;

    private int praiseNum;

    private String moreSmallComment;

    private String imageUrl;
}

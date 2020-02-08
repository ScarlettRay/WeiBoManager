package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Comment;
import xyz.iamray.weibomanger.pojo.WeiBo;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 根据微博爬取热门评论
 */
public class GetHotCommentAPI implements API<List<Comment>, WeiBo> {

    @Override
    public R<List<Comment>> exe(WeiBo param) {
        return null;
    }
}

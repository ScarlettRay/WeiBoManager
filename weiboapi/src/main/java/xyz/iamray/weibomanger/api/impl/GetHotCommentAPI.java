package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Comment;
import xyz.iamray.weibomanger.pojo.Blog;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 根据微博爬取热门评论
 */
public class GetHotCommentAPI implements API<List<Comment>, Blog> {

    @Override
    public APINumber getNumber() {
        return APINumber.GETHOTCOMMENTAPI;
    }

    @Override
    public R<List<Comment>> exe(Blog param, Context context) {
        return null;
    }
}

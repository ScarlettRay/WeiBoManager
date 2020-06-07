package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.Comment;
import xyz.iamray.weiboapi.spider.action.DeliverCommentAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public class DeliverCommentAPI extends AbstractPostAPI<Blog,Blog> {

    public static final DeliverCommentAPI INSTANCE = new DeliverCommentAPI();

    @Override
    protected String getUrl(Blog param,Context context) {
        return AutoWeiBoSpiderConstant.DELIVER_COMMENT_URL + System.currentTimeMillis();
    }

    @Override
    protected Map<String, String> getPostBody(Blog blog,Context context) {
        List<Comment> commentList = blog.getComments();
        Comment comment = commentList.get(0);
        return PostBodyBuildUtil.buildCommentParam(blog.getMid(),comment.getText());
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return new DeliverCommentAction();
    }

    @Override
    protected Map<String, String> getRequestHeader() {
        Map<String,String> header = new HashMap(Constant.COMMON_HEADER);
        header.put("Referer","https://s.weibo.com/weibo");
        header.put("X-Requested-With","XMLHttpRequest");
        header.put("Content-Type","application/x-www-form-urlencoded");
        return header;
    }

    @Override
    public String getNumber() {
        return "DeliverCommentAPI";
    }
}

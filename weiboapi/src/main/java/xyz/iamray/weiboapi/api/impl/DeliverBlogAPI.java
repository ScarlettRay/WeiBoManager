package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.DeliverBlogAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 发微博api
 */
public class DeliverBlogAPI extends AbstractPostAPI<Blog,Blog> {

    public final static DeliverBlogAPI INSTANCE = new DeliverBlogAPI();

    @Override
    public String getNumber() {
        return APINumber.DELIVERBLOGAPI;
    }

    @Override
    protected String getUrl(Blog param, Context context) {
        return AutoWeiBoSpiderConstant.SEND_BLOG_URL+System.currentTimeMillis();
    }

    @Override
    protected Map<String, String> getPostBody(Blog blog, Context context) {
        return PostBodyBuildUtil.buildSendBlogParam(blog.getReason(),blog.getImagePaths());
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return DeliverBlogAction.INSTANCE;
    }

}

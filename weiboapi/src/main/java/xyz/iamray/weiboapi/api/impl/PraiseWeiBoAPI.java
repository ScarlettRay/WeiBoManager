package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.core.HttpConstant;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.PraiseWeiBoAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 微博点赞API
 */
public class PraiseWeiBoAPI extends AbstractPostAPI<Blog,Blog> {

    public final static PraiseWeiBoAPI INSTANCE = new PraiseWeiBoAPI();

    @Override
    public String getNumber() {
        return "PraiseWeiBoAPI";
    }

    @Override
    protected String getUrl(Blog param, Context context) {
        return AutoWeiBoSpiderConstant.PRAISE_BLOG_URL + System.currentTimeMillis();
    }

    @Override
    protected Map<String, String> getPostBody(Blog blog,Context context) {
        return PostBodyBuildUtil.buildPraiseWeiBoParam(blog.getMid());
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return new PraiseWeiBoAction();
    }

    @Override
    protected Map<String, String> getRequestHeader() {
        Map<String,String> header = new HashMap<>(Constant.COMMON_HEADER);
        header.put("Content-Type","application/x-www-form-urlencoded");
        header.put("X-Requested-With","XMLHttpRequest");
        header.put(HttpConstant.Header.REFERER,"https://s.weibo.com/weibo");
        return header;
    }
}

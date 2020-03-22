package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.DeliverBlogAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 发微博api
 */
public class DeliverBlogAPI implements API<Blog,Blog> {

    @Override
    public String getNumber() {
        return APINumber.DELIVERBLOGAPI;
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        String url = AutoWeiBoSpiderConstant.SEND_BLOG_URL+System.currentTimeMillis();
        Map<String,String> postBody = PostBodyBuildUtil.buildSendBlogParam(blog.getReason(),blog.getImagePaths());
        Result<Blog> result = PostSpider.make().defaultThreadPool()
                //.setProperty("blog",blog)
                .setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url,postBody, DeliverBlogAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(result.getObj());
    }
}

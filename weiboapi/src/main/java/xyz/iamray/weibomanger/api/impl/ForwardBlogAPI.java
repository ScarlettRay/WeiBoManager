package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.common.constant.Constant;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.spider.action.ForwardBlogAction;
import xyz.iamray.weibomanger.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 转发微博的api
 */
public class ForwardBlogAPI implements API<Blog,Blog> {

    @Override
    public String getNumber() {
        return APINumber.FORWARDBLOGAPI.name();
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        String url = AutoWeiBoSpiderConstant.Forward_WeiBo_URL.replace("{}",context.getUid())+System.currentTimeMillis();
        Map<String,String> postBody = PostBodyBuildUtil.buildForwardParam(blog.getReason(),blog.getMid());
        Result<Blog> re = PostSpider.make().defaultThreadPool()
                .setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url, postBody, ForwardBlogAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(blog);
    }
}

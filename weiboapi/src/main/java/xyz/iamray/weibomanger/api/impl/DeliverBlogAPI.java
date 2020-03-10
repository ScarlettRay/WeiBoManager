package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.constant.Constant;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.spider.action.DeliverBlogAction;
import xyz.iamray.weibomanger.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 发微博api
 */
public class DeliverBlogAPI implements API<Blog,Blog> {

    @Override
    public String getNumber() {
        return APINumber.DELIVERBLOGAPI.name();
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        String url = AutoWeiBoSpiderConstant.SEND_BLOG_URL+System.currentTimeMillis();
        Map<String,String> postBody = PostBodyBuildUtil.buildSendBlogParam(blog.getReason(),blog.getImagePaths());
        Result<Blog> result = PostSpider.make().setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url,postBody, DeliverBlogAction.INSTANCE)
                .start();
        return R.ok(result.getObj());
    }
}

package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.WeiBoer;

/**
 * @author winray
 * @since v1.0.1
 */
public class CustomDemoAPI implements API<Blog, WeiBoer> {
    @Override
    public String getNumber() {
        return "CustomApi";
    }

    @Override
    public R<Blog> exe(WeiBoer weiBoer, Context context) {
        Blog blog = new Blog();
        blog.setReason("转发微博：" + weiBoer.getUid());
        blog.setMid("4480206255699492");
        return R.ok(blog);
    }


}

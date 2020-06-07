package xyz.iamray.weiboapi.api.impl.convertapi;

import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.WeiBoer;

/**
 * @author winray
 * @since v1.0.1
 */
public class CustomDemoAPI implements API<WeiBoer, Blog> {
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

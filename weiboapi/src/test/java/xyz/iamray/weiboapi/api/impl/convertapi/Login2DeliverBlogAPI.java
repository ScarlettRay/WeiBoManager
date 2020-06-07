package xyz.iamray.weiboapi.api.impl.convertapi;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.Date;

/**
 * @author winray
 * @since v1.0.1
 */
@Slf4j
public class Login2DeliverBlogAPI implements API<WeiBoer, Blog> {
    @Override
    public String getNumber() {
        return "Login2DeliverBlogAPI";
    }

    @Override
    public R<Blog> exe(WeiBoer param, Context context) {
        log.info(param.toString());
        Blog blog = new Blog();
        blog.setReason("测试发微博API:" + new Date());
        return R.ok(blog);
    }
}

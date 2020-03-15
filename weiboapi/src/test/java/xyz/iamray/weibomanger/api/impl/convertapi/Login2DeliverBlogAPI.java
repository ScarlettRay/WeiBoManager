package xyz.iamray.weibomanger.api.impl.convertapi;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.WeiBoer;

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

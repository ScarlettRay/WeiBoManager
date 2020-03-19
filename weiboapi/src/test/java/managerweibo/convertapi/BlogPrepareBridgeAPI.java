package managerweibo.convertapi;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.utils.WeiBoUtil;

/**
 * @author Ray
 * @create 2020-03-19 16:02:21
 * <p>
 */
public class BlogPrepareBridgeAPI implements API<Blog,Blog> {
    @Override
    public String getNumber() {
        return "BlogPrepareBridgeAPI";
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        blog.setMid(context.getProperty("forward_mid",String.class));
        if(!blog.getComments().isEmpty()){
            blog.setReason(WeiBoUtil.trimHtml(blog.getComments().get(0).getText()));
        }
        return R.ok(blog);
    }
}

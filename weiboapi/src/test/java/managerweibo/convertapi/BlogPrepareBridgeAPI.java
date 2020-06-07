package managerweibo.convertapi;

import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.utils.TextTrimer;

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
            blog.setReason(TextTrimer.trimHtml(blog.getComments().get(0).getText()));
        }
        return R.ok(blog);
    }
}

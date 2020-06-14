package xyz.iamray.flow.impl.ffp;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetOmidsBridgeAPI implements ApiBridge<Blog,String> {

    public static final GetOmidsBridgeAPI INSTANCE = new GetOmidsBridgeAPI();

    @Override
    public String getNumber() {
        return "GetOmidsBridgeAPI";
    }

    @Override
    public R<String> exe(Blog blog, Context context) {
        return R.ok(blog.getOmid());
    }
}

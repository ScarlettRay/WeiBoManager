package xyz.iamray.flow.impl.praisecomment;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.filter.Filter;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class BlogFilterBridgeAPI implements ApiBridge<List<Blog>,List<Blog>> {

    public static final BlogFilterBridgeAPI INSTANCE = new BlogFilterBridgeAPI();

    public static final String BLOG_FILTER = "BlogFilterBridgeAPI-Blog-Filter";

    @Override
    public String getNumber() {
        return "BlogFilterBridgeAPI";
    }

    @Override
    public R<List<Blog>> exe(List<Blog> blogs, Context context) {
        Filter<Blog> filter = (Filter<Blog>)context.getProperty(BLOG_FILTER, Filter.class);
        List<Blog> blogList = new ArrayList<>();
        if(WeiBoUtil.isNotNull(blogs)){
            for (Blog blog : blogs) {
                if(filter.contains(blog)){
                    blogList.add(blog);
                }
            }
        }
        return R.ok(blogList);
    }

}

package xyz.iamray.weiboapi.api.filter;

import xyz.iamray.weiboapi.pojo.Blog;

/**
 * @author winray
 * @since v1.0.1
 * 只选择是转发的微博
 */
public class SelectForwardBlogFilter extends AbstractFilter<Blog> {

    public static final SelectForwardBlogFilter INSTANCE = new SelectForwardBlogFilter();

    @Override
    public boolean contains(Blog blog) {
        return blog.isIsforward();
    }

    @Override
    public String getNumber() {
        return "SelectForwardBlogFilter";
    }
}

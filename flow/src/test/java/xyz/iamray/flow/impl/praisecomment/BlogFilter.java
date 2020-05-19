package xyz.iamray.flow.impl.praisecomment;

import xyz.iamray.flow.filter.AbstractFilter;
import xyz.iamray.flow.filter.Filter;
import xyz.iamray.weiboapi.pojo.Blog;

/**
 * @author winray
 * @since v1.0.1
 */
public class BlogFilter extends AbstractFilter<Blog> {

    @Override
    public boolean contains(Blog blog) {
        return true;
    }

    @Override
    public String getNumber() {
        return "BlogFilter";
    }
}

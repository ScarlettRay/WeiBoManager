package xyz.iamray.flow.impl.ffp;

import xyz.iamray.weiboapi.api.filter.AbstractFilter;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.Comment;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 判断这条微博是否为抽奖微博
 */
public class PrizeBlogFilter extends AbstractFilter<Blog> {

    public static final PrizeBlogFilter INSTANCE = new PrizeBlogFilter();

    @Override
    public String getNumber() {
        return "PrizeBlogFilter";
    }

    @Override
    public boolean contains(Blog blog) {
        //分析博文中是否有抽奖详情
        if(blog.getReason() !=null && blog.getReason().contains("抽奖详情")){
            return true;
        }
        //分析评论第一条
        if(WeiBoUtil.isNotNull(blog.getComments())){
            List<Comment> commentList = blog.getComments();
            Comment comment = commentList.get(0);
            if(comment.getText().contains("抽奖详情")){
                return true;
            }
        }
        return false;
    }
}

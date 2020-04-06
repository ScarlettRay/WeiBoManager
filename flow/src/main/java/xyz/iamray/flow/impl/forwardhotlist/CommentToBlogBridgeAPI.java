package xyz.iamray.flow.impl.forwardhotlist;

import xyz.iamray.weiboapi.api.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.Comment;
import xyz.iamray.weiboapi.utils.TextTrimer;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

/**
 * @author winray
 * @since v1.0.1
 */
public class CommentToBlogBridgeAPI implements ApiBridge<Blog, Blog> {

    public static final CommentToBlogBridgeAPI INSTANCE = new CommentToBlogBridgeAPI();

    @Override
    public String getNumber() {
        return "CommentToBlogBridgeAPI";
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        Blog newBlog = new Blog();
        newBlog.setReason("[doge]");
        if(!WeiBoUtil.isNull(blog.getComments())){
            for (Comment comment : blog.getComments()) {
                if(!blog.getWeiBoer().getUid().equals(comment.getWeiBoer().getUid())){
                    newBlog.setReason(TextTrimer.trimHtml(comment.getText()));
                    break;
                }
            }
        }
        newBlog.setMid(blog.getMid());

        return R.ok(newBlog);
    }
}

package xyz.iamray.flow.impl.praisecomment;

import org.apache.commons.lang.math.RandomUtils;
import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class AddCommentToBlogBridgeAPI implements ApiBridge<Blog, Blog> {

    public static final AddCommentToBlogBridgeAPI INSTANCE = new AddCommentToBlogBridgeAPI();

    public static final String COMMENT_LIST = "AddCommentToBlogBridgeAPI-CommentList";

    @Override
    public String getNumber() {
        return "AddCommentToBlogBridgeAPI";
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        List<String> tmp = null;
        if(context.getProperty(COMMENT_LIST, List.class) == null){
            tmp = Constant.COMMENT_LIST;
        }else{
            tmp = context.getProperty(COMMENT_LIST, List.class);
        }
        Comment comment = new Comment();
        comment.setText(tmp.get(RandomUtils.nextInt(tmp.size()-1)));
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        blog.setComments(comments);
        return R.ok(blog);
    }
}

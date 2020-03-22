package xyz.iamray.weiboapi.api.impl.mobal;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.Comment;
import xyz.iamray.weiboapi.spider.action.mobal.GetMobalHotCommentAction;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 根据微博爬取热门评论
 */
public class GetMobalHotCommentAPI implements API<Blog,Blog> {

    @Override
    public String getNumber() {
        return APINumber.GETMOBALHOTCOMMENTAPI;
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        String url = AutoWeiBoSpiderConstant.WEIBO_MOBAL_HOT_COMMENT_URL.replace("{mid}",blog.getMid());
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Comment>> result = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(url, GetMobalHotCommentAction.INSTANCE).start();
        blog.setComments(result.getObj());
        return R.ok(blog);
    }
}

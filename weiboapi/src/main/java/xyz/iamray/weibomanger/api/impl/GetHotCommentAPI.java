package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.Comment;
import xyz.iamray.weibomanger.spider.action.GetHotCommentAction;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 根据微博爬取热门评论
 */
public class GetHotCommentAPI implements API<List<Comment>, Blog> {

    @Override
    public APINumber getNumber() {
        return APINumber.GETHOTCOMMENTAPI;
    }

    @Override
    public R<List<Comment>> exe(Blog blog, Context context) {
        String url = AutoWeiBoSpiderConstant.WEIBO_HOT_COMMENT_URL.replace("{mid}",blog.getMid()) + System.currentTimeMillis();
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Comment>> result = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(url, GetHotCommentAction.INSTANCE).start();

        return R.ok(result.getObj());
    }
}

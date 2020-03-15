package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.spider.action.GetMobalHotCommentAction;

/**
 * @author winray
 * @since v1.0.1
 * 根据微博爬取热门评论
 */
public class GetMobalHotCommentAPI implements API<Blog,Blog> {

    @Override
    public String getNumber() {
        return APINumber.GETMOBALHOTCOMMENTAPI.name();
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        String url = AutoWeiBoSpiderConstant.WEIBO_MOBAL_HOT_COMMENT_URL.replace("{mid}",blog.getMid());
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<Blog> result = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(url, GetMobalHotCommentAction.INSTANCE).start();

        return R.ok(result.getObj());
    }
}

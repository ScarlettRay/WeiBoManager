package xyz.iamray.weibomanger.api.impl.mobal;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.Comment;
import xyz.iamray.weibomanger.spider.action.GetMobalHotCommentAction;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 16:30:46
 * <p>
 */
public class GetMobalWeiBoByUrlAPI implements API<String,List<Blog>> {
    @Override
    public String getNumber() {
        return APINumber.GETMOBALWEIBOBYURLAPI.name();
    }

    @Override
    public R<List<Blog>> exe(String url, Context context) {
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Blog>> result = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(url, GetMobalHotCommentAction.INSTANCE).start();

        return R.ok(result.getObj());
    }
}

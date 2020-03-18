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
import xyz.iamray.weibomanger.spider.action.CrawlWeiBoAction;
import xyz.iamray.weibomanger.spider.action.CrawlWeiBoByUrlAction;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-18 15:17:40
 * <p>
 */
public class CrawlWeiBoByUrlAPI implements API<String, List<Blog>> {
    @Override
    public String getNumber() {
        return APINumber.CRAWLWEIBOBYURLAPI.name();
    }

    @Override
    public R<List<Blog>> exe(String url, Context context) {
        String hotUrl = "https://s.weibo.com" + url;
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Blog>> re = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .addCookie("SUB","1",".weibo.com","/")
                .setStarterConfiger(hotUrl, CrawlWeiBoByUrlAction.INSTANCE).start();
        return R.ok(re.getObj());
    }
}

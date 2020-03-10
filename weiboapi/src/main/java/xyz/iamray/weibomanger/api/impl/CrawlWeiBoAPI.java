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
import xyz.iamray.weibomanger.pojo.WeiBoer;
import xyz.iamray.weibomanger.spider.action.CrawlWeiBoAction;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class CrawlWeiBoAPI implements API<WeiBoer,List<Blog>> {

    @Override
    public String getNumber() {
        return APINumber.CRAWLWEIBOAPI.name();
    }

    @Override
    public R<List<Blog>> exe(WeiBoer weiBoer, Context context) {
        String url = AutoWeiBoSpiderConstant.WEIBO_HOME_URL
                .replace("{uid}",weiBoer.getUid())
                .replace("{page}",context.getProperty("crawl_page",String.class));
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Blog>> re = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .addCookie("SUB","1",".weibo.com","/")
                .setStarterConfiger(url, CrawlWeiBoAction.INSTANCE).start();

        return R.ok(re.getObj());
    }
}

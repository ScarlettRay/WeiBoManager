package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.spider.action.CrawlWeiBoAction;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class CrawlWeiBoAPI implements API<WeiBoer,List<Blog>> {

    public final static CrawlWeiBoAPI INSTANCE = new CrawlWeiBoAPI();

    public final static String CRAWL_PAGE = "CrawlWeiBoAPI-CrawlPage";

    @Override
    public String getNumber() {
        return APINumber.CRAWLWEIBOAPI;
    }

    @Override
    public R<List<Blog>> exe(WeiBoer weiBoer, Context context) {
        String url = AutoWeiBoSpiderConstant.WEIBO_HOME_URL
                .replace("{uid}",weiBoer.getUid())
                .replace("{page}",context.getProperty(CRAWL_PAGE,String.class));
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Blog>> re = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .addCookie("SUB","1",".weibo.com","/")
                .setStarterConfiger(url, CrawlWeiBoAction.INSTANCE).start();

        return R.ok(re.getObj());
    }
}

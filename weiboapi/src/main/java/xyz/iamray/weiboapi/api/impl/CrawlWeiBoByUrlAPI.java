package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.CrawlWeiBoByUrlAction;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-18 15:17:40
 * <p>
 */
public class CrawlWeiBoByUrlAPI implements API<String, List<Blog>> {
    @Override
    public String getNumber() {
        return APINumber.CRAWLWEIBOBYURLAPI;
    }

    @Override
    public R<List<Blog>> exe(String url, Context context) {
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Blog>> re = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .addCookie("SUB","1",".weibo.com","/")
                .setStarterConfiger(url, CrawlWeiBoByUrlAction.INSTANCE).start();
        return R.ok(re.getObj());
    }
}

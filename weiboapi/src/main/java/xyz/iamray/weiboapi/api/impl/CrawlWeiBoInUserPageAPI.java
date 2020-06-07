package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.spider.action.CrawlWeiBoInUserPageAction;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class CrawlWeiBoInUserPageAPI implements API<WeiBoer, List<Blog>> {

    public final static CrawlWeiBoInUserPageAPI INSTANCE = new CrawlWeiBoInUserPageAPI();

    @Override
    public String getNumber() {
        return "CrawlWeiBoInUserPageAPI";
    }

    @Override
    public R<List<Blog>> exe(WeiBoer weiBoer, Context context) {
        String url = AutoWeiBoSpiderConstant.USER_HOME_WEIBO_URL
                .replace("{uid}",weiBoer.getUid())
                .replace("{page}","1");
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Blog>> re = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .addCookie("SUB","1",".weibo.com","/")
                .setStarterConfiger(url, CrawlWeiBoInUserPageAction.INSTANCE).start();
        return WeiBoUtil.dealResult(re);
    }
}

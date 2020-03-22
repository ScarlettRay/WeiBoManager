package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONObject;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.PrivateLetter;

/**
 * @author winray
 * @since v1.0.1
 * {@link xyz.iamray.weiboapi.api.impl.SendPrivateLetterAPI}
 */
public class SendPrivateLetterAction extends AbstractJsonObjectCrawlerAction<PrivateLetter> {

    public static final SendPrivateLetterAction INSTANCE = new SendPrivateLetterAction();

    @Override
    public PrivateLetter crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        return null;
    }
}

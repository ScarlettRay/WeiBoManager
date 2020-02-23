package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.GroupMessage;

/**
 * @author winray
 * @since v1.0.1
 * {@link xyz.iamray.weibomanger.api.impl.SendPrivateLetterAPI}
 */
@Slf4j
public class SendGroupMessageAction extends AbstractJsonObjectCrawlerAction<GroupMessage> {

    public static final SendGroupMessageAction INSTANCE = new SendGroupMessageAction();

    @Override
    public GroupMessage crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        log.info("SendGroupMessageActionv:" + jsonObject);
        return null;
    }
}

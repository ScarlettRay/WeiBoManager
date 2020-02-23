package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.WeiBoer;

/**
 * @author winray
 * @since v1.0.1
 */
@Slf4j
public class FollowWeiBoerAction extends AbstractJsonObjectCrawlerAction<WeiBoer> {

    public static final FollowWeiBoerAction INSTANCE = new FollowWeiBoerAction();

    @Override
    public WeiBoer crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        log.info("FollowWeiBoerAction:" + jsonObject);
        return null;
    }
}

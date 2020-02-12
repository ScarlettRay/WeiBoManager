package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.FollowingGroup;

/**
 * @author winray
 * @since v1.0.1
 * {@link xyz.iamray.weibomanger.api.impl.CreateNewFollowingGroupAPI}
 */
@Slf4j
public class CreateNewFollowingGroupAction extends AbstractJsonObjectCrawlerAction<FollowingGroup> {

    public static final CreateNewFollowingGroupAction INSTANCE = new CreateNewFollowingGroupAction();

    @Override
    public FollowingGroup crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        log.info(jsonObject.toJSONString());
        return null;
    }
}

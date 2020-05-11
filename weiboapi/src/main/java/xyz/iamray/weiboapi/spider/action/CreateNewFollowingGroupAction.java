package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.constant.TextConstant;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.FollowingGroup;

/**
 * @author winray
 * @since v1.0.1
 * {@link xyz.iamray.weiboapi.api.impl.CreateNewFollowingGroupAPI}
 */
@Slf4j
public class CreateNewFollowingGroupAction extends AbstractJsonObjectCrawlerAction<FollowingGroup> {

    public static final CreateNewFollowingGroupAction getInstance(){
        return new CreateNewFollowingGroupAction();
    }

    @Override
    public FollowingGroup crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        log.info(jsonObject.toJSONString());
        if(jsonObject.getInteger("code") == 100000){
            FollowingGroup followingGroup = this.getAttribute(TextConstant.API_INPUT,FollowingGroup.class);
            return followingGroup;
        }
        throw new WbException(jsonObject.toJSONString());
    }
}

package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.WeiBoer;

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
        WeiBoer weiBoer = getAttribute("weiboer",WeiBoer.class);
        if(jsonObject.getInteger("code") == 100000){
            JSONObject relation = (JSONObject) JSONPath.eval(jsonObject,"$.data.relation");
            if(relation.getInteger("following") == 1){
                weiBoer.setFollowing(true);
            }
            if(relation.getInteger("follow_me") == 1){
                weiBoer.setFollowMe(true);
            }
            weiBoer.setNickName((String)JSONPath.eval(jsonObject,"$.data.fnick"));
        }else{
            log.error("FollowWeiBoerAction:",jsonObject,weiBoer);
            throw new WbException(jsonObject.toJSONString());
        }
        return weiBoer;
    }
}

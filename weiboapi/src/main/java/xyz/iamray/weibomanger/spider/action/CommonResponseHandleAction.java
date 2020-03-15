package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONObject;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.common.exception.WbException;

/**
 * @author winray
 * @since v1.0.1
 * 处理返回结果的统一的动作
 */
public class CommonResponseHandleAction extends AbstractJsonObjectCrawlerAction<String> {

    public static final CommonResponseHandleAction INSTANCE = new CommonResponseHandleAction();

    @Override
    public String crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        if(jsonObject.getInteger("code") == 100000){
            return jsonObject.getString("msg");
        }
        throw new WbException(jsonObject.toJSONString());
    }
}

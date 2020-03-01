package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONObject;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.Comment;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetHotCommentAction extends AbstractJsonObjectCrawlerAction<List<Comment>> {

    public static GetHotCommentAction INSTANCE = new GetHotCommentAction();

    @Override
    public List<Comment> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        if(jsonObject.getInteger("code") == 100000){
            String html = jsonObject.getJSONObject("data").getJSONObject("html");
        }
        jsonObject.get("");
    }
}

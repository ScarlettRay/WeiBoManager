package xyz.iamray.weiboapi.spider.action.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 14:48:53
 * <p>{@link xyz.iamray.weiboapi.api.impl.mobile.CrawlMobalHotListAPI}
 */
public class CrawlMobileHotListAction extends AbstractJsonObjectCrawlerAction<List<String>> {

    public static final CrawlMobileHotListAction INSTANCE = new CrawlMobileHotListAction();

    @Override
    public List<String> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        List<String> urls = new ArrayList<>();
        if(jsonObject.getInteger("ok") == 1){
            JSONArray cards = (JSONArray) JSONPath.eval(jsonObject,"$.data.cards.card_group");
            for (Object o : cards) {
                JSONObject card = (JSONObject)o;
                urls.add(card.getString("scheme"));
            }
        }

        return urls;
    }
}

package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONObject;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.Blog;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 16:35:06
 * <p>
 */
public class GetMobalWeiBoByUrlAction extends AbstractJsonObjectCrawlerAction<List<Blog>> {

    @Override
    public List<Blog> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        return null;
    }

}

package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.Blog;

/**
 * @author winray
 * @since v1.0.1
 * {@link xyz.iamray.weibomanger.api.impl.DeliverBlogAPI}
 */
@Slf4j
public class DeliverBlogAction extends AbstractJsonObjectCrawlerAction<Blog> {

    public static final DeliverBlogAction INSTANCE = new DeliverBlogAction();

    @Override
    public Blog crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        log.info("DeliverBlogAction:" + jsonObject);
        return null;
    }
}

package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.Blog;

/**
 * @author winray
 * @since v1.0.1
 *转发微博后的结果处理动作
 */
@Slf4j
public class ForwardBlogAction extends AbstractJsonObjectCrawlerAction<Blog> {

    public static ForwardBlogAction INSTANCE = new ForwardBlogAction();

    @Override
    public Blog crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        log.info("返回的数据："+jsonObject.toJSONString());
        return new Blog();
    }
}

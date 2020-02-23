package xyz.iamray.weibomanger.spider.action;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractStringCrawlerAction;
import xyz.iamray.repo.CrawlMes;

/**
 * @author winray
 * @since v1.0.1
 */
@Slf4j
public class UploadImageAction extends AbstractStringCrawlerAction<String> {

    public static final UploadImageAction INSTANCE = new UploadImageAction();

    @Override
    public String crawl(String s, CrawlMes crawlMes) {
        log.info("UploadImageAction 的 header:" + crawlMes.getLastHeaderValue("Location"));
        log.info("UploadImageAction:" + s);
        return crawlMes.getLastHeaderValue("Location");
    }
}

package xyz.iamray.weiboapi.spider.action.mobile;

import xyz.iamray.action.impl.AbstractStringCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.WeiBoer;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetUidByNickNameAction extends AbstractStringCrawlerAction<WeiBoer> {

    public static final GetUidByNickNameAction INSTANCE = new GetUidByNickNameAction();

    @Override
    public WeiBoer crawl(String string, CrawlMes crawlMes) {
        String location = crawlMes.getLastHeaderValue("Location");
        String uid = location.substring(3);
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid(uid);
        return weiBoer;
    }

}

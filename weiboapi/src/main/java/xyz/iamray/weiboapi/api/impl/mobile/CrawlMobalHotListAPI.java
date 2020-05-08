package xyz.iamray.weiboapi.api.impl.mobile;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.impl.AbstractGetAPI;
import xyz.iamray.weiboapi.spider.action.mobile.CrawlMobileHotListAction;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 14:02:18
 * <p>
 *     I:输入热榜主页链接
 *     O:输出热榜榜单分录链接
 */
public class CrawlMobalHotListAPI extends AbstractGetAPI<String, List<String>> {

    public final static CrawlMobalHotListAPI INSTANCE = new CrawlMobalHotListAPI();

    @Override
    public String getNumber() {
        return "CRAWLMOBALHOTLISTAPI";
    }

    @Override
    protected String getUrl(String url, Context context) {
        return url;
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return CrawlMobileHotListAction.INSTANCE;
    }

}

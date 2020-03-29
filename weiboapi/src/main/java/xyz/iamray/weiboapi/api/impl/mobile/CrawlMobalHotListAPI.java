package xyz.iamray.weiboapi.api.impl.mobile;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.spider.action.mobile.CrawlMobileHotListAction;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 14:02:18
 * <p>
 *     I:输入热榜主页链接
 *     O:输出热榜榜单分录链接
 */
public class CrawlMobalHotListAPI implements API<String, List<String>> {

    public final static CrawlMobalHotListAPI INSTANCE = new CrawlMobalHotListAPI();

    @Override
    public String getNumber() {
        return "CRAWLMOBALHOTLISTAPI";
    }

    @Override
    public R<List<String>> exe(String url, Context context) {
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<String>> result = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(url, CrawlMobileHotListAction.INSTANCE)
                .start();
        return R.ok(result.getObj());
    }
}

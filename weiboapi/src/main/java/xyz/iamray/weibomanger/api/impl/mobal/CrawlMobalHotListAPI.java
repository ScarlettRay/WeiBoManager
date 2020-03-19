package xyz.iamray.weibomanger.api.impl.mobal;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.spider.action.CrawlMobalHotListAction;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 14:02:18
 * <p>
 *     I:输入热榜主页链接
 *     O:输出热榜榜单分录链接
 */
public class CrawlMobalHotListAPI implements API<String, List<String>> {
    @Override
    public String getNumber() {
        return APINumber.CRAWLMOBALHOTLISTAPI.name();
    }

    @Override
    public R<List<String>> exe(String url, Context context) {
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<String>> reuslt = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(url,CrawlMobalHotListAction.INSTANCE)
                .start();
        return R.ok(reuslt.getObj());
    }
}

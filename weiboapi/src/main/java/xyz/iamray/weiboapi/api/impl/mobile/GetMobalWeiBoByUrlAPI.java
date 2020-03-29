package xyz.iamray.weiboapi.api.impl.mobile;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.mobile.GetMobileWeiBoByUrlAction;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 16:30:46
 * <p>
 */
public class GetMobalWeiBoByUrlAPI implements API<String,List<Blog>> {

    public final static GetMobalWeiBoByUrlAPI INSTANCE = new GetMobalWeiBoByUrlAPI();

    @Override
    public String getNumber() {
        return APINumber.GETMOBALWEIBOBYURLAPI;
    }

    @Override
    public R<List<Blog>> exe(String url, Context context) {
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Blog>> result = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(url, GetMobileWeiBoByUrlAction.INSTANCE).start();

        return R.ok(result.getObj());
    }
}

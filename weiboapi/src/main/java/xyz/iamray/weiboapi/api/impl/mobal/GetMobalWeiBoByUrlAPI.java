package xyz.iamray.weiboapi.api.impl.mobal;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.mobal.GetMobalWeiBoByUrlAction;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 16:30:46
 * <p>
 */
public class GetMobalWeiBoByUrlAPI implements API<List<String>,List<Blog>> {
    @Override
    public String getNumber() {
        return APINumber.GETMOBALWEIBOBYURLAPI;
    }

    @Override
    public R<List<Blog>> exe(List<String> urls, Context context) {
        if(WeiBoUtil.isNull(urls))return R.no();
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<List<Blog>> result = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(urls.toArray(new String[0]), GetMobalWeiBoByUrlAction.INSTANCE).start();

        return R.ok(result.getObj());
    }
}

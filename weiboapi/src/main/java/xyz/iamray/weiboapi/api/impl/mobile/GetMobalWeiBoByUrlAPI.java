package xyz.iamray.weiboapi.api.impl.mobile;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.impl.AbstractGetAPI;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.mobile.GetMobileWeiBoByUrlAction;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 16:30:46
 * <p>
 */
public class GetMobalWeiBoByUrlAPI extends AbstractGetAPI<String,List<Blog>> {

    public final static GetMobalWeiBoByUrlAPI INSTANCE = new GetMobalWeiBoByUrlAPI();

    @Override
    public String getNumber() {
        return APINumber.GETMOBALWEIBOBYURLAPI;
    }

    @Override
    protected String getUrl(String url, Context context) {
        return url;
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return GetMobileWeiBoByUrlAction.INSTANCE;
    }

}

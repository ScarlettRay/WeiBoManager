package xyz.iamray.weiboapi.api.impl.mobile;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.AbstractGetAPI;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.GetMobalWeiBoDetailByMidAction;

/**
 * @author winray
 * @since v1.0.1
 * 根据mid获取微博详情
 */
public class GetMobalWeiBoDetailByMidAPI extends AbstractGetAPI<String,Blog> {

    public static final GetMobalWeiBoDetailByMidAPI INSTANCE = new GetMobalWeiBoDetailByMidAPI();

    @Override
    public String getNumber() {
        return "GetMobalWeiBoDetailByMidAPI";
    }


    @Override
    protected String getUrl(String mid, Context context) {
        return AutoWeiBoSpiderConstant.MOBAL_WEIBO_DETAIL_URL + mid;
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return GetMobalWeiBoDetailByMidAction.INSTANCE;
    }
}

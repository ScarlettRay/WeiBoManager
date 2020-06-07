package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.spider.action.FollowWeiBoerAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 关注微博api,
 * 此接口必须有自定义的线程池
 */
public class FollowWeiboerAPI extends AbstractPostAPI<WeiBoer, WeiBoer> {

    public final static FollowWeiboerAPI INSTANCE = new FollowWeiboerAPI();

    @Override
    public String getNumber() {
        return APINumber.FOLLOWWEIBOERAPI;
    }

    @Override
    protected String getUrl(WeiBoer param, Context context) {
        return AutoWeiBoSpiderConstant.Followed_URL+System.currentTimeMillis();
    }

    @Override
    protected Map<String, String> getPostBody(WeiBoer weiBoer, Context context) {
        return PostBodyBuildUtil.buildFollowedParam(weiBoer.getUid());
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return FollowWeiBoerAction.INSTANCE;
    }

}

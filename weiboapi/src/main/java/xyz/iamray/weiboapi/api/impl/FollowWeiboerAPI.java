package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
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
public class FollowWeiboerAPI implements API<WeiBoer, WeiBoer> {

    public final static FollowWeiboerAPI INSTANCE = new FollowWeiboerAPI();

    @Override
    public String getNumber() {
        return APINumber.FOLLOWWEIBOERAPI;
    }

    @Override
    public R<WeiBoer> exe(WeiBoer weiBoer, Context context) {
        String url = AutoWeiBoSpiderConstant.Followed_URL+System.currentTimeMillis();
        Map<String,String> postBody = PostBodyBuildUtil.buildFollowedParam(weiBoer.getUid());
        PostSpider spider = PostSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<WeiBoer> re = spider.setRequestHeader(Constant.COMMON_HEADER)
                .setProperty("weiboer",weiBoer)
                .setStarterConfiger(url,postBody, FollowWeiBoerAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(re.getObj());
    }
}

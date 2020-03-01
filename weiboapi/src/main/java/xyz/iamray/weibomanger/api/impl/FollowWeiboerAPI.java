package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.constant.Constant;
import xyz.iamray.weibomanger.pojo.WeiBoer;
import xyz.iamray.weibomanger.spider.action.FollowWeiBoerAction;
import xyz.iamray.weibomanger.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 关注微博api,
 * 此接口必须有自定义的线程池
 */
public class FollowWeiboerAPI implements API<WeiBoer, WeiBoer> {

    @Override
    public APINumber getNumber() {
        return APINumber.FOLLOWWEIBOERAPI;
    }

    @Override
    public R<WeiBoer> exe(WeiBoer weiBoer, Context context) {
        String url = AutoWeiBoSpiderConstant.Followed_URL+System.currentTimeMillis();
        Map<String,String> postBody = PostBodyBuildUtil.buildFollowedParam(weiBoer.getUid());
        PostSpider spider = PostSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<WeiBoer> re = spider.setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url,postBody, FollowWeiBoerAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(re.getObj());
    }
}

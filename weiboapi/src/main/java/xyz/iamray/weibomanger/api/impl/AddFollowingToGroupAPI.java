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
import xyz.iamray.weibomanger.spider.action.AddFollowingToGroupAction;
import xyz.iamray.weibomanger.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 将用户加入指定分组
 */
public class AddFollowingToGroupAPI implements API<WeiBoer,WeiBoer> {

    @Override
    public String getNumber() {
        return APINumber.ADDFOLLOWINGTOGROUPAPI.name();
    }

    @Override
    public R<WeiBoer> exe(WeiBoer weiBoer, Context context) {
        Map<String,String> postBody = PostBodyBuildUtil.buildGroupAddParam(weiBoer.getGid(),weiBoer.getUid());
        Result<WeiBoer> re = PostSpider.make().defaultThreadPool().
                setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(AutoWeiBoSpiderConstant.UpdateGroup_URL+System.currentTimeMillis(),
                        postBody, AddFollowingToGroupAction.INSTANCE, context.getHttpClient())
                .start();
        return R.ok(re.getObj());
    }
}

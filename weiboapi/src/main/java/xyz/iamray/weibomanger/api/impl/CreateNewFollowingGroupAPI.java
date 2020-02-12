package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.constant.Constant;
import xyz.iamray.weibomanger.pojo.FollowingGroup;
import xyz.iamray.weibomanger.spider.action.CreateNewFollowingGroupAction;
import xyz.iamray.weibomanger.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public class CreateNewFollowingGroupAPI implements API<FollowingGroup,FollowingGroup> {

    @Override
    public R<FollowingGroup> exe(FollowingGroup group, Context context) {
        Map<String,String> postBody = PostBodyBuildUtil.buildAddGroupParam(group.getName(),group.getDescription(),group.isPublic());
        Result<FollowingGroup> re = PostSpider.make().defaultThreadPool().setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(AutoWeiBoSpiderConstant.ADD_GROUP_URL+System.currentTimeMillis()
                        ,postBody,CreateNewFollowingGroupAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(re.getObj());
    }
}

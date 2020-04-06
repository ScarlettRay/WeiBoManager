package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.FollowingGroup;
import xyz.iamray.weiboapi.spider.action.CreateNewFollowingGroupAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public class CreateNewFollowingGroupAPI implements API<FollowingGroup,FollowingGroup> {

    public final static CreateNewFollowingGroupAPI INSTANCE = new CreateNewFollowingGroupAPI();

    @Override
    public String getNumber() {
        return APINumber.CREATENEWFOLLOWINGGROUPAPI;
    }

    @Override
    public R<FollowingGroup> exe(FollowingGroup group, Context context) {
        Map<String,String> postBody = PostBodyBuildUtil.buildAddGroupParam(group.getName(),group.getDescription(),group.isPublic());
        Result<FollowingGroup> re = PostSpider.make().defaultThreadPool().setRequestHeader(Constant.COMMON_HEADER)
                .setProperty("followingGroup",group)
                .setStarterConfiger(AutoWeiBoSpiderConstant.ADD_GROUP_URL+System.currentTimeMillis(),
                        postBody,CreateNewFollowingGroupAction.getInstance(),context.getHttpClient())
                .start();

        return WeiBoUtil.dealResult(re);
    }
}

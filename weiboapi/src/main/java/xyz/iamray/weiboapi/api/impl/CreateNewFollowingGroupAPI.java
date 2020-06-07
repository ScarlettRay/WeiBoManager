package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.FollowingGroup;
import xyz.iamray.weiboapi.spider.action.CreateNewFollowingGroupAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public class CreateNewFollowingGroupAPI extends AbstractPostAPI<FollowingGroup,FollowingGroup> {

    public final static CreateNewFollowingGroupAPI INSTANCE = new CreateNewFollowingGroupAPI();

    @Override
    public String getNumber() {
        return APINumber.CREATENEWFOLLOWINGGROUPAPI;
    }

    @Override
    protected String getUrl(FollowingGroup param, Context context) {
        return AutoWeiBoSpiderConstant.ADD_GROUP_URL+System.currentTimeMillis();
    }

    @Override
    protected Map<String, String> getPostBody(FollowingGroup group,Context context) {
        return PostBodyBuildUtil.buildAddGroupParam(group.getName(),group.getDescription(),group.isPublic());
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return CreateNewFollowingGroupAction.getInstance();
    }

}

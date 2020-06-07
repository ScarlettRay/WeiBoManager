package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.spider.action.AddFollowingToGroupAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 将用户加入指定分组
 */
public class AddFollowingToGroupAPI extends AbstractPostAPI<WeiBoer,String> {

    public final static AddFollowingToGroupAPI INSTANCE = new AddFollowingToGroupAPI();

    @Override
    public String getNumber() {
        return "AddFollowingToGroupApi";
    }

    @Override
    protected String getUrl(WeiBoer param, Context context) {
        return AutoWeiBoSpiderConstant.UpdateGroup_URL+System.currentTimeMillis();
    }

    @Override
    protected Map<String, String> getPostBody(WeiBoer weiBoer,Context context) {
        return PostBodyBuildUtil.buildGroupAddParam(weiBoer.getGroup().getGroupId(),weiBoer.getUid());
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return AddFollowingToGroupAction.INSTANCE;
    }

}

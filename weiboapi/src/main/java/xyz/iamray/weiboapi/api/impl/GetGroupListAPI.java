package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.FollowingGroup;
import xyz.iamray.weiboapi.spider.action.GetGroupListAction;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 获取分组
 */
public class GetGroupListAPI implements API<Object, List<FollowingGroup>>{

    public static final GetGroupListAPI INSTANCE = new GetGroupListAPI();

    @Override
    public String getNumber() {
        return "GetGroupListAPI";
    }

    @Override
    public R<List<FollowingGroup>> exe(Object param, Context context) {
        Result<List<FollowingGroup>> result = SimpleSpider.make().setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(AutoWeiBoSpiderConstant.GET_GROUP_LIST_URL, null,GetGroupListAction.INSTANCE,context.getHttpClient())
                .start();
        return WeiBoUtil.dealResult(result);
    }
}

package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.Group;
import xyz.iamray.weiboapi.spider.action.GetWeiBoersFromGroupChatAction;

/**
 * @author winray
 * @since v1.0.1
 * 从群聊中获取最近发送消息的三个账号
 * 一般从求粉群中获取获取账号并关注，可以提高粉丝数
 */
public class GetWeiBoersFromGroupChatAPI implements API<Group, Group> {

    public final static GetWeiBoersFromGroupChatAPI INSTANCE = new GetWeiBoersFromGroupChatAPI();

    @Override
    public String getNumber() {
        return APINumber.GETWEIBOERSFROMGROUPCHATAPI;
    }

    @Override
    public R<Group> exe(Group chatGroup, Context context) {
        String url = AutoWeiBoSpiderConstant.Following_FromQueryMes_URL.replace("{gid}",chatGroup.getGid())+System.currentTimeMillis();
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<Group> result = spider.setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url, null,GetWeiBoersFromGroupChatAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(result.getObj());
    }
}

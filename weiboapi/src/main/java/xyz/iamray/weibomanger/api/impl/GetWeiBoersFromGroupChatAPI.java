package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.constant.Constant;
import xyz.iamray.weibomanger.pojo.ChatGroup;
import xyz.iamray.weibomanger.spider.action.GetWeiBoersFromGroupChatAction;

/**
 * @author winray
 * @since v1.0.1
 * 从群聊中获取最近发送消息的三个账号
 * 一般从求粉群中获取获取账号并关注，可以提高粉丝数
 */
public class GetWeiBoersFromGroupChatAPI implements API<ChatGroup, ChatGroup> {

    @Override
    public APINumber getNumber() {
        return APINumber.GETWEIBOERSFROMGROUPCHATAPI;
    }

    @Override
    public R<ChatGroup> exe(ChatGroup chatGroup, Context context) {
        String url = AutoWeiBoSpiderConstant.Following_FromQueryMes_URL.replace("{gid}",chatGroup.getGid())+System.currentTimeMillis();
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<ChatGroup> result = spider.setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url, null,GetWeiBoersFromGroupChatAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(result.getObj());
    }
}

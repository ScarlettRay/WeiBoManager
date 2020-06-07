package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.ChatGroup;
import xyz.iamray.weiboapi.spider.action.GetWeiBoersFromGroupChatAction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 从群聊中获取最近发送消息的三个账号
 * 一般从求粉群中获取获取账号并关注，可以提高粉丝数
 */
public class GetWeiBoersFromGroupChatAPI implements API<ChatGroup, ChatGroup> {

    public final static GetWeiBoersFromGroupChatAPI INSTANCE = new GetWeiBoersFromGroupChatAPI();

    @Override
    public String getNumber() {
        return APINumber.GETWEIBOERSFROMGROUPCHATAPI;
    }

    @Override
    public R<ChatGroup> exe(ChatGroup chatGroup, Context context) {
        String url = AutoWeiBoSpiderConstant.Following_FromQueryMes_URL.replace("{gid}",chatGroup.getGid())+System.currentTimeMillis();
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Map<String,String> map = new HashMap<>(Constant.COMMON_HEADER);
        map.put("Referer","https://api.weibo.com/chat/");
        map.put("Sec-Fetch-Mode","cors");
        map.put("Sec-Fetch-Site","same-origin");
        Result<ChatGroup> result = spider.setRequestHeader(map)
                .setProperty("chat_group",chatGroup)
                .setStarterConfiger(url, null,GetWeiBoersFromGroupChatAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(result.getObj());
    }
}

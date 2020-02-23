package xyz.iamray.weibomanger.api.impl;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.constant.Constant;
import xyz.iamray.weibomanger.pojo.GroupMessage;
import xyz.iamray.weibomanger.spider.action.SendGroupMessageAction;
import xyz.iamray.weibomanger.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 发送群消息
 */
@Slf4j
public class SendGroupMessageAPI implements API<GroupMessage,GroupMessage> {

    @Override
    public APINumber getNumber() {
        return APINumber.SENDGROUPMESSAGEAPI;
    }

    @Override
    public R<GroupMessage> exe(GroupMessage groupMessage, Context context) {
        String url = AutoWeiBoSpiderConstant.GROUP_CHAT_ADD_URL+System.currentTimeMillis();
        Map<String,String> postBody = PostBodyBuildUtil.buildGroupChatParam(groupMessage.getGid(),groupMessage.getContent());
        PostSpider spider = PostSpider.make();
        spider.setCumstomizeExecutorService(context.getExecutorService());
        Result<GroupMessage> result = spider.setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url,postBody, SendGroupMessageAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(result.getObj());
    }
}

package xyz.iamray.weiboapi.api.impl;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Message;
import xyz.iamray.weiboapi.spider.action.SendGroupMessageAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 发送群消息
 */
@Slf4j
public class SendGroupMessageAPI extends AbstractPostAPI<Message,Message> {

    public final static SendGroupMessageAPI INSTANCE = new SendGroupMessageAPI();

    @Override
    public String getNumber() {
        return APINumber.SENDGROUPMESSAGEAPI;
    }

    @Override
    protected String getUrl(Message param, Context context) {
        return AutoWeiBoSpiderConstant.SEND_MESSAGE_URL;
    }

    @Override
    protected Map<String, String> getPostBody(Message groupMessage, Context context) {
        return PostBodyBuildUtil.buildGroupChatBody(groupMessage.getId(),groupMessage.getText());
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return SendGroupMessageAction.getInstance();
    }
}

package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Message;
import xyz.iamray.weiboapi.spider.action.SendPrivateLetterAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 私信api
 */
public class SendPrivateLetterAPI extends AbstractPostAPI<Message,Message> {

    public final static SendPrivateLetterAPI INSTANCE = new SendPrivateLetterAPI();

    @Override
    public String getNumber() {
        return APINumber.SENDPRIVATELETTERAPI;
    }

    @Override
    protected String getUrl(Message param, Context context) {
        return AutoWeiBoSpiderConstant.SendPrivateMes_URL+System.currentTimeMillis();
    }

    @Override
    protected Map<String, String> getPostBody(Message message, Context context) {
        return PostBodyBuildUtil.buildPrivateMesParam(message.getId(),message.getText());
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return SendPrivateLetterAction.INSTANCE;
    }
}

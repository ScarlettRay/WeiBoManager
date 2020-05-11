package xyz.iamray.weiboapi.api.impl.mobile;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.impl.AbstractGetAPI;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Message;
import xyz.iamray.weiboapi.spider.action.mobile.GetMobileMessageListAction;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * I:消息列表第几页
 * O:
 */
public class GetMobileMessageListAPI extends AbstractGetAPI<String, List<Message>> {

    public static final GetMobileMessageListAPI INSTANCE  = new GetMobileMessageListAPI();

    @Override
    public String getNumber() {
        return "GetMobileMessageListAPI";
    }

    @Override
    protected String getUrl(String pageNo, Context context) {
        return AutoWeiBoSpiderConstant.GET_MESSAGE_LIST_URL.replace("{page}",pageNo);
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return GetMobileMessageListAction.getInstance();
    }
}

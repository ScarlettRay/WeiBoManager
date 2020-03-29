package xyz.iamray.weiboapi.api.impl.mobile;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.Message;
import xyz.iamray.weiboapi.spider.action.mobile.GetMobileMessageListAction;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * I:消息列表第几页
 * O:
 */
public class GetMobileMessageListAPI implements API<String, List<Message>> {

    public static final GetMobileMessageListAPI INSTANCE  = new GetMobileMessageListAPI();

    @Override
    public String getNumber() {
        return "GetMobileMessageListAPI";
    }

    @Override
    public R<List<Message>> exe(String pageNo, Context context) {
        String url = AutoWeiBoSpiderConstant.GET_MESSAGE_LIST_URL.replace("{page}",pageNo);
        Result<List<Message>> r = SimpleSpider.make().defaultThreadPool()
                .setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url, GetMobileMessageListAction.getInstance(),context.getHttpClient())
                .start();

        return WeiBoUtil.dealResult(r);
    }
}

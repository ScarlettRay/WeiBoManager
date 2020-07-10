package xyz.iamray.weiboapi.api.impl.mobile;

import org.apache.http.HttpStatus;
import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.AbstractPostAPI;
import xyz.iamray.weiboapi.api.AbstractSessionLessPostAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.AbstractGetAPI;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.spider.action.mobile.GetUidByNickNameAction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetUidByNickNameAPI extends AbstractSessionLessPostAPI<String, WeiBoer> {

    public static final GetUidByNickNameAPI INSTANCE = new GetUidByNickNameAPI();

    @Override
    protected int getHttpListenStatus() {
        return HttpStatus.SC_MOVED_TEMPORARILY;
    }

    @Override
    public String getNumber() {
        return "GetUidByNickNameAPI";
    }

    @Override
    protected String getUrl(String string, Context context) {
        return AutoWeiBoSpiderConstant.MOBAL_HOME_PAGE_URL + string;
    }

    @Override
    protected Map<String, String> getPostBody(String param, Context context) {
        Map<String,String> params = new HashMap<>();
        params.put("act","post");
        return params;
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return GetUidByNickNameAction.INSTANCE;
    }
}

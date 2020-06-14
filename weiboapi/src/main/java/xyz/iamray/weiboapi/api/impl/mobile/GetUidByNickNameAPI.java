package xyz.iamray.weiboapi.api.impl.mobile;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.AbstractGetAPI;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.spider.action.mobile.GetUidByNickNameAction;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetUidByNickNameAPI extends AbstractGetAPI<String, WeiBoer> {

    public static final GetUidByNickNameAPI INSTANCE = new GetUidByNickNameAPI();

    @Override
    public String getNumber() {
        return "GetUidByNickNameAPI";
    }

    @Override
    protected String getUrl(String string, Context context) {
        return AutoWeiBoSpiderConstant.MOBAL_HOME_PAGE_URL + string;
    }

    @Override
    protected CrawlerAction getCrawlerAction() {
        return GetUidByNickNameAction.INSTANCE;
    }
}

package xyz.iamray.flow.bridge;

import xyz.iamray.flow.common.ApiBridgeNumber;
import xyz.iamray.weiboapi.api.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;

/**
 * @author winray
 * @since v1.0.1
 */
public class MobalHotListUrlBridgeAPI implements ApiBridge<Object,String> {
    @Override
    public String getNumber() {
        return ApiBridgeNumber.MOBALHOTLISTURLBRIDGEAPI;
    }

    @Override
    public R<String> exe(Object param, Context context) {
        if(context.getProperty("hotlist_url",String.class) != null){
            return R.ok(context.getProperty("hotlist_url",String.class));
        }
        return R.ok("https://m.weibo.cn/api/container/getIndex?containerid=106003type=25%26t=3%26disable_hot=1%26filter_type=realtimehot");
    }

}

package managerweibo.convertapi;

import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.WeiBoer;

/**
 * @author Ray
 * @create 2020-03-18 17:38:40
 * <p>
 */
public class WeiBoToUrlBridgeAPI implements API<WeiBoer,String> {
    @Override
    public String getNumber() {
        return "WeiBoToUrlBridgeAPI";
    }

    @Override
    public R<String> exe(WeiBoer param, Context context) {
        return R.ok("/weibo?q=%23张萌道歉%23&Refer=top");
    }
}

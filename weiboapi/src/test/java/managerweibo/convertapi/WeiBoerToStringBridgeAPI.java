package managerweibo.convertapi;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.WeiBoer;

/**
 * @author Ray
 * @create 2020-03-19 16:15:48
 * <p>
 */
public class WeiBoerToStringBridgeAPI implements API<WeiBoer,String> {
    @Override
    public String getNumber() {
        return "WeiBoerToStringBridgeAPI";
    }

    @Override
    public R<String> exe(WeiBoer weiBoer, Context context) {
        return R.ok("https://m.weibo.cn/api/container/getIndex?containerid=106003type%3D25%26t%3D3%26disable_hot%3D1%26filter_type%3Drealtimehot&title=%E5%BE%AE%E5%8D%9A%E7%83%AD%E6%90%9C&extparam=pos%3D0_0%26mi_cid%3D100103%26cate%3D10103%26filter_type%3Drealtimehot%26c_type%3D30%26display_time%3D1584597807");
    }
}

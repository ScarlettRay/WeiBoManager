package xyz.iamray.weiboapi.api.bridge.impl;

import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 将爬取得热门微博url转换成restful api;
 */
public class ConvertHotWeiBoUrlBridgeAPI implements ApiBridge<List<String>,List<String>> {

    public final static ConvertHotWeiBoUrlBridgeAPI INSTANCE = new ConvertHotWeiBoUrlBridgeAPI();

    @Override
    public String getNumber() {
        return "ConvertHotWeiBoUrlBridgeAPI";
    }

    @Override
    public R<List<String>> exe(List<String> param, Context context) {
        List<String> re = new ArrayList<>();
        if(param != null){
            for (String s : param) {
                re.add("https://m.weibo.cn/api/container/getIndex" + s.substring(s.indexOf("?")));
            }
        }
        return R.ok(re);
    }
}

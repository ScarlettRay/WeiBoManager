package xyz.iamray.weiboapi.api.bridge.impl;

import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class DealListBridgeAPI<I,O> implements ApiBridge<List<I>,List<O>> {

    @Override
    public String getNumber() {
        return "DealListBridgeAPI";
    }

    @Override
    public R<List<O>> exe(List<I> param, Context context) {
        if(WeiBoUtil.isNull(param))return R.no();
        List<O> re = new ArrayList<>();
        for (I i : param) {
            //re.add();
        }
        return null;
    }
}

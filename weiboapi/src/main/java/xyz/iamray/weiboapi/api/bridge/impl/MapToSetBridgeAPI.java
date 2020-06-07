package xyz.iamray.weiboapi.api.bridge.impl;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 将Map对象的value组装成list返回
 */
public class MapToSetBridgeAPI implements ApiBridge<Map<Object,Object>, List> {

    public final static MapToSetBridgeAPI INSTANCE = new MapToSetBridgeAPI();

    @Override
    public String getNumber() {
        return "MapToSetBridgeAPI";
    }

    @Override
    public R<List> exe(Map<Object,Object> map, Context context) {
        List list = new ArrayList();
        if(map == null || map.isEmpty())return R.ok(list);
        map.entrySet().forEach(e->list.add(e.getValue()));
        return R.ok(list);
    }
}

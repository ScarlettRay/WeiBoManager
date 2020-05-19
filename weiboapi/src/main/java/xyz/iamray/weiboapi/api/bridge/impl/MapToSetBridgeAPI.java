package xyz.iamray.weiboapi.api.bridge.impl;

import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author winray
 * @since v1.0.1
 */
public class MapToSetBridgeAPI implements ApiBridge<Map, List> {

    public final static MapToSetBridgeAPI INSTANCE = new MapToSetBridgeAPI();

    @Override
    public String getNumber() {
        return "MapToSetBridgeAPI";
    }

    @Override
    public R<List> exe(Map map, Context context) {
        List list = new ArrayList();
        if(map == null || map.isEmpty())return R.ok(list);
        Set<Map.Entry> entries =  map.entrySet();
        entries.forEach(e->list.add(e.getValue()));
        return R.ok(list);
    }
}

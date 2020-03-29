package xyz.iamray.flow.bridge;

import xyz.iamray.weiboapi.api.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class ListToOneBridgeAPI implements ApiBridge<List<Object>,Object> {

    public static final ListToOneBridgeAPI INSTANCE = new ListToOneBridgeAPI();

    public static final String INDEX = "ListToOneBridgeAPI-index";

    @Override
    public String getNumber() {
        return "ListToOneBridgeAPI";
    }

    @Override
    public R<Object> exe(List<Object> param, Context context) {
        if(WeiBoUtil.isNull(param))return R.no();
        int i = 0;
        if(param.size() > context.getProperty(INDEX,Integer.class)){
            i = context.getProperty(INDEX,Integer.class);
        }
        return R.ok(param.get(i));
    }
}

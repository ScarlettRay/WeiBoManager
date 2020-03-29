package xyz.iamray.flow.bridge;

import xyz.iamray.weiboapi.api.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.exception.WbException;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class MoreToOneInListBridgeAPI implements ApiBridge<List,List> {

    public final static MoreToOneInListBridgeAPI INSTANCE = new MoreToOneInListBridgeAPI();
    public final static String INDEX = "MoreToOneInListBridgeAPI-index";

    @Override
    public String getNumber() {
        return "MoreToOneBridgeAPI";
    }

    @Override
    public R<List> exe(List collection, Context context) {
        if(collection == null || collection.isEmpty())return R.ok(collection);
        int index = 0;
        if(context.getProperty(INDEX,Integer.class) != null){
            index = context.getProperty(INDEX,Integer.class);
        }
        if(collection.size() <= index){
            index = 0;
        }
        List newCollection = null;
        try {
            newCollection = collection.getClass().newInstance();
        } catch (Exception e) {
            throw new WbException(collection.getClass() + "创建实例失败！");
        }
        newCollection.add(collection.toArray()[index]);
        return R.ok(newCollection);
    }
}


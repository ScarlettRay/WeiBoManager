package xyz.iamray.weiboapi.api.bridge.impl;

import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.Collection;

/**
 * @author winray
 * @since v1.0.1
 */
public class ListsToListBridgeAPI implements ApiBridge<Collection,Collection> {

    public static final ListsToListBridgeAPI INSTANCE = new ListsToListBridgeAPI();

    @Override
    public String getNumber() {
        return "ListsToListBridgeAPI";
    }

    @Override
    public R<Collection> exe(Collection collection, Context context) {
        if(WeiBoUtil.isNotNull(collection)){
            Collection newCollection = null;

            try {
                newCollection = collection.getClass().newInstance();
            } catch (Exception e) {
                throw new WbException(collection.getClass() + "创建实例失败！");
            }
            for (Object o : collection) {
                Collection tmp = (Collection)o;
                newCollection.addAll(tmp);
            }
            return R.ok(newCollection);
        }else{
            return R.ok(collection);
        }
    }
}

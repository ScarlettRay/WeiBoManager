package xyz.iamray.flow;

import xyz.iamray.flow.bridge.*;
import xyz.iamray.flow.impl.forwardhotlist.CommentToBlogBridgeAPI;
import xyz.iamray.weiboapi.api.APIManger;

/**
 * @author winray
 * @since v1.0.1
 */
public class RegisterCenter {

    public static void registerAll() {
        APIManger.register(ConvertHotWeiBoUrlBridgeAPI.INSTANCE);
        APIManger.register(MobalHotListUrlBridgeAPI.INSTANCE);
        APIManger.register(MoreToOneInListBridgeAPI.INSTANCE);
        APIManger.register(MapToSetBridgeAPI.INSTANCE);
        APIManger.register(CommentToBlogBridgeAPI.INSTANCE);
        APIManger.register(ListToOneBridgeAPI.INSTANCE);
    }
}

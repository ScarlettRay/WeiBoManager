package xyz.iamray.flow;

import xyz.iamray.flow.bridge.*;
import xyz.iamray.flow.impl.forwardhotlist.CommentToBlogBridgeAPI;
import xyz.iamray.flow.impl.getfansflow.*;
import xyz.iamray.weiboapi.api.APIManager;

/**
 * @author winray
 * @since v1.0.1
 */
public class RegisterCenter {

    public static void registerAll() {
        APIManager.register(ConvertHotWeiBoUrlBridgeAPI.INSTANCE);
        APIManager.register(MobalHotListUrlBridgeAPI.INSTANCE);
        APIManager.register(MoreToOneInListBridgeAPI.INSTANCE);
        APIManager.register(MapToSetBridgeAPI.INSTANCE);
        APIManager.register(CommentToBlogBridgeAPI.INSTANCE);
        APIManager.register(ListToOneBridgeAPI.INSTANCE);
        //GetFansFlow
        APIManager.register(AddFollowingToGroupWrapperAPI.INSTANCE);
        APIManager.register(BuildGroupsBridgeAPI.INSTANCE);
        APIManager.register(BuildMessagesBridgeAPI.INSTANCE);
        APIManager.register(BuildWeiBoerWithGroupBridgeAPI.INSTANCE);
        APIManager.register(GroupsToWeiBosAPI.INSTANCE);
    }
}

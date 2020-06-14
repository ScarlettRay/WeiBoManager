package xyz.iamray.flow;

import xyz.iamray.flow.impl.ffp.GetOmidsBridgeAPI;
import xyz.iamray.flow.impl.forwardhotlist.CommentToBlogBridgeAPI;
import xyz.iamray.flow.impl.getfansflow.*;
import xyz.iamray.flow.impl.praisecomment.AddCommentToBlogBridgeAPI;
import xyz.iamray.flow.impl.praisecomment.BuildWeiBoerBridegAPI;
import xyz.iamray.weiboapi.api.APIManager;

/**
 * @author winray
 * @since v1.0.1
 */
public class RegisterCenter {

    public static void registerAll() {
        //GetFansFlow
        APIManager.register(AddFollowingToGroupWrapperAPI.INSTANCE);
        APIManager.register(BuildGroupsBridgeAPI.INSTANCE);
        APIManager.register(BuildMessagesBridgeAPI.INSTANCE);
        APIManager.register(BuildWeiBoerWithGroupBridgeAPI.INSTANCE);
        APIManager.register(GroupsToWeiBosAPI.INSTANCE);
        //PraiseCommentFlow
        APIManager.register(BuildWeiBoerBridegAPI.INSTANCE);
        APIManager.register(AddCommentToBlogBridgeAPI.INSTANCE);
        APIManager.register(CommentToBlogBridgeAPI.INSTANCE);

        APIManager.register(GetOmidsBridgeAPI.INSTANCE);
    }
}

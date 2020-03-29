package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.flow.AbstractFlow;
import xyz.iamray.weiboapi.api.impl.FollowWeiboerAPI;
import xyz.iamray.weiboapi.api.impl.GetWeiBoersFromGroupChatAPI;
import xyz.iamray.weiboapi.api.impl.LoginAPI;
import xyz.iamray.weiboapi.api.impl.SendGroupMessageAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetFansFlow extends AbstractFlow {

    private static List<String> apis = new ArrayList<>();

    static {
        apis.add(LoginAPI.INSTANCE.getNumber());
        apis.add(BuildGroupsBridgeAPI.INSTANCE.getNumber());
        apis.add(GetWeiBoersFromGroupChatAPI.INSTANCE.getNumber());
        apis.add(GroupsToWeiBosAPI.INSTANCE.getNumber());
        apis.add(FollowWeiboerAPI.INSTANCE.getNumber());
        apis.add(SendGroupMessageAPI.INSTANCE.getNumber());
    }

    @Override
    protected List<String> getApis() {
        return apis;
    }

    @Override
    public void check() throws Exception {

    }
}

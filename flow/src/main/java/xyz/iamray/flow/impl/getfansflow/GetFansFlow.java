package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.flow.AbstractFlow;
import xyz.iamray.weiboapi.api.impl.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 *
 * 此流程的环境变量有：
 * 1.BuildGroupsBridgeAPI-groups（ArrayList<String>）：群组id
 * 2.GroupsToWeiBosAPI-groupToWeiBoers (Map<String,List<WeiBoer>>):群组id:要关注的组内成员
 * 3.BuildWeiBoerWithGroupBridgeAPI-GroupId（String）:把关注用户加入的分组
 * 4.AddFollowingToGroupWrapperAPI-GroupName(String):新增分组的名称
 * 5.AddFollowingToGroupWrapperAPI-GroupDesc（String）:新增分组的描述
 * 6.BuildMessagesBridgeAPI-message(String):群发消息
 *
 */
public class GetFansFlow extends AbstractFlow {

    private static List<String> apis = new ArrayList<>();

    static {
        apis.add(LoginAPI.INSTANCE.getNumber());
        apis.add(BuildGroupsBridgeAPI.INSTANCE.getNumber());
        apis.add(GetWeiBoersFromGroupChatAPI.INSTANCE.getNumber());
        apis.add(GroupsToWeiBosAPI.INSTANCE.getNumber());
        apis.add(FollowWeiboerAPI.INSTANCE.getNumber());
        apis.add(BuildWeiBoerWithGroupBridgeAPI.INSTANCE.getNumber());
        apis.add(AddFollowingToGroupWrapperAPI.INSTANCE.getNumber());
        apis.add(BuildMessagesBridgeAPI.INSTANCE.getNumber());
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

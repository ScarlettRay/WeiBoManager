package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.flow.AbstractFlow;
import xyz.iamray.weiboapi.api.impl.*;
import xyz.iamray.weiboapi.common.exception.WbException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static Map<String, String> requiredMap = new HashMap<>();


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

        requiredMap.put(BuildGroupsBridgeAPI.GROUPS,"请输入群组id(List<String>)");
        requiredMap.put(AddFollowingToGroupWrapperAPI.GROUP_NAME,"请输入关注分组名");
        requiredMap.put(AddFollowingToGroupWrapperAPI.GROUP_DESC,"请输入关注分组描述");
        requiredMap.put(INIT_PARAM,"请传入第一个API执行需要的参数");
        requiredMap.put(INIT_UID,"请输入微博用户的uid");
    }

    @Override
    protected List<String> getApis() {
        return apis;
    }

    @Override
    public void check() throws Exception {
        String mes = checkUtil(requiredMap);
        if(!mes.isEmpty()){
            throw new WbException(mes);
        }
    }
}

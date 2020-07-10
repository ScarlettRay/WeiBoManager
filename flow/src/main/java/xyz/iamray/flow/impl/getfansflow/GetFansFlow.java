package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.flow.AbstractFlow;


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

    {

    }

}

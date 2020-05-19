package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.FollowingGroup;
import xyz.iamray.weiboapi.pojo.WeiBoer;

/**
 * @author winray
 * @since v1.0.1
 * 构建微博用户，并往里面塞进用户参数，即关注分组
 */
public class BuildWeiBoerWithGroupBridgeAPI implements ApiBridge<WeiBoer,WeiBoer> {

    public static final BuildWeiBoerWithGroupBridgeAPI INSTANCE = new BuildWeiBoerWithGroupBridgeAPI();
    public static final String GROUP_ID = "BuildWeiBoerWithGroupBridgeAPI-GroupId";

    @Override
    public String getNumber() {
        return "BuildWeiBoerWithGroupBridgeAPI";
    }

    @Override
    public R<WeiBoer> exe(WeiBoer weiBoer, Context context) {
        FollowingGroup group = new FollowingGroup();
        String groupId = context.getProperty(GROUP_ID,String.class);
        group.setGroupId(groupId);
        weiBoer.setGroup(group);
        return R.ok(weiBoer);
    }
}

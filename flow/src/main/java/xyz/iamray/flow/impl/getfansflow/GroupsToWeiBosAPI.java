package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.ChatGroup;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public class GroupsToWeiBosAPI implements ApiBridge<List<ChatGroup>, List<WeiBoer>> {

    public static final GroupsToWeiBosAPI INSTANCE = new GroupsToWeiBosAPI();

    public static final String GROUP_TO_WEIBOS = "GroupsToWeiBosAPI-groupToWeiBoers";

    @Override
    public String getNumber() {
        return "GroupsToWeiBosAPI";
    }

    @Override
    public R<List<WeiBoer>> exe(List<ChatGroup> groups, Context context) {
        Map<String,List<WeiBoer>> map = new HashMap<>();
        List<WeiBoer> weiBoers = new ArrayList<>();
        for (ChatGroup group : groups) {
            weiBoers.addAll(group.getWeiBoers());
            map.put(group.getGid(),group.getWeiBoers());
        }
        context.setProperty(GROUP_TO_WEIBOS,map);
        return R.ok(weiBoers);
    }
}

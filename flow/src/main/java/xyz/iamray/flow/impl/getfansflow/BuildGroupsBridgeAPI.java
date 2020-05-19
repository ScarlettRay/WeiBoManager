package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.ChatGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class BuildGroupsBridgeAPI implements ApiBridge<Object, List<ChatGroup>> {
    public static final BuildGroupsBridgeAPI INSTANCE = new BuildGroupsBridgeAPI();
    public static final String GROUPS = "BuildGroupsBridgeAPI-groups";
    @Override
    public String getNumber() {
        return "BuildGroupsBridgeAPI";
    }

    @Override
    public R<List<ChatGroup>> exe(Object param, Context context) {
        List<String> groupIds = context.getProperty(GROUPS, List.class);
        List<ChatGroup> groups = new ArrayList<>();
        groupIds.forEach(e->groups.add(new ChatGroup(e)));
        return R.ok(groups);
    }
}

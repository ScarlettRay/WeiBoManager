package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.weiboapi.api.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class BuildGroupsBridgeAPI implements ApiBridge<Object, List<Group>> {
    public static final BuildGroupsBridgeAPI INSTANCE = new BuildGroupsBridgeAPI();
    public static final String GROUPS = "BuildGroupsBridgeAPI-groups";
    @Override
    public String getNumber() {
        return "BuildGroupsBridgeAPI";
    }

    @Override
    public R<List<Group>> exe(Object param, Context context) {
        List<String> groupIds = context.getProperty(GROUPS, ArrayList.class);
        List<Group> groups = new ArrayList<>();
        groupIds.forEach(e->groups.add(new Group(e)));
        return R.ok(groups);
    }
}

package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.AddFollowingToGroupAPI;
import xyz.iamray.weiboapi.api.impl.CreateNewFollowingGroupAPI;
import xyz.iamray.weiboapi.api.impl.GetGroupListAPI;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.Status;
import xyz.iamray.weiboapi.pojo.FollowingGroup;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.List;
import java.util.UUID;

/**
 * @author winray
 * @since v1.0.1
 * 批量将关注添加到分组
 */
public class AddFollowingToGroupWrapperAPI implements ApiBridge<List<WeiBoer>,List<WeiBoer>> {

    public static final AddFollowingToGroupWrapperAPI INSTANCE = new  AddFollowingToGroupWrapperAPI();

    public static final String GROUP_NAME = "AddFollowingToGroupWrapperAPI-GroupName";
    public static final String GROUP_DESC = "AddFollowingToGroupWrapperAPI-GroupDesc";
    public static final String NEW_GROUP = "AddFollowingToGroupWrapperAPI-NewGroup";

    private static final String DEFAULT_NAME = "HelloWorld";
    private static final String DESC_EG = "此分组由WeiBoManager自动生成";

    @Override
    public String getNumber() {
        return "AddFollowingToGroupWrapperAPI";
    }

    @Override
    public R<List<WeiBoer>> exe(List<WeiBoer> weiBoers, Context context) {
        FollowingGroup group = null;
        for (int i = 0; i < weiBoers.size(); i++) {
            WeiBoer weiBoer = weiBoers.get(i);
            if(group != null){
                weiBoer.setGroup(group);
            }
            if(weiBoer.getGroup() == null || weiBoer.getGroup().getGroupId() == null){
                group = buildNewGroup(context);
                weiBoer.setGroup(group);
            }
            R<String> r = AddFollowingToGroupAPI.INSTANCE.exe(weiBoer,context);
            if("100098".equals(r.getRe())){
                group = buildNewGroup(context);
                i--;//微博用户重新加入
            }
        }
        return R.ok(weiBoers);
    }

    /**
     * 创建新分组
     * @param context
     */
    public FollowingGroup buildNewGroup(Context context){
        FollowingGroup group = new FollowingGroup();
        group.setName(context.getProperty(GROUP_NAME,String.class,DEFAULT_NAME));
        group.setDescription(context.getProperty(GROUP_DESC,String.class,DESC_EG));
        group.setPublic(false);
        R<FollowingGroup> r = CreateNewFollowingGroupAPI.INSTANCE.exe(group,context);
        if(r.getStatus().equals(Status.ERR)){
            //命名冲突，重试
            group.setName(UUID.randomUUID().toString().substring(0,16));
            r = CreateNewFollowingGroupAPI.INSTANCE.exe(group,context);
        }

        setGid(r.getRe(),context);
        context.setProperty(NEW_GROUP,group);
        return group;
    }

    private FollowingGroup setGid(FollowingGroup group,Context context){
        R<List<FollowingGroup>> groups = GetGroupListAPI.INSTANCE.exe(null,context);
        for (FollowingGroup followingGroup : groups.getRe()) {
            if(group.getName().equals(followingGroup.getName())){
                group.setGroupId(followingGroup.getGroupId());
                return group;
            }
        }
        return group;
    }
}

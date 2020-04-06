package xyz.iamray.flow.impl.getfansflow;


import lombok.extern.slf4j.Slf4j;
import xyz.iamray.flow.Flow;
import xyz.iamray.flow.LoginUtil;
import xyz.iamray.flow.RegisterCenter;
import xyz.iamray.flow.TestConstant;
import xyz.iamray.flow.common.SpiderPool;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.ContextBuilder;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class GetFansFlowTest {


    public static void main(String[] args) throws Exception {
        RegisterCenter.registerAll();
        LoginUtil.createSession();//创建会话
        GetFansFlow flow = new GetFansFlow();
        List<String> groups = Arrays.asList("4480524380658668","4483270710201090","4486374369563128");
        Context context = ContextBuilder.buildContext(SpiderPool.executorService);
        flow.put(BuildGroupsBridgeAPI.GROUPS,groups);
        flow.put(AddFollowingToGroupWrapperAPI.GROUP_NAME,"测试分组");
        flow.put(AddFollowingToGroupWrapperAPI.GROUP_DESC,"测试分组描述");
        flow.put(Flow.INIT_PARAM, TestConstant.WEIBOER);
        flow.put(Flow.INIT_UID,TestConstant.UID);
        log.info(flow.execute(context).toString());
    }


}

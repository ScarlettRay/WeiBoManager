package xyz.iamray.flow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.flow.common.SpiderPool;
import xyz.iamray.flow.impl.forwardhotlist.ForwardHotListFlow;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.bridge.impl.ListToOneBridgeAPI;
import xyz.iamray.weiboapi.api.context.Context;

@Slf4j
public class ForwardHotListFlowTest {

    @Test
    public void test() throws Exception {
        RegisterCenter.registerAll();
        LoginUtil.createSession();
        ForwardHotListFlow flow = new ForwardHotListFlow();
        Context context = ContextBuilder.buildContext(SpiderPool.executorService);
        flow.put("MoreToOneBridgeAPI-index",1);
        flow.put(ListToOneBridgeAPI.INDEX,1);
        flow.put(Flow.INIT_PARAM,TestConstant.WEIBOER);
        flow.put(Flow.INIT_UID,TestConstant.UID);
        log.info(flow.execute(context).toString());
    }
}

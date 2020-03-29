package xyz.iamray.flow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.flow.bridge.ListToOneBridgeAPI;
import xyz.iamray.flow.impl.forwardhotlist.ForwardHotListFlow;

@Slf4j
public class ForwardHotListFlowTest {

    @Test
    public void test() throws Exception {
        RegisterCenter.registerAll();
        ForwardHotListFlow flow = new ForwardHotListFlow();
        flow.put("MoreToOneBridgeAPI-index",1);
        flow.put(ListToOneBridgeAPI.INDEX,1);
        flow.put(Flow.INIT_PARAM,TestConstant.WEIBOER);
        flow.put(Flow.INIT_UID,TestConstant.UID);
        log.info(flow.execute().toString());
    }
}

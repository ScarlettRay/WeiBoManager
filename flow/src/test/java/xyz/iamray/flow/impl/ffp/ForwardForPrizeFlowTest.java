package xyz.iamray.flow.impl.ffp;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.flow.Flow;
import xyz.iamray.flow.RegisterCenter;
import xyz.iamray.flow.TestConstant;
import xyz.iamray.flow.common.SpiderPool;
import xyz.iamray.flow.impl.praisecomment.BuildWeiBoerBridegAPI;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.context.Context;

import java.util.Arrays;

@Slf4j
public class ForwardForPrizeFlowTest {


    public static void main(String[] args) throws Exception {
        RegisterCenter.registerAll();
        ForwardForPrizeFlow flow = new ForwardForPrizeFlow();
        //LoginUtil.createSession();
        Context context = ContextBuilder.buildContext(SpiderPool.executorService);
        flow.put(BuildWeiBoerBridegAPI.WEIBOERS, Arrays.asList("5581785513"));
        flow.put(Flow.INIT_PARAM, TestConstant.WEIBOER);
        flow.put(Flow.INIT_UID,TestConstant.UID);
        log.info(flow.execute(context).toString());
    }
}

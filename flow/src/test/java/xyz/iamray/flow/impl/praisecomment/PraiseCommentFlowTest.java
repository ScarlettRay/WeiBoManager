package xyz.iamray.flow.impl.praisecomment;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.flow.Flow;
import xyz.iamray.flow.LoginUtil;
import xyz.iamray.flow.RegisterCenter;
import xyz.iamray.flow.TestConstant;
import xyz.iamray.flow.common.SpiderPool;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.bridge.impl.ListToOneBridgeAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.CrawlWeiBoAPI;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PraiseCommentFlowTest {

    @Test
    public void test() {
        testFlow();
    }

    public static void testFlow(){
        LoginUtil.createSession();
        RegisterCenter.registerAll();
        Context context = ContextBuilder.buildContext(SpiderPool.executorService);
        context.setProperty(BuildWeiBoerBridegAPI.WEIBOERS,getWeiBoers());
        try {
            getFlow().execute(context);
        } catch (Exception e) {
            log.error("Exception",e);
        }
    }

    public static List<String> getWeiBoers(){
        List<String> list = new ArrayList<>();
        list.add("1005056012758011");
        return list;
    }

    public static Flow getFlow(){
        PraiseCommentFlow flow = new PraiseCommentFlow(new BlogFilter());
        flow.put(Flow.INIT_PARAM, TestConstant.WEIBOER);
        flow.put(Flow.INIT_UID,TestConstant.UID);
        flow.put(BuildWeiBoerBridegAPI.WEIBOERS,getWeiBoers());
        flow.put(CrawlWeiBoAPI.CRAWL_PAGE,"1");
        flow.put(ListToOneBridgeAPI.INDEX,0);
        return flow;
    }
}

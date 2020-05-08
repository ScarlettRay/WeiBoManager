package xyz.iamray.flow.impl.praisecomment;


import lombok.extern.slf4j.Slf4j;
import xyz.iamray.flow.LoginUtil;
import xyz.iamray.flow.common.SpiderPool;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.ContextBuilder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PraiseCommentFlowTest {

    public void testFlow(){
        LoginUtil.createSession();
        Context context = ContextBuilder.buildContext(SpiderPool.executorService);
        context.setProperty(BuildWeiBoerBridegAPI.WEIBOERS,getWeiBoers());
        PraiseCommentFlow flow = new PraiseCommentFlow();
        try {
            flow.execute(context);
        } catch (Exception e) {
            log.error("Exception",e);
        }
    }

    public List<String> getWeiBoers(){
        List<String> list = new ArrayList<>();
        //list.add();
        return list;
    }
}

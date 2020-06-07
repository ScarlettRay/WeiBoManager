package xyz.iamray.weiboapi.api.impl;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weiboapi.api.APIManager;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
public class CrawlWeiBoInUserPageAPITest {

    @Test
    public void test(){
        List<String> apis = new ArrayList<>();
        apis.add(CrawlWeiBoInUserPageAPI.INSTANCE.getNumber());
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid("5581785513");
        Context context = ContextBuilder.buildContext(Executors.newSingleThreadExecutor());
        R<List<Blog>> r = APIManager.call(weiBoer,apis, null, context);
        r.getRe().forEach(e->log.info(e.toString()));
    }

}

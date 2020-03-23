package xyz.iamray.weiboapi.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weiboapi.api.APIManger;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
public class CrawlWeiBoAPITest {

    @Test
    public void testCrawlWeiBoApi(){
        List<String> apis = new ArrayList<>();
        apis.add(APINumber.CRAWLWEIBOAPI);
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid("1006051782432341");
        Context context = ContextBuilder.buildContext(Executors.newSingleThreadExecutor());
        context.setProperty("crawl_page","1");
        R<List<Blog>> r = APIManger.call(weiBoer,apis, null, context);
        r.getRe().forEach(e->log.info(e.toString()));
    }

}

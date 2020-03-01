package xyz.iamray.weibomanger.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weibomanger.api.APIManger;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.api.ContextBuilder;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
public class CrawlWeiBoAPITest {

    @Test
    public void testCrawlWeiBoApi(){
        List<APINumber> apis = new ArrayList<>();
        apis.add(APINumber.CRAWLWEIBOAPI);
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid("1006051782432341");
        Context context = ContextBuilder.buildContext(Executors.newSingleThreadExecutor());
        context.setProperty("crawl_page","1");
        R<List<Blog>> r = APIManger.call(weiBoer,apis, null, context);
        r.getRe().forEach(e->log.info(e.toString()));
    }

}

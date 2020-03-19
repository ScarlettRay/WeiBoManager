package managerweibo;

import lombok.extern.slf4j.Slf4j;
import managerweibo.convertapi.*;
import org.junit.Test;
import xyz.iamray.weibomanger.api.APIManger;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.api.ContextBuilder;
import xyz.iamray.weibomanger.api.impl.TestConstant;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author Ray
 * @create 2020-03-18 15:05:38
 * 定义一套动作
 * 1.爬取微博热榜和旗下的评论
 * 2.转发之
 * 一小时爬取一次，一次爬前十条，记录已发送的mid,防止重复发送
 * <p>
 */
@Slf4j
public class CrawlHotWeiBoAndForward {

    @Test
    public void start(){
        APIManger.register(new BlogsToBlogBridgeAPI());
        APIManger.register(new WeiBoToUrlBridgeAPI());
        APIManger.register(new MoewToOneAPI());
        APIManger.register(new BlogPrepareBridgeAPI());
        APIManger.register(new WeiBoerToStringBridgeAPI());

        List<String> apis = new ArrayList<>();
        apis.add(APINumber.LOGINAPI.name());
        apis.add("WeiBoerToStringBridgeAPI");
        apis.add(APINumber.CRAWLMOBALHOTLISTAPI.name());
        apis.add("MoewToOneAPI");
        apis.add(APINumber.CRAWLWEIBOBYURLAPI.name());
        apis.add("BlogsToBlogBridgeAPI");
        apis.add(APINumber.GETMOBALHOTCOMMENTAPI.name());
        apis.add("BlogPrepareBridgeAPI");
        apis.add(APINumber.DELIVERBLOGAPI.name());
        Context context = ContextBuilder.buildContext(Executors.newSingleThreadExecutor());
        R<Blog> re = APIManger.call(TestConstant.WEIBOER,apis,"7364250637",context);
        log.info(re.getRe().toString());
    }
}

package managerweibo;

import lombok.extern.slf4j.Slf4j;
import managerweibo.convertapi.*;
import org.junit.Test;
import xyz.iamray.weiboapi.api.APIManager;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.TestConstant;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;

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
        APIManager.register(new BlogsToBlogBridgeAPI());
        APIManager.register(new WeiBoToUrlBridgeAPI());
        APIManager.register(new MoewToOneAPI());
        APIManager.register(new BlogPrepareBridgeAPI());
        APIManager.register(new WeiBoerToStringBridgeAPI());

        List<String> apis = new ArrayList<>();
        apis.add(APINumber.LOGINAPI);
        apis.add("WeiBoerToStringBridgeAPI");
        apis.add(APINumber.CRAWLMOBALHOTLISTAPI);
        apis.add("MoewToOneAPI");
        apis.add(APINumber.CRAWLWEIBOBYURLAPI);
        apis.add("BlogsToBlogBridgeAPI");
        apis.add(APINumber.GETMOBALHOTCOMMENTAPI);
        apis.add("BlogPrepareBridgeAPI");
        apis.add(APINumber.DELIVERBLOGAPI);
        Context context = ContextBuilder.buildContext(Executors.newSingleThreadExecutor());
        R<Blog> re = APIManager.call(TestConstant.WEIBOER,apis,"7364250637",context);
        log.info(re.getRe().toString());
    }
}

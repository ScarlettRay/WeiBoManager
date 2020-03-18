package managerweibo;

import org.junit.Test;
import xyz.iamray.weibomanger.api.APIManger;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.api.ContextBuilder;
import xyz.iamray.weibomanger.api.impl.LoginAPI;
import xyz.iamray.weibomanger.api.impl.TestConstant;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.Comment;

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
public class CrawlHotWeiBoAndForward {

    @Test
    public void start(){
        APIManger.register(new BlogsToBlogBridgeAPI());
        APIManger.register(new WeiBoToUrlBridgeAPI());
        List<String> apis = new ArrayList<>();
        apis.add(APINumber.LOGINAPI.name());
        apis.add("WeiBoToUrlBridgeAPI");
        apis.add(APINumber.CRAWLWEIBOBYURLAPI.name());
        apis.add("BlogsToBlogBridgeAPI");
        apis.add(APINumber.GETMOBALHOTCOMMENTAPI.name());
        Context context = ContextBuilder.buildContext(Executors.newSingleThreadExecutor());
        R<Blog> re = APIManger.call(TestConstant.WEIBOER,apis,null,context);

    }
}

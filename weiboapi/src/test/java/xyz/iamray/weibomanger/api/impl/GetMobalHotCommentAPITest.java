package xyz.iamray.weibomanger.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weibomanger.api.APIManger;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.api.ContextBuilder;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
public class GetMobalHotCommentAPITest {

    @Test
    public void testCrawlComment(){
        List<String> apis = new ArrayList<>();
        apis.add(APINumber.GETMOBALHOTCOMMENTAPI.name());
        Blog blog = new Blog();
        blog.setMid("4482129221905543");
        Context context = ContextBuilder.buildContext(Executors.newSingleThreadExecutor());
        R<List<Comment>> r =  APIManger.call(blog,apis,null,context);
        r.getRe().forEach(e->log.info(e.toString()));
    }

}

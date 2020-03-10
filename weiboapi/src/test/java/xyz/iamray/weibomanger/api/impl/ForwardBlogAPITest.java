package xyz.iamray.weibomanger.api.impl;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weibomanger.api.APIManger;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.ContextBuilder;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ForwardBlogAPITest {

    @Test
    public void testForwardBlogApi(){
        List<String> apis = new ArrayList<>();
        APIManger.register(new CustomDemoAPI());
        apis.add(APINumber.LOGINAPI.name());
        apis.add("CustomApi");
        apis.add(APINumber.FORWARDBLOGAPI.name());
        WeiBoer weiboer = new WeiBoer("1454377676@qq.com","sina3211922");
        R<Blog> r = APIManger.call(weiboer,apis, "5945738590", ContextBuilder.buildContext());
        log.info(r.getRe().getMid() );
    }

}

package xyz.iamray.weiboapi.api.impl;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weiboapi.api.APIManager;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.impl.convertapi.CustomDemoAPI;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.utils.TestConstant;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ForwardBlogAPITest {

    @Test
    public void testForwardBlogApi(){
        List<String> apis = new ArrayList<>();
        APIManager.register(new CustomDemoAPI());
        apis.add(APINumber.LOGINAPI);
        apis.add("CustomApi");
        apis.add(APINumber.FORWARDBLOGAPI);
        R<Blog> r = APIManager.call(TestConstant.WEIBOER,apis, "7364250637", ContextBuilder.buildContext());
        log.info(r.getRe().getMid() );
    }

}

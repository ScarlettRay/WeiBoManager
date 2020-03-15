package xyz.iamray.weibomanger.api.impl;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weibomanger.api.APIManger;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.ContextBuilder;
import xyz.iamray.weibomanger.api.impl.convertapi.Login2DeliverBlogAPI;
import xyz.iamray.weibomanger.common.R;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class DeliverBlogAPITest {

    @Test
    public void testDeliverBlogApi(){
        List<String> apis = new ArrayList<>();
        apis.add(APINumber.LOGINAPI.name());
        apis.add("Login2DeliverBlogAPI");
        apis.add(APINumber.DELIVERBLOGAPI.name());
        APIManger.register(new Login2DeliverBlogAPI());
        R<String> r = APIManger.call(TestConstant.WEIBOER,apis, "7364250637", ContextBuilder.buildContext());
        log.info(r.getRe());
    }
}

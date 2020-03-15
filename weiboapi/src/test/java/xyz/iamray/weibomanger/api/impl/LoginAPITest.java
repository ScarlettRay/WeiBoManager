package xyz.iamray.weibomanger.api.impl;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weibomanger.api.APIManger;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.ContextBuilder;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LoginAPITest {

    @Test
    public void testLoginAPI(){
        List<String> apis = new ArrayList<>();
        apis.add(APINumber.LOGINAPI.name());
        R<WeiBoer> r = APIManger.call(TestConstant.WEIBOER,apis, null,ContextBuilder.buildContext());
        log.info("登陆成功：" + r.getRe().getUid());

    }

    @Test
    public void testLength(){
    }
}

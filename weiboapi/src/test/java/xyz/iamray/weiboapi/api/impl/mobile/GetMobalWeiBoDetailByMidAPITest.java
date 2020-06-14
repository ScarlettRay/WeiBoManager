package xyz.iamray.weiboapi.api.impl.mobile;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weiboapi.api.APIManager;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Blog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Slf4j
public class GetMobalWeiBoDetailByMidAPITest {

    @Test
    public void test(){
        List<String> apis = new ArrayList<>();
        apis.add(GetMobalWeiBoDetailByMidAPI.INSTANCE.getNumber());
        Context context = ContextBuilder.buildContext(Executors.newSingleThreadExecutor());
        R<Blog> r = APIManager.call("4514607240391463",apis, null, context);
        log.info(r.getRe().toString());
    }
}

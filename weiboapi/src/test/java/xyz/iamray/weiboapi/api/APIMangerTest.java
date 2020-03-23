package xyz.iamray.weiboapi.api;


import org.junit.Test;
import xyz.iamray.link.SpiderUtil;
import xyz.iamray.weiboapi.api.impl.CrawlWeiBoByUrlAPI;

public class APIMangerTest {

    @Test
    public void test(){
        API api = new CrawlWeiBoByUrlAPI();
        String[] classStrs1 = SpiderUtil.getClassArgumentsFromInterface(api.getClass());
        System.out.println("+++++++++++++++++++");
        for (String s : classStrs1) {
            System.out.println(s);
        }
    }
}

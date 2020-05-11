package xyz.iamray.weiboapi.utils;

import org.junit.Assert;
import org.junit.Test;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.impl.CrawlWeiBoByUrlAPI;
import xyz.iamray.weiboapi.api.impl.PraiseWeiBoAPI;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParamConvertorTest {

    @Test
    public void test(){
        API api = new CrawlWeiBoByUrlAPI();
        Object re = new ArrayList<WeiBoer>();
        System.out.println(ParamConvertor.checkAndConvert(re,api).getClass());
    }

    /**
     * ArrayList<WeiBoer> --> List<String>
     */
    @Test
    public void test1(){
        API test = new ListAPI();
        Object re = new ArrayList<WeiBoer>();
        Assert.assertEquals(ParamConvertor.checkAndConvert(re,test).getClass(),ArrayList.class);
    }

    /**
     * String ---> List<String>
     */
    @Test
    public void test2(){
        API api = new ListAPI();
        String re = new String();
        Assert.assertEquals(ParamConvertor.checkAndConvert(re,api).getClass(),ArrayList.class);
    }


    @Test
    public void test3(){
        API api = new StringAPI();
        List<String> re = new ArrayList<>();
        re.add("test");
        Assert.assertEquals(ParamConvertor.checkAndConvert(re,api).getClass(),String.class);
    }

    @Test
    public void test4(){
        API api = new StringAPI();
        Set<String> re = new HashSet<>();
        re.add("test");
        Assert.assertEquals(ParamConvertor.checkAndConvert(re,api).getClass(),String.class);
    }

    @Test
    public void test5(){
        API api = new ListAPI();
        Set<String> re = new HashSet<>();
        re.add("test");
        Assert.assertEquals(ParamConvertor.checkAndConvert(re,api).getClass(),ArrayList.class);
    }

    @Test
    public void test6(){
        API api = new PraiseWeiBoAPI();
        ParamConvertor.getRowType(api);
    }


    private class ListAPI implements API<List<String>,String>{

        @Override
        public String getNumber() {
            return null;
        }

        @Override
        public R<String> exe(List<String> param, Context context) {
            return null;
        }
    }

    private class StringAPI implements API<String,String>{

        @Override
        public String getNumber() {
            return null;
        }

        @Override
        public R<String> exe(String param, Context context) {
            return null;
        }
    }
}

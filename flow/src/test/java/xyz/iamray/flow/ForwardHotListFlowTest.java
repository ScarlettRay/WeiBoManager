package xyz.iamray.flow;

import org.junit.Test;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.impl.CrawlWeiBoByUrlAPI;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ForwardHotListFlowTest {

    @Test
    public void test(){
        API api = new CrawlWeiBoByUrlAPI();
        Object re = new ArrayList<WeiBoer>();

        Type apiArg = getRowType(api);
        if(apiArg instanceof Class){
            if(re.getClass().equals(apiArg)){

            }else{

            }
        }else if(apiArg instanceof ParameterizedType){
            //有泛型，则继续比较

        }
    }

    /**
     * 获取api的第一个泛型类
     * @param api
     * @return
     */
    private static Type getRowType(API api){
        Class<?> clazz = api.getClass();
        Type[] types = clazz.getGenericInterfaces();
        for(Type type:types){
            if(type.getTypeName().contains("xyz.iamray.weiboapi.api.API")){
                Type[] typeArgs = ((ParameterizedType)type).getActualTypeArguments();
                return typeArgs[0];
            }
        }
        throw new WbException("API:" + api.getNumber() + " 没有获取到泛型，请检查！");
    }
}

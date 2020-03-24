package xyz.iamray.flow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.api.impl.CrawlWeiBoByUrlAPI;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class ForwardHotListFlowTest {

    @Test
    public void test(){
        API api = new CrawlWeiBoByUrlAPI();
        Object re = new ArrayList<WeiBoer>();


        System.out.println(checkAndConvert(re,api).getClass());
    }

    @Test
    public void test1(){
        API test = new TestAPI();
        Object re = new ArrayList<WeiBoer>();
        System.out.println(checkAndConvert(re,test).getClass());
    }

    @Test
    public void test2(){
        log.info(String.valueOf(API.class.isAssignableFrom(ApiBridge.class)));
    }

    public Object checkAndConvert(Object re,API api){
        Type apiArg = getRowType(api);
        if(apiArg instanceof Class){
            if(re.getClass().equals(apiArg)|| re.getClass().isAssignableFrom((Class<?>) apiArg)){
                System.out.println(re.getClass() + "---" + apiArg);
                return re;
            }else{
                Class apiClazz = (Class)apiArg;
                if(re instanceof Collection && !(apiClazz.isAssignableFrom(Collection.class))){
                    System.out.println(re.getClass() + "---" + apiClazz);
                    Collection collection = (Collection)re;
                    Iterator it = collection.iterator();
                    while (it.hasNext()){
                        return it.next();
                    }
                }else if(!(re instanceof Collection) && apiClazz.isAssignableFrom(Collection.class)){
                    System.out.println(re.getClass() + "---" + apiClazz);
                    try {
                        Collection collection = (Collection)apiClazz.newInstance();
                        collection.add(re);
                        return collection;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else if(re instanceof Collection && apiClazz.isAssignableFrom(Collection.class)){
                    System.out.println(re.getClass() + "---" + apiClazz);
                    try {
                        Collection collection = (Collection)apiClazz.newInstance();
                        collection.addAll((Collection) re);
                        return collection;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    throw new WbException("类型不符，允许类型为" + apiClazz + ",而实际类型为" + re.getClass());
                }
            }
        }else if(apiArg instanceof ParameterizedType){
            //有泛型，则继续比较
            Type type = ((ParameterizedType) apiArg).getRawType();
            Class apiClazz = null;
            if(type instanceof Class){
                apiClazz = (Class)type;
            }else{
               throw new WbException("((ParameterizedType) apiArg).getRawType() 得到新类型：" + type.getClass());
            }
            if(re.getClass().equals(apiClazz) || re.getClass().isAssignableFrom(apiClazz)){
                return re;
            }else if(re instanceof Collection && !(apiClazz.isAssignableFrom(Collection.class))){
                //判断apiArg的泛型是否与re的类相同
                System.out.println(re.getClass() + "---" + apiClazz);
                Collection collection = (Collection)re;
                Iterator it = collection.iterator();
                while (it.hasNext()){
                    return it.next();
                }
            }else if(!(re instanceof Collection) && apiClazz.isAssignableFrom(Collection.class)){
                Type tmpType = ((ParameterizedType) apiArg).getActualTypeArguments()[0];
                Class tmpClazz = tmpType instanceof Class ? (Class) tmpType:(Class) ((ParameterizedType)tmpType).getRawType();
                if(re.getClass().equals(tmpClazz)){
                        try {
                            Collection collection = (Collection)tmpClazz.newInstance();
                            collection.add(re);
                            return collection;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }else{
                    throw new WbException("类型不符，允许类型为" + tmpClazz + ",而实际类型为" + re.getClass());
                }
            }else if(re instanceof Collection && apiClazz.isAssignableFrom(Collection.class)){
                try {
                    Collection collection = (Collection)apiClazz.newInstance();
                    collection.addAll((Collection) re);
                    return collection;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                throw new WbException("类型不符，允许类型为" + apiClazz + ",而实际类型为" + re.getClass());
            }
        }else{
            throw new WbException("出现未发现的Type类型：" + apiArg.getClass());
        }
        throw new WbException("类型不符，允许类型为" + api.getClass() + ",而实际类型为" + re.getClass());
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

     private class TestAPI implements API<List<String>,String>{

         @Override
         public String getNumber() {
             return null;
         }

         @Override
         public R<String> exe(List<String> param, Context context) {
             return null;
         }

     }
}

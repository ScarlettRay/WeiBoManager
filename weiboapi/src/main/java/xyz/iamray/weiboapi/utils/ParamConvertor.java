package xyz.iamray.weiboapi.utils;

import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.common.exception.WbException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author winray
 * @since v1.0.1
 */
public final class ParamConvertor {

    /**
     * 检查re的类型是否为api的输入类型
     * 如果两者有一个是列表，需要拿列表里面的泛型比较
     * TODO
     * @param re
     * @param api
     */
    public static Object checkAndConvert(Object re, API api){
        if(re == null)return null;
        Type apiArg = getRowType(api);
        if(apiArg instanceof Class){
            if(re.getClass().equals(apiArg)
                    || ((Class<?>) apiArg).isAssignableFrom(re.getClass())
                    || re.getClass().isAssignableFrom((Class<?>) apiArg)){
                return re;
            }else{
                Class apiClazz = (Class)apiArg;
                if(re instanceof Collection && !(Collection.class.isAssignableFrom(apiClazz))){
                    Collection collection = (Collection)re;
                    Iterator it = collection.iterator();
                    while (it.hasNext()){
                        return it.next();
                    }
                    return null;
                }else if(!(re instanceof Collection) && Collection.class.isAssignableFrom(apiClazz)){
                    Collection collection = buildCollectionInstance(apiClazz);
                    collection.add(re);
                    return collection;
                }else if(re instanceof Collection && Collection.class.isAssignableFrom(apiClazz)){
                    Collection collection = buildCollectionInstance(apiClazz);
                    collection.addAll((Collection) re);
                    return collection;
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
            if(re.getClass().equals(apiClazz)
                    || apiClazz.isAssignableFrom(re.getClass())
                    || re.getClass().isAssignableFrom(apiClazz)){
                return re;
            }else if(re instanceof Collection && !(Collection.class.isAssignableFrom(apiClazz))){
                //判断apiArg的泛型是否与re的类相同
                System.out.println(re.getClass() + "---" + apiClazz);
                Collection collection = (Collection)re;
                Iterator it = collection.iterator();
                while (it.hasNext()){
                    return it.next();
                }
                return null;
            }else if(!(re instanceof Collection) && Collection.class.isAssignableFrom(apiClazz)){
                Type tmpType = ((ParameterizedType) apiArg).getActualTypeArguments()[0];
                Class tmpClazz = tmpType instanceof Class ? (Class) tmpType:(Class) ((ParameterizedType)tmpType).getRawType();
                if(re.getClass().equals(tmpClazz)){
                    Collection collection = buildCollectionInstance(apiClazz);
                    collection.add(re);
                    return collection;
                }else{
                    throw new WbException("类型不符，允许类型为" + tmpClazz + ",而实际类型为" + re.getClass());
                }
            }else if(re instanceof Collection && Collection.class.isAssignableFrom(apiClazz)){
                Collection collection = buildCollectionInstance(apiClazz);
                collection.addAll((Collection) re);
                return collection;
            }else{
                throw new WbException("类型不符，允许类型为" + apiClazz + ",而实际类型为" + re.getClass());
            }
        }else{
            throw new WbException("出现未发现的Type类型：" + apiArg.getClass());
        }
    }

    /**
     * 获取api的第一个泛型类
     * @param api
     * @return
     */
    public static Type getRowType(API api){
        Class<?> clazz = api.getClass();
        Type[] types = clazz.getGenericInterfaces();
        for(Type type:types){
            if(API.class.isAssignableFrom(((ParameterizedTypeImpl) type).getRawType())){
                Type[] typeArgs = ((ParameterizedType)type).getActualTypeArguments();
                return typeArgs[0];
            }
        }
        throw new WbException("API:" + api.getNumber() + " 没有获取到泛型，请检查！");
    }

    private static Collection buildCollectionInstance(Class cls){
        if(!cls.isInterface()){
            try {
                cls.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(List.class.isAssignableFrom(cls)){
            return new ArrayList();
        }else if(Set.class.isAssignableFrom(cls)){
            return new HashSet();
        }else if(Queue.class.isAssignableFrom(cls)){
            return new PriorityQueue();
        }
        throw new WbException("无法为" + cls + "创建实例！");
    }
}

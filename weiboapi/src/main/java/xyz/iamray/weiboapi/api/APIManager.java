package xyz.iamray.weiboapi.api;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.weiboapi.api.bridge.impl.*;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.*;
import xyz.iamray.weiboapi.api.impl.mobile.CrawlMobalHotListAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetMobalHotCommentAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetMobalWeiBoByUrlAPI;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.session.SessionManger;
import xyz.iamray.weiboapi.utils.ParamConvertor;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author winray
 * @since v1.0.1
 * API管理者
 * API注册
 * API调用入口
 */
@Slf4j
public class APIManager {

    private static final Map<String,API> API_MAP = new HashMap<>();

    //注册系统自带的api
    static{
        register(AddFollowingToGroupAPI.INSTANCE);
        register(CrawlWeiBoAPI.INSTANCE);
        register(CreateNewFollowingGroupAPI.INSTANCE);
        register(DeliverBlogAPI.INSTANCE);
        register(FollowWeiboerAPI.INSTANCE);
        register(ForwardBlogAPI.INSTANCE);
        register(GetMobalHotCommentAPI.INSTANCE);
        register(GetWeiBoersFromGroupChatAPI.INSTANCE);
        register(LoginAPI.INSTANCE);
        register(SendGroupMessageAPI.INSTANCE);
        register(SendPrivateLetterAPI.INSTANCE);
        register(UploadImageAPI.INSTANCE);
        register(GetMobalHotCommentAPI.INSTANCE);
        register(CrawlWeiBoByUrlAPI.INSTANCE);
        register(CrawlMobalHotListAPI.INSTANCE);
        register(GetMobalWeiBoByUrlAPI.INSTANCE);
        register(PraiseWeiBoAPI.INSTANCE);
        register(DeliverCommentAPI.INSTANCE);
        register(ConvertHotWeiBoUrlBridgeAPI.INSTANCE);
        register(MobalHotListUrlBridgeAPI.INSTANCE);
        register(MoreToOneInListBridgeAPI.INSTANCE);
        register(MapToSetBridgeAPI.INSTANCE);
        register(ListToOneBridgeAPI.INSTANCE);
        register(ListsToListBridgeAPI.INSTANCE);
        register(CrawlWeiBoInUserPageAPI.INSTANCE);
    }

    public static boolean register(API api){
        if(!API_MAP.containsKey(api.getNumber())){
            API_MAP.put(api.getNumber(),api);
            return true;
        }
        return false;
    }

    /**
     * 注册api
     * @param classPath api类路径
     */
    public static void register(String classPath)  {
        try {
            Class<?> apiClass = APIManager.class.getClassLoader().loadClass(classPath);
            API<?,?> api = (API<?,?>) apiClass.getConstructor().newInstance();
            if(api.getNumber() == null || API_MAP.containsKey(api.getNumber())){
                throw new WbException("API编码为空或者已被注册");
            }
            API_MAP.put(api.getNumber(),api);
        }catch (Exception e){
            log.error(classPath + "注册失败：发生如下错误:" + e);
        }
    }

    /**
     * api调用接口
     * 上下文环境构建
     * @param obj 初始api的参数
     * @param apiNumbers
     * @param uid
     */
    public static <T,E> R<E> call(T obj, List<String> apiNumbers, String uid, Context context){
        if(apiNumbers.isEmpty())return null;
        int i = 0;
        R<?> r = R.ok(obj);
        if(SessionManger.hasSession(uid)){
            context = ContextBuilder.buildAPIContext(context,SessionManger.getSession(uid));
        }else if (APINumber.LOGINAPI.equals(apiNumbers.get(0))){
            API<T,?> api = API_MAP.get(APINumber.LOGINAPI);
            r = api.exe(obj,null);
            i++;
            context = ContextBuilder.buildAPIContext(context,SessionManger.getSession(uid));
        }

        for (; i < apiNumbers.size(); i++) {
            log.info(r.toString());
            String number = apiNumbers.get(i);
            if(API_MAP.get(number) == null)throw new WbException("编码为：" + apiNumbers.get(i) + "的API未注册");
            if((APINumber.LOGINAPI.equals(apiNumbers.get(i))) && SessionManger.hasSession(uid)){
                context = ContextBuilder.buildAPIContext(context,SessionManger.getSession(uid));
            }else{
                API api = API_MAP.get(number);
                log.info("调用API:" + api.getNumber());
                if(isMoreToOne(r.getRe(),api)){//如果多对一
                    List<Object> reList = new ArrayList<>();
                    for (Object o : ((Collection) r.getRe())) {
                        if(o == null)continue;
                        Object tmpObj = api.exe(ParamConvertor.checkAndConvert(o,api),context).getRe();
                        if(tmpObj == null)continue;
                        reList.add(tmpObj);
                    }
                    r = R.ok(reList);
                }else{
                    r = api.exe(ParamConvertor.checkAndConvert(r.getRe(),api),context);//先转换
                }
                if((APINumber.LOGINAPI.equals(apiNumbers.get(i)))){
                    context = ContextBuilder.buildAPIContext(context,SessionManger.getSession(uid));
                }
            }

        }
        return (R<E>)r;

    }

    private static boolean isMoreToOne(Object re,API api){
        Type type = ParamConvertor.getRowType(api);
        boolean b;
        if(type instanceof Class){
            b = Collection.class.isAssignableFrom((Class<?>) type);
        }else if(type instanceof ParameterizedType){
            Class tmp = (Class)((ParameterizedType) type).getRawType();
            b = Collection.class.isAssignableFrom(tmp);
        }else{
            throw new WbException("出现未发现的Type类型：" + type.getClass());
        }
        return Collection.class.isAssignableFrom(re.getClass()) && !b;
    }



}

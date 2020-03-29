package xyz.iamray.weiboapi.api;

import lombok.extern.slf4j.Slf4j;
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
public class APIManger {

    private static final Map<String,API> API_MAP = new HashMap<>();

    //注册系统自带的api
    static{
        register( new AddFollowingToGroupAPI());
        register( new CrawlWeiBoAPI());
        register( new CreateNewFollowingGroupAPI());
        register( new DeliverBlogAPI());
        register( new FollowWeiboerAPI());
        register( new ForwardBlogAPI());
        register( new GetMobalHotCommentAPI());
        register( new GetWeiBoersFromGroupChatAPI());
        register( new LoginAPI());
        register( new SendGroupMessageAPI());
        register( new SendPrivateLetterAPI());
        register( new UploadImageAPI());
        register( new GetMobalHotCommentAPI());
        register( new CrawlWeiBoByUrlAPI());
        register( new CrawlMobalHotListAPI());
        register( new GetMobalWeiBoByUrlAPI());

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
            Class<?> apiClass = APIManger.class.getClassLoader().loadClass(classPath);
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
     * TODO 1.如果有会话了，删除掉前面的登录api
     * TODO 2.API之间传入传出类型的转换
     * @param obj 初始api的参数
     * @param apiNumbers
     * @param uid
     */
    public static <T,E> R<E> call(T obj,List<String> apiNumbers, String uid,Context context){
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
                        reList.add(api.exe(ParamConvertor.checkAndConvert(o,api),context));
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

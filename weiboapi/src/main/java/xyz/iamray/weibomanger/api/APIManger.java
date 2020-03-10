package xyz.iamray.weibomanger.api;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.weibomanger.api.impl.*;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.common.exception.WbException;
import xyz.iamray.weibomanger.session.SessionManger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        register( new GetHotCommentAPI());
        register( new GetWeiBoersFromGroupChatAPI());
        register( new LoginAPI());
        register( new SendGroupMessageAPI());
        register( new SendPrivateLetterAPI());
        register( new UploadImageAPI());

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
            Class apiClass = APIManger.class.getClassLoader().loadClass(classPath);
            API api = (API) apiClass.getConstructor().newInstance();
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
    public static <T,E> R<E> call(T obj,List<String> apiNumbers, String uid,Context context){
        if(apiNumbers.isEmpty())return null;
        int i = 0;
        R r = R.ok(obj);
        if(SessionManger.hasSession(uid)){
            context = ContextBuilder.buildAPIContext(context,SessionManger.getSession(uid));
        }else if (APINumber.LOGINAPI.name() == apiNumbers.get(0)){
            r = API_MAP.get(APINumber.LOGINAPI.name()).exe(obj,null);
            i++;
            context = ContextBuilder.buildAPIContext(context,SessionManger.getSession(uid));
        }else {
            log.warn("用户未登录！");
        }

        for (; i < apiNumbers.size(); i++) {
            r = API_MAP.get(apiNumbers.get(i)).exe(r.getRe(),context);
        }
        return (R<E>)r;

    }


}

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

    private static final Map<APINumber,API> API_MAP = new HashMap<>();

    //注册系统自带的api
    static{
        AddFollowingToGroupAPI addFollowingToGroupAPI = new AddFollowingToGroupAPI();
        CrawlWeiBoAPI crawlWeiBoAPI = new CrawlWeiBoAPI();
        CreateNewFollowingGroupAPI createNewFollowingGroupAPI = new CreateNewFollowingGroupAPI();
        DeliverBlogAPI deliverBlogAPI = new DeliverBlogAPI();
        FollowWeiboerAPI followWeiboerAPI = new FollowWeiboerAPI();
        ForwardBlogAPI forwardBlogAPI = new ForwardBlogAPI();
        GetHotCommentAPI getHotCommentAPI = new GetHotCommentAPI();
        GetWeiBoersFromGroupChatAPI getWeiBoersFromGroupChatAPI = new GetWeiBoersFromGroupChatAPI();
        LoginAPI loginAPI = new LoginAPI();
        SendGroupMessageAPI sendGroupMessageAPI = new SendGroupMessageAPI();
        SendPrivateLetterAPI sendPrivateLetterAPI = new SendPrivateLetterAPI();
        UploadImageAPI uploadImageAPI = new UploadImageAPI();

        API_MAP.put(addFollowingToGroupAPI.getNumber(), addFollowingToGroupAPI);
        API_MAP.put(crawlWeiBoAPI.getNumber(), crawlWeiBoAPI);
        API_MAP.put(createNewFollowingGroupAPI.getNumber(), createNewFollowingGroupAPI);
        API_MAP.put(deliverBlogAPI.getNumber(), deliverBlogAPI);
        API_MAP.put(followWeiboerAPI.getNumber(), followWeiboerAPI);
        API_MAP.put(forwardBlogAPI.getNumber(), forwardBlogAPI);
        API_MAP.put(getHotCommentAPI.getNumber(), getHotCommentAPI);
        API_MAP.put(getWeiBoersFromGroupChatAPI.getNumber(), getWeiBoersFromGroupChatAPI);
        API_MAP.put(loginAPI.getNumber(), loginAPI);
        API_MAP.put(sendGroupMessageAPI.getNumber(), sendGroupMessageAPI);
        API_MAP.put(sendPrivateLetterAPI.getNumber(), sendPrivateLetterAPI);
        API_MAP.put(uploadImageAPI.getNumber(), uploadImageAPI);
    }

    /**
     * 注册api
     * @param classPath api类路径
     */
    public static void register(String classPath)  {
        try {
            Class apiClass = APIManger.class.getClassLoader().loadClass(classPath);
            API api = (API) apiClass.newInstance();
            if(api.getNumber() == null || API_MAP.containsKey(api.getNumber())){
                throw new WbException("API编码为空或者已被注册");
            }
            API_MAP.put(api.getNumber(),api);
        }catch (Exception e){
            log.error(classPath + "注册失败：发生如下错误");
            log.error(e.getMessage());
        }
    }

    /**
     * api调用接口
     * 上下文环境构建
     * @param obj 初始api的参数
     * @param apiNumbers
     * @param uid
     */
    public static <T,E> R<E> call(T obj,List<APINumber> apiNumbers, String uid,Context context){
        if(apiNumbers.isEmpty())return null;
        int i = 0;
        R r = R.ok(obj);
        if(SessionManger.hasSession(uid)){
            context = ContextBuilder.buildAPIContext(context,SessionManger.getSession(uid));
        }else if (APINumber.LOGINAPI == apiNumbers.get(0)){
            r = API_MAP.get(APINumber.LOGINAPI).exe(obj,null);
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

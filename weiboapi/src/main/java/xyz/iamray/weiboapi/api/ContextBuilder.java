package xyz.iamray.weiboapi.api;

import xyz.iamray.weiboapi.api.context.APIContext;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.session.Session;

import java.util.concurrent.ExecutorService;

/**
 * @author winray
 * @since v1.0.1
 */
public class ContextBuilder {

    public static Context buildContext(){
        return new APIContext();
    }

    public static Context buildContext(ExecutorService executorService){
        return new APIContext(executorService);
    }

    static APIContext buildAPIContext(Context context, Session session){
        if(context == null)return new APIContext(session);
        if(context instanceof APIContext){
            ((APIContext)context).setSession(session);
        }else{
            throw new WbException("无法将Context转成APIContext");
        }
        return (APIContext)context;
    }
}

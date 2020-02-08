package xyz.iamray.weibomanger.session;

import xyz.iamray.weibomanger.common.exception.WbException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 会话管理员
 * 会话退出之后，将会话标记为过期，任务要协议退出，会话的任务全部退出之后将此会话移除
 */
public class SessionManger {

    private SessionManger(){}

    private static final SessionManger DEFAULT_SESSION_MANGER = new SessionManger();

    /**
     * key 是 微博用户UID
     */
    private Map<String,Session> sessions = new HashMap<>();

    public static void setSession(String uid,Session session){
        DEFAULT_SESSION_MANGER.sessions.put(uid,session);
    }

    public static boolean hasSession(String uid){
        if(DEFAULT_SESSION_MANGER.sessions.containsKey(uid)
                && !DEFAULT_SESSION_MANGER.sessions.get(uid).isExpired()){
            throw new WbException("此账号已存在会话，请先退出之前的会话。");
        }else{
            return false;
        }
    }

}

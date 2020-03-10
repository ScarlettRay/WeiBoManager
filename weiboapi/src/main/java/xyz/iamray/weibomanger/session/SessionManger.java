package xyz.iamray.weibomanger.session;

import org.apache.http.impl.client.CloseableHttpClient;
import xyz.iamray.weibomanger.pojo.WeiBoer;

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

    public static synchronized void createAndStoreSession(WeiBoer weiBoer, CloseableHttpClient httpClient){
        Session session = new Session(weiBoer);
        session.setWeiboHttpClient(httpClient);
        DEFAULT_SESSION_MANGER.sessions.put(weiBoer.getUid(),session);
    }

    public static boolean hasSession(String uid){
        if(DEFAULT_SESSION_MANGER.sessions.containsKey(uid)
                && !DEFAULT_SESSION_MANGER.sessions.get(uid).isExpired()){
            return true;
        }else{
            return false;
        }
    }

    public static Session getSession(String uid){
        return DEFAULT_SESSION_MANGER.sessions.get(uid);
    }

}

package xyz.iamray.weibomanger.api;

import org.apache.http.impl.client.CloseableHttpClient;
import xyz.iamray.weibomanger.session.Session;

/**
 * @author winray
 * @since v1.0.1
 * 调用过程中的上下文环境
 * 配置会话信息和调用参数
 */
public class Context {

    private Session session;

    private Context(Session session){
        this.session = session;
    }

    public String getUid(){
        return session.getWeiBoer().getUid();
    }

    public CloseableHttpClient getHttpClient(){
        return session.getWeiboHttpClient();
    }
}

package xyz.iamray.weiboapi.api.context;

import org.apache.http.impl.client.CloseableHttpClient;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.session.Session;

import java.util.concurrent.ExecutorService;

/**
 * @author winray
 * @since v1.0.1
 * API运行时上下文
 */
public class APIContext extends AbstractContext {

    private Session session;

    public APIContext(){}

    public APIContext(ExecutorService executorService){
        super.executorService = executorService;
    };

    public APIContext(Session session){
        this.session = session;
    }

    public String getUid(){
        return session.getWeiBoer().getUid();
    }

    public CloseableHttpClient getHttpClient(){
        return session.getWeiboHttpClient();
    }

    public WeiBoer getWeiBoer(){
        return session.getWeiBoer();
    }

    public void setSession(Session session){
        this.session = session;
    }
}

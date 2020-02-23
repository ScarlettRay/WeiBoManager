package xyz.iamray.weibomanger.api;

import org.apache.http.impl.client.CloseableHttpClient;
import xyz.iamray.weibomanger.pojo.WeiBoer;
import xyz.iamray.weibomanger.session.Session;

import java.util.concurrent.ExecutorService;

/**
 * @author winray
 * @since v1.0.1
 */
public class APIContext extends Context{

    private Session session;

    APIContext(){}

    APIContext(ExecutorService executorService){
        super.executorService = executorService;
    };

    APIContext(Session session){
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

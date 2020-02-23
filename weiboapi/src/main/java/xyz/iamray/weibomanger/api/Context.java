package xyz.iamray.weibomanger.api;

import org.apache.http.impl.client.CloseableHttpClient;
import xyz.iamray.weibomanger.common.exception.WbException;
import xyz.iamray.weibomanger.pojo.WeiBoer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author winray
 * @since v1.0.1
 * 调用过程中的上下文环境
 * 配置会话信息和调用参数
 */
public abstract class Context {


    protected ExecutorService executorService;

    private Map<String,Object> userProperties = new HashMap<>();

    Context(){}

    public ExecutorService getExecutorService(){
        if(executorService == null){
            throw new WbException("Context无可用的线程池，请在初始化Context时，传入线程池");
        }
        return this.executorService;
    }

    public void setProperty(String key,Object object){
        userProperties.put(key,object);
    }

    public <T> T getProperty(String key,Class<T> tClass){
        return (T)userProperties.get(key);
    }

    public abstract String getUid();

    public abstract CloseableHttpClient getHttpClient();

    public abstract WeiBoer getWeiBoer();
}

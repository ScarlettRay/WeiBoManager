package xyz.iamray.weiboapi.api.context;

import org.apache.http.impl.client.CloseableHttpClient;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.concurrent.ExecutorService;

/**
 * @author winray
 * @since v1.0.1
 * 运行时环境上下文接口，
 * 一个上下文应该提供环境参数、线程池（提供给爬虫使用）、用户UID、用户会话HttpClient、微博用户对象
 *
 */
public interface Context {

    void setProperty(String key,Object object);

    <T> T getProperty(String key,Class<T> tClass);

    <T> T getProperty(String key,Class<T> tClass,T defaultObj);

    ExecutorService getExecutorService();

    String getUid();

    CloseableHttpClient getHttpClient();

    WeiBoer getWeiBoer();

}

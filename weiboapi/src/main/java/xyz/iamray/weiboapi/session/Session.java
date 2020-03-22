package xyz.iamray.weiboapi.session;

import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 用来表示机器人管理一个微博账户的整个会话过程
 * 可以隔离机器人管理的所有微博账号
 * 各个账号相互之间隔离，不影响
 * 此对象用来储存会话过程产生的所有数据
 */
@Data
public class Session {

    Session(WeiBoer weiBoer){
        this.weiBoer = weiBoer;
    }

    private CloseableHttpClient weiboHttpClient;

    private WeiBoer weiBoer;

    private boolean isExpired = true;//是否过期

    private List tasks;

    /**
     * TODO
     * 退出会话
     * @param force 为true时，强制退出，将tasks里面的任务退出，将SessionManger里面的session删除
     */
    public void exit(boolean force){

    }

}

package xyz.iamray.weiboapi.login;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;

/**
 * @author liuwenrui
 * @date 2018/5/26
 */
@Data
public class WeiBoLoginMes {

    private String account;
    private String su;
    private boolean needValid = false;
    private InputStream validCode;
    private String codeURL;
    private JSONObject preLoginJsonObject;
    private CloseableHttpClient weiboHttpClient;
    private CookieStore cookieStore;
    private String uid;
    private String nickName;
    private String profileUrl;

    private String failReason;

    //微博每天都有私信人数限制，此字段为一个field
    private boolean continueSendPrivateMes = true;

    public WeiBoLoginMes(String username,CookieStore cookieStore, CloseableHttpClient weiboHttpClient) {
        this.account = username;
        this.weiboHttpClient = weiboHttpClient;
        this.cookieStore = cookieStore;
    }

}

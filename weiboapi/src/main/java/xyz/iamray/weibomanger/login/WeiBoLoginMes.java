package xyz.iamray.weibomanger.login;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;

/**
 * @author liuwenrui
 * @date 2018/5/26
 */
public class WeiBoLoginMes {

    private String username;
    private String su;
    private boolean needValid = false;
    private InputStream validCode;
    private String codeURL;
    private JSONObject preLoginJsonObject;
    private CloseableHttpClient weiboHttpClient;
    private CookieStore cookieStore;
    private String uid;

    private String failReason;

    //微博每天都有私信人数限制，此字段为一个field
    private boolean continueSendPrivateMes = true;

    public WeiBoLoginMes(String username,CookieStore cookieStore, CloseableHttpClient weiboHttpClient) {
        this.username = username;
        this.weiboHttpClient = weiboHttpClient;
        this.cookieStore = cookieStore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isNeedValid() {
        return needValid;
    }

    public void setNeedValid(boolean needValid) {
        this.needValid = needValid;
    }

    public InputStream getValidCode() {
        return validCode;
    }

    public void setValidCode(InputStream validCode) {
        this.validCode = validCode;
    }

    public CloseableHttpClient getWeiboHttpClient() {
        return weiboHttpClient;
    }

    public void setWeiboHttpClient(CloseableHttpClient weiboHttpClient) {
        this.weiboHttpClient = weiboHttpClient;
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isContinueSendPrivateMes() {
        return continueSendPrivateMes;
    }

    public void setContinueSendPrivateMes(boolean continueSendPrivateMes) {
        this.continueSendPrivateMes = continueSendPrivateMes;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public String getCodeURL() {
        return codeURL;
    }

    public void setCodeURL(String codeURL) {
        this.codeURL = codeURL;
    }

    public JSONObject getPreLoginJsonObject() {
        return preLoginJsonObject;
    }

    public void setPreLoginJsonObject(JSONObject preLoginJsonObject) {
        this.preLoginJsonObject = preLoginJsonObject;
    }
}

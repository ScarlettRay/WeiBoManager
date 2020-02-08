package xyz.iamray.weibomanger.login;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;

import java.io.UnsupportedEncodingException;

/**
 * @author liuwenrui
 * @date 2018/5/26
 * 登录接口
 */
public class LoginAssistant {

    /**
     * 登录接口
     * @param username
     * @param password
     */
    public static WeiBoLoginMes login(String username, String password){
        WeiBoLoginService weiBoLoginService =  new WeiBoLoginService();
        CookieStore cookieStore = new BasicCookieStore();
        WeiBoLoginMes weiBoLoginMes = new WeiBoLoginMes(
                username,
                cookieStore,
                HttpClients.custom().setDefaultCookieStore(cookieStore).build()
        );
        try {
            weiBoLoginService.preLogin(username,password,weiBoLoginMes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return weiBoLoginMes;
    }

}

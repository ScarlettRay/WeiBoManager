package xyz.iamray.flow;

import org.apache.commons.lang.StringUtils;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import xyz.iamray.link.http.HttpClientPool;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.session.SessionManger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winray
 * @since v1.0.1
 */
public class LoginUtil {
    public static final Pattern REGEX = Pattern.compile("(?<=\\[).*?(?=\\])");


    public static void createSession(){
        BasicCookieStore store = buildCookies();
        CloseableHttpClient httpClient = HttpClientPool.getHttpClient(store);
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid(TestConstant.UID);


        SessionManger.createAndStoreSession(weiBoer,httpClient);
    }

    public static void createSession2(){
        BasicCookieStore store = buildCookies2();
        CloseableHttpClient httpClient = HttpClientPool.getHttpClient(store);
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid(TestConstant.UID);


        SessionManger.createAndStoreSession(weiBoer,httpClient);
    }



    private static BasicCookieStore buildCookies2(){
        String cookieStr = "SINAGLOBAL=84752659228.94603.1517993264194; login_sid_t=4ce2377e611a37f217ed1c3a6b86480c; cross_origin_proto=SSL; _s_tentry=-; Apache=720490706658.8983.1580976446136; ULV=1580976446146:31:2:2:720490706658.8983.1580976446136:1580808985541; SSOLoginState=1584179742; UOR=,,news.mtrend.cn; un=13710292611; ALF=1617513951; SCF=AmrCcwLRiRP9VntqX95fSDa81Xohv5kpDgPjP4xRwS5coY_WDHAfPIAWKVHMoEN-FSJVNYzvejQHL33IPMLIEJU.; SUB=_2A25zjGowDeRhGeFN7VYT9S7KyDuIHXVQ-Nz4rDV8PUNbmtAKLU77kW9NQ-Osq4e5YiOlyWfttS6jWA9RbJoxbrtC; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWO5PxwIKsCeM8kqzS-4WLZ5JpX5KzhUgL.FoM0SoBESK5ce0M2dJLoIEXLxK.LBKeL12-LxKnLB.BLBKqLxKBLBonL12-LxKqLBK2L1KBLxK-LB-BL1K5t; SUHB=0XDVfUHmhlaAy9; webim_unReadCount=%7B%22time%22%3A1586060761815%2C%22dm_pub_total%22%3A1%2C%22chat_group_client%22%3A0%2C%22chat_group_notice%22%3A0%2C%22allcountNum%22%3A0%2C%22msgbox%22%3A-1%7D";
        String[] cookiesArray = cookieStr.split(";");
        BasicCookieStore cookieStore = new BasicCookieStore();
        for (String s : cookiesArray) {
            String[] cookieArr = s.split("=");
            BasicClientCookie cookie = new BasicClientCookie(cookieArr[0],cookieArr[1]);
            cookie.setPath("/");
            cookie.setDomain(".weibo.com");
            cookie.setAttribute(ClientCookie.DOMAIN_ATTR,".weibo.com");
            cookieStore.addCookie(cookie);
        }
        return cookieStore;
    }

    private static BasicCookieStore buildCookies(){
        String str = "[version: 0][name: ALC][value: ac%3D0%26bt%3D1586147866%26cv%3D5.0%26et%3D1617683866%26ic%3D1876815428%26login_time%3D1586147866%26scf%3D%26uid%3D7364250637%26vf%3D0%26vs%3D0%26vt%3D0%26es%3D0a8028524c68056c69a7549cd728d2d8][domain: login.sina.com.cn][path: /][expiry: Tue Apr 06 12:37:46 CST 2021], [version: 0][name: ALF][value: 1617683866][domain: sina.com.cn][path: /][expiry: Tue Apr 06 12:37:46 CST 2021], [version: 0][name: ALF][value: 1617683866][domain: weibo.com][path: /][expiry: Tue Apr 06 12:37:46 CST 2021], [version: 0][name: LT][value: 1586147866][domain: login.sina.com.cn][path: /][expiry: null], [version: 0][name: SCF][value: AgOZnUD38GP9Duj50Xa9bpiq_kMPAC6wSoL18gFNk68STm1gklpmiwyg5C0Cs10IUN__A0c85l15X5HtZ9eZRLU.][domain: sina.com.cn][path: /][expiry: Thu Apr 04 12:37:46 CST 2030], [version: 0][name: SCF][value: AgOZnUD38GP9Duj50Xa9bpiq_kMPAC6wSoL18gFNk68StqpFxKS631rcEn8WZb1RmQxneeZdXOBu-NOtwHZgWTQ.][domain: weibo.com][path: /][expiry: Thu Apr 04 12:37:47 CST 2030], [version: 0][name: SUB][value: _2A25zjsJKDeRhGeFN7VYT9S7KyDuIHXVQ_bSCrDV_PUNbm9ANLW_QkW9NQ-OsqzWqEPUslcOb2yWFOWzMx26NIq2k][domain: sina.com.cn][path: /][expiry: null], [version: 0][name: SUB][value: _2A25zjsJLDeRhGeFN7VYT9S7KyDuIHXVQ_bSDrDV8PUNbktAKLWHykW9NQ-Osq3DYYcMWWhcF0UtXf9dLqtcr8nvU][domain: weibo.com][path: /][expiry: null], [version: 0][name: SUBP][value: 0033WrSXqPxfM725Ws9jqgMF55529P9D9WWO5PxwIKsCeM8kqzS-4WLZ5NHD95QNe0qXeo-7SoeNWs4Dqcj_i--4i-20iKy8i--Ri-i2i-2ci--Xi-zRiKy8i--ci-2piK.Xi--fi-82iK.7][domain: sina.com.cn][path: /][expiry: null], [version: 0][name: SUBP][value: 0033WrSXqPxfM725Ws9jqgMF55529P9D9WWO5PxwIKsCeM8kqzS-4WLZ5JpX5K2hUgL.FoM0SoBESK5ce0M2dJLoIEXLxK.LBKeL12-LxKnLB.BLBKqLxKBLBonL12-LxKqLBK2L1KBLxK-LB-BL1K5t][domain: weibo.com][path: /][expiry: Tue Apr 06 12:37:47 CST 2021], [version: 0][name: SUHB][value: 0YhhZI-pgpWWz9][domain: weibo.com][path: /][expiry: Tue Apr 06 12:37:47 CST 2021], [version: 0][name: Ugrow-G0][value: cf25a00b541269674d0feadd72dce35f][domain: weibo.com][path: /][expiry: null], [version: 0][name: route][value: 1312c9c574002583683776fe4e1c1b2a][domain: login.sina.com.cn][path: /][expiry: null], [version: 0][name: sso_info][value: v02m6alo5qztKWRk6SlkJOMpY6ElKWRk6ClkKSQpZCTmKWRk5ClkKOgpY6ElKWRk5ilkJOEpY6TkKWRk5SlkJSQpY6TgKadlqWkj5Ocs42jkLKNk4C2jLOcwA==][domain: sina.com.cn][path: /][expiry: Tue Apr 06 12:37:46 CST 2021], [version: 0][name: tgc][value: TGT-NzM2NDI1MDYzNw==-1586147866-yf-0E3F99827E684AF5A0EBE492EE266332-1][domain: login.sina.com.cn][path: /][expiry: null]";
        Matcher ma = REGEX.matcher(str);
        BasicCookieStore cookieStore = new BasicCookieStore();
        List<String> stringList = new ArrayList<>();
        while (ma.find()){
            stringList.add(trimText(ma.group()));
        }
        for (int i = 0; i < stringList.size();i = i+6) {
            BasicClientCookie cookie = new BasicClientCookie(stringList.get(i+1),stringList.get(i+2));
            cookie.setVersion(Integer.parseInt(stringList.get(i)));
            cookie.setDomain(stringList.get(i+3));
            cookie.setAttribute(ClientCookie.DOMAIN_ATTR,".weibo.com");
            cookie.setPath(stringList.get(i+4));
            if(!"null".equals(stringList.get(i+5))){
                cookie.setExpiryDate(new Date(stringList.get(i+5)));
            }
            cookieStore.addCookie(cookie);

        }
        return cookieStore;
    }

    private static String trimText(String text){
        String[] strs = text.split(":");
        if(strs.length > 2){

            return StringUtils.join(strs,":").substring(7).trim();
        }else{
            return strs[1].trim();
        }
    }

}

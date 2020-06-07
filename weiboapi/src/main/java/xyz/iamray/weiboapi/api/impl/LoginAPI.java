package xyz.iamray.weiboapi.api.impl;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.login.LoginAssistant;
import xyz.iamray.weiboapi.login.WeiBoLoginMes;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.session.SessionManger;

/**
 * @author winray
 * @since v1.0.1
 * 登录接口，将登录信息放在session里边，把session交给SessionManger
 *
 */
@Slf4j
public class LoginAPI implements API<WeiBoer, WeiBoer>{

    public final static LoginAPI INSTANCE = new LoginAPI();

    @Override
    public String getNumber() {
        return APINumber.LOGINAPI;
    }

    @Override
    public R<WeiBoer> exe(WeiBoer user, Context context){
        WeiBoLoginMes mes = LoginAssistant.login(user.getAccount(),user.getPassword());
        user.setLoginMessage(mes);
        if(!SessionManger.hasSession(mes.getUid())){
            SessionManger.createAndStoreSession(user,mes.getWeiboHttpClient());
        }
        return R.ok(user);
    }
}

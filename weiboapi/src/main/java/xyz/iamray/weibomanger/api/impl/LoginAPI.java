package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.login.LoginAssistant;
import xyz.iamray.weibomanger.login.WeiBoLoginMes;
import xyz.iamray.weibomanger.pojo.WeiBoer;
import xyz.iamray.weibomanger.session.SessionManger;

/**
 * @author winray
 * @since v1.0.1
 * 登录接口，将登录信息放在session里边，把session交给SessionManger
 *
 */
public class LoginAPI implements API<WeiBoer, WeiBoer>{

    @Override
    public R<WeiBoer> exe(WeiBoer user, Context context){
        WeiBoLoginMes mes = LoginAssistant.login(user.getAccount(),user.getPassword());
        user.setLoginMessage(mes);
        if(!SessionManger.hasSession(mes.getUid())){
            SessionManger.createAndStoreSession(user);
        }
        return new R<>(user);
    }
}

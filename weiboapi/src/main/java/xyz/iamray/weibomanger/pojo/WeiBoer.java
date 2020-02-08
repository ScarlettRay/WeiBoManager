package xyz.iamray.weibomanger.pojo;

import lombok.Data;
import xyz.iamray.weibomanger.login.WeiBoLoginMes;

/**
 * @author winray
 * @since v1.0.1
 * 微博用户
 */
@Data
public class WeiBoer {

    private String account;

    private String password;

    private boolean needVerify;

    private String captchaUrl;

    private String uid;

    /**
     * 设置微博登录信息
     * @param mes
     */
    public void setLoginMessage(WeiBoLoginMes mes){
        this.setNeedVerify(mes.isNeedValid());
        this.setCaptchaUrl(mes.getCodeURL());
        this.setUid(mes.getUid());
    }
}

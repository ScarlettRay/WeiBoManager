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

    private String captchaUrl;//验证码链接

    private String uid;

    private String gid;//分组

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

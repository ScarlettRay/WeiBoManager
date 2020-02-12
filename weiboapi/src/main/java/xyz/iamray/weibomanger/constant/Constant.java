package xyz.iamray.weibomanger.constant;

import xyz.iamray.core.HttpConstant;
import xyz.iamray.core.SpiderConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 公共常量
 */
public class Constant {

    public static final Map<String,String> COMMON_HEADER = new HashMap<>();

    static {
        COMMON_HEADER.put(HttpConstant.Header.USER_AGENT, SpiderConstant.ChromeUserAgent);
        COMMON_HEADER.put(HttpConstant.Header.REFERER, AutoWeiBoSpiderConstant.MyWeiBO_ProFile);
    }
}

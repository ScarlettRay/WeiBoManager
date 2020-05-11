package xyz.iamray.weiboapi.common.constant;

import xyz.iamray.core.HttpConstant;
import xyz.iamray.core.SpiderConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 公共常量
 */
public class Constant {

    public static final Map<String,String> COMMON_HEADER = new HashMap<>();

    public static final List<String> COMMENT_LIST = new ArrayList<>();

    static {
        COMMON_HEADER.put(HttpConstant.Header.USER_AGENT, SpiderConstant.ChromeUserAgent);
        COMMON_HEADER.put(HttpConstant.Header.REFERER, AutoWeiBoSpiderConstant.MyWeiBO_ProFile);

        COMMENT_LIST.add("支持博主[鲜花]");
        COMMENT_LIST.add("常来往哟！[抱抱]");
        COMMENT_LIST.add("亲爱的，我来看你了[害羞]");
        COMMENT_LIST.add("我是您忠实的粉丝！[害羞]");
        COMMENT_LIST.add("博主，早安[鲜花]");
        COMMENT_LIST.add("来喽，不要忘了来回o[抱抱]");
    }
}

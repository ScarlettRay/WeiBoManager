package xyz.iamray.weiboapi.utils;

import java.util.Collection;
import java.util.List;

/**
 * @author liuwenrui
 * @since 2018/11/24
 */
public class WeiBoUtil {


    /**
     * 字符串拼接技术
     * @param strs
     * @return
     */
    public static String strjoin(List strs, String substr){
        StringBuilder sb = new StringBuilder();
        strs.forEach(e->sb.append(e+substr));
        return sb.toString().substring(0,
                sb.lastIndexOf(substr)>0?sb.lastIndexOf(substr):0);
    }



    public static boolean isNull(Collection collection){
        return collection == null || collection.isEmpty();
    }

}

package xyz.iamray.weiboapi.utils;

import xyz.iamray.exception.ExceptionWrapper;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.Status;

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

    public static boolean isNotNull(Collection collection){
        return collection != null && !collection.isEmpty();
    }

    public static <I,O> R<O> dealResult(Result<I> result){
        R r = new R();
        if(result.getCrawlMes().getExceptions().isEmpty()){
            r.setStatus(Status.OK);
        }else{
            r.setStatus(Status.ERR);
            StringBuilder sb = new StringBuilder();
            for (ExceptionWrapper exception : result.getCrawlMes().getExceptions()) {
                sb.append(exception.toString()).append("\n");
            }
            r.setDescription(sb.toString());
        }
        if(result.getObj() != null){
            r.setRe(result.getObj());
        }else if(result.getObjList() != null){
            r.setRe(result.getObjList());
        }
        return r;
    }

}

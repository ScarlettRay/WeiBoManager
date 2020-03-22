package xyz.iamray.weiboapi.api;

import xyz.iamray.weiboapi.common.R;

/**
 * @author winray
 * @since v1.0.1
 */
public class APIWrapper<I,O> {

    private I i;

    private O o;

    private API<I,O> api;

    private APIWrapper(API<I,O> api){
        this.api = api;
    }

    public static <T,E> APIWrapper<T,E> build(API<T,E> api){
        return new APIWrapper<T,E>(api);
    }

    public R<O> exe(I i, Context context){
        return api.exe(i,context);
    }

    /**
     * 检查传入传出是否符合api的要求
     */
    public void check(){

    }
}

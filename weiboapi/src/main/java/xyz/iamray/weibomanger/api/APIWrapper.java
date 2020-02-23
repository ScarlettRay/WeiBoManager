package xyz.iamray.weibomanger.api;

import xyz.iamray.weibomanger.common.R;

/**
 * @author winray
 * @since v1.0.1
 */
public class APIWrapper<T,E> {

    private T t;

    private E e;

    private API<T,E> api;

    private APIWrapper(API<T,E> api){
        this.api = api;
    }

    public static <T,E> APIWrapper<T,E> build(API<T,E> api){
        return new APIWrapper<T,E>(api);
    }

    public R<T> exe(E t, Context context){
        return api.exe(t,context);
    }

    /**
     * 检查传入传出是否符合api的要求
     */
    public void check(){

    }
}

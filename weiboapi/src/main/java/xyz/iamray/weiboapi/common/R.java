package xyz.iamray.weiboapi.common;

import lombok.Data;

/**
 * @author winray
 * @since v1.0.1
 * 封装结果
 */
@Data
public class R<T> {

    private T re;//结果对象

    private Status status;//状态编码

    private String description;//描述

    public R(T obj,Status status){
        this.re = obj;
        this.status = status;
    }


    public static <T> R<T> ok(T obj){
        return new R(obj,Status.OK);
    }

    public static <T> R<T> err(T obj){
        return new R(obj,Status.ERR);
    }

    public T getRe(){
        return re;
    }

}

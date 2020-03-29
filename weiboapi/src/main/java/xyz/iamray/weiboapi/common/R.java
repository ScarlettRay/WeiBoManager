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

    public R(){}

    public R(T obj,Status status,String description){
        this.re = obj;
        this.status = status;
        this.description = description;
    }


    public static <T> R<T> ok(T obj){
        return new R(obj,Status.OK,null);
    }

    public static R setDesc(String description){
        return new R(null,Status.OK,description);
    }

    public static R no(){
        return new R(null,Status.NO,Status.NO.description);
    }

    public static <T> R<T> err(T obj){
        return err(obj,null);
    }

    public static <T> R<T> err(T obj,String description){
        return new R<T>(obj,Status.ERR,description);
    }


    public T getRe(){
        return re;
    }

}

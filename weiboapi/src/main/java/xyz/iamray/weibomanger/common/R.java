package xyz.iamray.weibomanger.common;

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

    public R(T obj){
        this.re = obj;
    }

    public T getRe(){
        return re;
    }

}

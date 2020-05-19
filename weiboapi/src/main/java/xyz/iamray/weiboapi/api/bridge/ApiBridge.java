package xyz.iamray.weiboapi.api.bridge;

import xyz.iamray.weiboapi.api.API;

/**
 * @author winray
 * @since v1.0.1
 * 适配两个API的桥API
 * 主要用于对上一个API的输出做处理，来适配下一API的输入
 */
public interface ApiBridge<I,O> extends API<I,O> {

}

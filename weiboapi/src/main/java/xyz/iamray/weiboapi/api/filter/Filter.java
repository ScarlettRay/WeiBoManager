package xyz.iamray.weiboapi.api.filter;

import xyz.iamray.weiboapi.api.API;

/**
 * @author winray
 * @since v1.0.1
 * 判断输入对像是否需要被过滤掉
 */
public interface Filter<I> extends API<I,I> {

    boolean contains(I i);
}

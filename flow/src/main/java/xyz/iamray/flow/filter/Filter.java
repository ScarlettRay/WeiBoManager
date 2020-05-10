package xyz.iamray.flow.filter;

/**
 * @author winray
 * @since v1.0.1
 * 判断输入对像是否需要被过滤掉
 */
public interface Filter<I> {

    boolean contains(I i);
}

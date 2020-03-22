package xyz.iamray.weiboapi.handle;

import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;


/**
 * @author Ray
 * @create 2020-03-05 13:50:04
 * <p>API的后置任务，一般由用户自定义
 * 每一个API只能配置一个BackHandle
 */
@Deprecated()
public interface BackHandle<I,O> {

    /**
     * TODO 这个handle的使用场景暂时可以用API代替
     * @param result API执行完之后的输出
     * @return 处理完之后返回结果
     */
    public R<O> handleResult(R<I> result, Context context);
}

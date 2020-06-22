package xyz.iamray.weiboapi.api;

import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;

/**
 * @author winray
 * @since v1.0.1
 * 微博管理机器人的接口调用标准定义
 * I:输出
 * O:输入
 */
public interface API<I,O> {

    String getNumber();

    R<O> exe(I param, Context context);

}

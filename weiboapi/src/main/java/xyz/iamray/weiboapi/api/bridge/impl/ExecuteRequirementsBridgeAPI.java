package xyz.iamray.weiboapi.api.bridge.impl;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.PrizeBlog;

/**
 * @author winray
 * @since v1.0.1
 * 执行抽奖要求
 */
public class ExecuteRequirementsBridgeAPI implements ApiBridge<PrizeBlog,PrizeBlog> {

    public static final ExecuteRequirementsBridgeAPI INSTANCE = new ExecuteRequirementsBridgeAPI();

    public static final String NICK_NAME = "ExecuteRequirementsAPI-NickName";//你要@的用户名称
    @Override
    public String getNumber() {
        return "ExecuteRequirementsAPI";
    }

    @Override
    public R<PrizeBlog> exe(PrizeBlog param, Context context) {
        return null;
    }
}

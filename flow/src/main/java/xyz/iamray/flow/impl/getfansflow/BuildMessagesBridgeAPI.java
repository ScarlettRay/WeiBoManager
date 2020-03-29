package xyz.iamray.flow.impl.getfansflow;

import xyz.iamray.weiboapi.api.ApiBridge;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.Message;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class BuildMessagesBridgeAPI implements ApiBridge<Object, List<Message>> {
    @Override
    public String getNumber() {
        return "BuildMessagesBridgeAPI";
    }

    @Override
    public R<List<Message>> exe(Object param, Context context) {
        return null;
    }
}

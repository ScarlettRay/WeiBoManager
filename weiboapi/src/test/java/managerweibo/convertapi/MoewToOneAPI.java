package managerweibo.convertapi;

import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 15:27:42
 * <p>
 */
public class MoewToOneAPI implements API<List<String>,String> {
    @Override
    public String getNumber() {
        return "MoewToOneAPI";
    }

    @Override
    public R<String> exe(List<String> param, Context context) {
        if(!param.isEmpty()){
            String tmp = param.get(1);
            String url = "https://m.weibo.cn/api/container/getIndex?" + tmp.substring(tmp.indexOf("?"));
            return R.ok(url);
        }
        return R.ok("");
    }

}

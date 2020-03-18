package managerweibo;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;

import java.util.List;

/**
 * @author Ray
 * @create 2020-03-18 17:04:30
 * <p>
 */
public class BlogsToBlogBridgeAPI implements API<List<Blog>,Blog> {
    @Override
    public String getNumber() {
        return "BlogsToBlogBridgeAPI";
    }

    @Override
    public R<Blog> exe(List<Blog> param, Context context) {
        if(!param.isEmpty()){
            context.setProperty("forward_mid",param.get(0).getMid());
            return R.ok(param.get(0));
        }
        return null;
    }

}

package xyz.iamray.flow.impl.praisecomment;

import xyz.iamray.weiboapi.api.bridge.ApiBridge;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class BuildWeiBoerBridegAPI implements ApiBridge<Object,List<WeiBoer>> {

    public static final BuildWeiBoerBridegAPI INSTANCE = new BuildWeiBoerBridegAPI();

    public static final String WEIBOERS = "BuildWeiBoerBridegAPI-WeiBoers";

    @Override
    public String getNumber() {
        return "BuildWeiBoerBridegAPI";
    }

    @Override
    public R<List<WeiBoer>> exe(Object param, Context context) {
        List<String> weiboerIds = context.getProperty(WEIBOERS,List.class);
        List<WeiBoer> weiBoers = new ArrayList<>();
        weiboerIds.forEach(e->{
            WeiBoer weiBoer = new WeiBoer();
            weiBoer.setUid(e);
            weiBoers.add(weiBoer);
        });
        return R.ok(weiBoers);
    }
}

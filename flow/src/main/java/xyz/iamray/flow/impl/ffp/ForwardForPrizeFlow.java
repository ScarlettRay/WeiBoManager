package xyz.iamray.flow.impl.ffp;

import xyz.iamray.flow.AbstractFlow;
import xyz.iamray.flow.impl.praisecomment.BuildWeiBoerBridegAPI;
import xyz.iamray.weiboapi.api.impl.CrawlWeiBoAPI;
import xyz.iamray.weiboapi.api.impl.LoginAPI;

/**
 * @author winray
 * @since v1.0.1
 * 转发抽奖微博
 */
public class ForwardForPrizeFlow extends AbstractFlow {

    {
        apis.add(LoginAPI.INSTANCE.getNumber());
        apis.add(BuildWeiBoerBridegAPI.INSTANCE.getNumber());
        apis.add(CrawlWeiBoAPI.INSTANCE.getNumber());
    }
}

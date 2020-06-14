package xyz.iamray.flow.impl.ffp;

import xyz.iamray.flow.AbstractFlow;
import xyz.iamray.flow.impl.praisecomment.BuildWeiBoerBridegAPI;
import xyz.iamray.weiboapi.api.bridge.impl.ExecuteRequirementsBridgeAPI;
import xyz.iamray.weiboapi.api.bridge.impl.ListsToListBridgeAPI;
import xyz.iamray.weiboapi.api.filter.SelectForwardBlogFilter;
import xyz.iamray.weiboapi.api.impl.CrawlWeiBoInUserPageAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetMobalHotCommentAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetMobalWeiBoDetailByMidAPI;

/**
 * @author winray
 * @since v1.0.1
 * 转发抽奖微博
 */
public class ForwardForPrizeFlow extends AbstractFlow {

    {
        //apis.add(LoginAPI.INSTANCE.getNumber());
        apis.add(BuildWeiBoerBridegAPI.INSTANCE.getNumber());
        apis.add(CrawlWeiBoInUserPageAPI.INSTANCE.getNumber());
        apis.add(ListsToListBridgeAPI.INSTANCE.getNumber());
        apis.add(SelectForwardBlogFilter.INSTANCE.getNumber());
        apis.add(GetOmidsBridgeAPI.INSTANCE.getNumber());
        apis.add(GetMobalWeiBoDetailByMidAPI.INSTANCE.getNumber());
        apis.add(GetMobalHotCommentAPI.INSTANCE.getNumber());
        apis.add(PrizeBlogFilter.INSTANCE.getNumber());
        apis.add(GetPrizeRequirementsAPI.INSTANCE.getNumber());
        apis.add(ExecuteRequirementsBridgeAPI.INSTANCE.getNumber());
    }
}

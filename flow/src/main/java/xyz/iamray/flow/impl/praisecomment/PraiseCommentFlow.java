package xyz.iamray.flow.impl.praisecomment;

import xyz.iamray.flow.AbstractFlow;

import xyz.iamray.weiboapi.api.bridge.impl.ListToOneBridgeAPI;
import xyz.iamray.weiboapi.api.bridge.impl.ListsToListBridgeAPI;
import xyz.iamray.weiboapi.api.filter.Filter;
import xyz.iamray.weiboapi.api.impl.CrawlWeiBoAPI;
import xyz.iamray.weiboapi.api.impl.DeliverCommentAPI;
import xyz.iamray.weiboapi.api.impl.LoginAPI;
import xyz.iamray.weiboapi.api.impl.PraiseWeiBoAPI;

/**
 * @author winray
 * @since v1.0.1
 *
 * 评论点赞流程
 */
public class PraiseCommentFlow extends AbstractFlow{

    public PraiseCommentFlow(Filter blogFilter){
        apis.add(LoginAPI.INSTANCE.getNumber());

        apis.add(BuildWeiBoerBridegAPI.INSTANCE.getNumber());
        apis.add(CrawlWeiBoAPI.INSTANCE.getNumber());
        apis.add(ListsToListBridgeAPI.INSTANCE.getNumber());
        apis.add(blogFilter.getNumber());//过滤已经评论和点赞过的的blog
        apis.add(ListToOneBridgeAPI.INSTANCE.getNumber());
        apis.add(PraiseWeiBoAPI.INSTANCE.getNumber());
        apis.add(AddCommentToBlogBridgeAPI.INSTANCE.getNumber());
        apis.add(DeliverCommentAPI.INSTANCE.getNumber());
    }

}

package xyz.iamray.flow.impl.forwardhotlist;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.flow.AbstractFlow;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.bridge.impl.ConvertHotWeiBoUrlBridgeAPI;
import xyz.iamray.weiboapi.api.bridge.impl.ListToOneBridgeAPI;
import xyz.iamray.weiboapi.api.bridge.impl.MobalHotListUrlBridgeAPI;
import xyz.iamray.weiboapi.api.impl.ForwardBlogAPI;
import xyz.iamray.weiboapi.api.impl.mobile.CrawlMobalHotListAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetMobalHotCommentAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetMobalWeiBoByUrlAPI;

/**
 * @author winray
 * @since v1.0.1
 * 转发热榜微博
 * ForwardBlogAPI-HasForwardKey(ArrayList):已转发的微博mid
 */
@Slf4j
public class ForwardHotListFlow extends AbstractFlow {

    {
        apis.add(APINumber.LOGINAPI);
        apis.add(MobalHotListUrlBridgeAPI.INSTANCE.getNumber());
        apis.add(CrawlMobalHotListAPI.INSTANCE.getNumber());
        apis.add(ConvertHotWeiBoUrlBridgeAPI.INSTANCE.getNumber());
        apis.add(ListToOneBridgeAPI.INSTANCE.getNumber());
        apis.add(GetMobalWeiBoByUrlAPI.INSTANCE.getNumber());
        apis.add(ListToOneBridgeAPI.INSTANCE.getNumber());
        apis.add(GetMobalHotCommentAPI.INSTANCE.getNumber());
        apis.add(CommentToBlogBridgeAPI.INSTANCE.getNumber());
        apis.add(ForwardBlogAPI.INSTANCE.getNumber());

        requiredMap.put(INIT_PARAM,"请传入第一个API执行需要的参数");
        requiredMap.put(INIT_UID,"请输入微博用户的uid");
    }


}

package xyz.iamray.flow.impl.forwardhotlist;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.flow.AbstractFlow;
import xyz.iamray.flow.bridge.ConvertHotWeiBoUrlBridgeAPI;
import xyz.iamray.flow.bridge.ListToOneBridgeAPI;
import xyz.iamray.flow.bridge.MobalHotListUrlBridgeAPI;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.impl.ForwardBlogAPI;
import xyz.iamray.weiboapi.api.impl.mobile.CrawlMobalHotListAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetMobalHotCommentAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetMobalWeiBoByUrlAPI;
import xyz.iamray.weiboapi.common.exception.WbException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 转发热榜微博
 */
@Slf4j
public class ForwardHotListFlow extends AbstractFlow {
    private static List<String> apis = new ArrayList<>();

    private static Map<String, String> requiredMap = new HashMap<>();


    static {
        apis.add(APINumber.LOGINAPI);
        apis.add(MobalHotListUrlBridgeAPI.INSTANCE.getNumber());
        apis.add(CrawlMobalHotListAPI.INSTANCE.getNumber());
        apis.add(ConvertHotWeiBoUrlBridgeAPI.INSTANCE.getNumber());
        apis.add(ListToOneBridgeAPI.INSTANCE.getNumber());
        apis.add(GetMobalWeiBoByUrlAPI.INSTANCE.getNumber());
        apis.add(GetMobalHotCommentAPI.INSTANCE.getNumber());
        apis.add(CommentToBlogBridgeAPI.INSTANCE.getNumber());
        apis.add(ForwardBlogAPI.INSTANCE.getNumber());

        //requiredMap.put(ListToOneBridgeAPI.INDEX,"");
        requiredMap.put(INIT_PARAM,"请传入第一个API执行需要的参数");
        requiredMap.put(INIT_UID,"请输入微博用户的uid");
    }

    protected List<String> getApis(){
        return apis;
    }

    @Override
    public void check() throws Exception {
        String mes = checkUtil(requiredMap);
        if(!mes.isEmpty()){
            throw new WbException(mes);
        }
    }

}

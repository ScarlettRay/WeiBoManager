package xyz.iamray.flow;

import xyz.iamray.flow.common.ApiBridgeNumber;
import xyz.iamray.weiboapi.api.APINumber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * 转发热榜微博
 */
public class ForwardHotListFlow extends AbstractFlow{

    private List<String> getApis(){
        List<String> apis = new ArrayList<>();
        apis.add(APINumber.LOGINAPI);
        apis.add(ApiBridgeNumber.MOBALHOTLISTURLBRIDGEAPI);
        apis.add(APINumber.CRAWLMOBALHOTLISTAPI);
        apis.add(ApiBridgeNumber.CONVERTHOTWEIBOURLBRIDGEAPI);

        return apis;
    }

    @Override
    public void check() throws Exception {

    }

    @Override
    public long execute() {
        return 0;
    }
}

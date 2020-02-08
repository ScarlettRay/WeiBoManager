package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.WeiBo;
import xyz.iamray.weibomanger.pojo.WeiBoer;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class CrawlWeiBo implements API<List<WeiBo>,WeiBoer> {

    @Override
    public R<List<WeiBo>> exe(WeiBoer param) {
        return null;
    }
}

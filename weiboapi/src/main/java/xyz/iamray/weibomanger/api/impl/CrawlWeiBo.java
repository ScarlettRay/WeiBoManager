package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.WeiBoer;

import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class CrawlWeiBo implements API<List<Blog>,WeiBoer> {

    @Override
    public R<List<Blog>> exe(WeiBoer param, Context context) {
        return null;
    }
}

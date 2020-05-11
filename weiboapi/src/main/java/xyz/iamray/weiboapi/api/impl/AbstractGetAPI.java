package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.core.SimpleSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public abstract class AbstractGetAPI<I,O> implements API<I,O> {

    protected abstract String getUrl(I param, Context context);

    protected Map<String,String> getRequestHeader(){
        return Constant.COMMON_HEADER;
    }

    protected abstract CrawlerAction getCrawlerAction();

    @Override
    public R<O> exe(I param, Context context) {
        SimpleSpider spider = SimpleSpider.make();
        if(context.getExecutorService() != null){
            spider.customThreadPool(context.getExecutorService(),true);
        }
        Result<O> result = spider.setRequestHeader(getRequestHeader())
                .setStarterConfiger(getUrl(param, context), getCrawlerAction())
                .start();
        return WeiBoUtil.dealResult(result);
    }
}

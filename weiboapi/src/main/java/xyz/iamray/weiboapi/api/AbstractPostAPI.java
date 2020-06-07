package xyz.iamray.weiboapi.api;

import xyz.iamray.action.CrawlerAction;
import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.common.constant.TextConstant;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.Collections;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 */
public abstract class AbstractPostAPI<I,O> implements API<I,O> {


    protected abstract String getUrl(I param, Context context);

    protected abstract Map<String,String> getPostBody(I param, Context context);

    protected abstract CrawlerAction getCrawlerAction();

    protected Map<String,String> getRequestHeader(){
        return Constant.COMMON_HEADER;
    }

    protected Map<String,Object> getSpiderProperties(Context context){
        return Collections.emptyMap();
    }

    @Override
    public R<O> exe(I param, Context context) {
        PostSpider spider = PostSpider.make();
        if(context.getExecutorService() != null){
            spider.customThreadPool(context.getExecutorService(),true);
        }
        if(getSpiderProperties(context) != null && !getSpiderProperties(context).isEmpty()){
            getSpiderProperties(context).forEach(spider::setProperty);
        }
        Result<O> result = spider.setRequestHeader(getRequestHeader())
                .setProperty(TextConstant.API_INPUT,param)
                .setStarterConfiger(getUrl(param,context),getPostBody(param, context), getCrawlerAction(),context.getHttpClient())
                .start();
        return WeiBoUtil.dealResult(result);
    }
}

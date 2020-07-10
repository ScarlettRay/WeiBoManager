package xyz.iamray.weiboapi.api;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.TextConstant;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

/**
 * @author winray
 * @since v1.0.1
 */
public abstract class AbstractSessionLessPostAPI<I,O> extends AbstractPostAPI<I,O> {

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
                .setListenHttpStatus(getHttpListenStatus())
                .setStarterConfiger(getUrl(param,context),getPostBody(param, context), getCrawlerAction())
                .start();
        return WeiBoUtil.dealResult(result);
    }
}

package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONObject;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.constant.TextConstant;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.Blog;

/**
 * @author winray
 * @since v1.0.1
 */
public class PraiseWeiBoAction extends AbstractJsonObjectCrawlerAction<Blog> {

    @Override
    public Blog crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        if(jsonObject.getInteger("code") == 100000){
            return this.getAttribute(TextConstant.API_INPUT,Blog.class);
        }
        throw new WbException(jsonObject.toJSONString());
    }
}

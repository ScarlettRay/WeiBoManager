package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.exception.spiderexceptions.SpiderException;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.Blog;

/**
 * @author winray
 * @since v1.0.1
 *转发微博后的结果处理动作
 */
@Slf4j
public class ForwardBlogAction extends AbstractJsonObjectCrawlerAction<Blog> {

    public static ForwardBlogAction getInstance(){
        return new ForwardBlogAction();
    }

    @Override
    public Blog crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        if(jsonObject.getInteger("code") == 100000){
            return new Blog();//TODO 获取mid
        }else{
            throw new SpiderException(jsonObject.toJSONString());
        }
    }
}

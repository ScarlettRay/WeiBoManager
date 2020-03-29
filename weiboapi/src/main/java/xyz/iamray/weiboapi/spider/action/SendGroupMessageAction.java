package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.exception.spiderexceptions.SpiderException;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.Message;

/**
 * @author winray
 * @since v1.0.1
 * {@link xyz.iamray.weiboapi.api.impl.SendPrivateLetterAPI}
 */
@Slf4j
public class SendGroupMessageAction extends AbstractJsonObjectCrawlerAction<Message> {

    public static final SendGroupMessageAction getInstance(){return new SendGroupMessageAction();}

    @Override
    public Message crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        log.info("SendGroupMessageActionv:" + jsonObject);
        if(jsonObject.getInteger("code") == 100000){
            return new Message();//TODO
        }else{
            throw new SpiderException(jsonObject.toJSONString());
        }
    }
}

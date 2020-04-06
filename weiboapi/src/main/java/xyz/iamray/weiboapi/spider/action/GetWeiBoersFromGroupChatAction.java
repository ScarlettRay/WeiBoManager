package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.ChatGroup;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 * {@link xyz.iamray.weiboapi.api.impl.GetWeiBoersFromGroupChatAPI}
 */
@Slf4j
public class GetWeiBoersFromGroupChatAction extends AbstractJsonObjectCrawlerAction<ChatGroup> {

    public static final GetWeiBoersFromGroupChatAction INSTANCE = new GetWeiBoersFromGroupChatAction();

    @Override
    public ChatGroup crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        log.info("GetWeiBoersFromGroupChatAction:" + jsonObject);
        if(jsonObject.getBoolean("result")){
            JSONArray mesArr = jsonObject.getJSONArray("messages");
            List<WeiBoer> weiBoers = new ArrayList<>();
            for (Object o : mesArr) {
                JSONObject mes = (JSONObject)o;
                WeiBoer weiBoer = new WeiBoer();
                weiBoer.setUid(mes.getString("from_uid"));
                weiBoer.setNickName((String) JSONPath.eval(mes,"$.from_user.screen_name"));
                weiBoers.add(weiBoer);
            }
            ChatGroup chatGroup = getAttribute("chat_group", ChatGroup.class);
            chatGroup.setWeiBoers(weiBoers);
            return chatGroup;
        }
        throw new WbException(jsonObject.toJSONString());
    }
}

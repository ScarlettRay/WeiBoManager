package xyz.iamray.weiboapi.spider.action.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetMobileMessageListAction extends AbstractJsonObjectCrawlerAction<List<Message>> {

    public static GetMobileMessageListAction getInstance(){
        return new GetMobileMessageListAction();
    }

    @Override
    public List<Message> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        if(jsonObject.getInteger("ok") == 1){
            JSONArray data = jsonObject.getJSONArray("data");
            List<Message> messageList = new ArrayList<>();
            for (Object datum : data) {
               JSONObject mesJson = (JSONObject)datum;
                Message message = new Message();
                message.setText(mesJson.getString("text"));
                message.setUnread(mesJson.getInteger("unread"));
                message.setId((String) JSONPath.eval(mesJson,"$.user.id"));
                messageList.add(message);
            }
            return messageList;
        }
        throw new WbException(jsonObject.toJSONString());
    }
}

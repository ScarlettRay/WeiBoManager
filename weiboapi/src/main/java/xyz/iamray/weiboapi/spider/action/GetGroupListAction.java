package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.FollowingGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetGroupListAction extends AbstractJsonObjectCrawlerAction<List<FollowingGroup>> {

    public static final GetGroupListAction INSTANCE = new GetGroupListAction();
    @Override
    public List<FollowingGroup> crawl(JSONObject jsonObject, CrawlMes crawlMes){
        if(jsonObject.getInteger("code") == 100000){
            JSONArray groupArr = jsonObject.getJSONArray("data");
            List<FollowingGroup> groups = new ArrayList<>();
            for (Object o : groupArr) {
                JSONObject groupJson = (JSONObject)o;
                FollowingGroup group = new FollowingGroup();
                group.setGroupId(groupJson.getString("gid"));
                group.setName(groupJson.getString("gname"));
                groups.add(group);
            }
            return groups;
        }
        throw new WbException(jsonObject.toJSONString());
    }
}

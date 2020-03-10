package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.Comment;
import xyz.iamray.weibomanger.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetMobalHotCommentAction extends AbstractJsonObjectCrawlerAction<List<Comment>> {

    public static GetMobalHotCommentAction INSTANCE = new GetMobalHotCommentAction();

    @Override
    public List<Comment> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        if(jsonObject.getInteger("ok") == 1){
            JSONArray commentArray = jsonObject.getJSONArray("data");
            for (Object o : commentArray) {
                JSONObject commentJsonObj = (JSONObject) o;
                Comment comment = new Comment();
                comment.setId(commentJsonObj.getString("id"));
                comment.setMid(commentJsonObj.getString("mid"));
                comment.setText(commentJsonObj.getString("text"));
                comment.setPraiseNum(commentJsonObj.getInteger("like_count"));
                if(commentJsonObj.getJSONObject("pic.large") != null){
                    JSONObject largeJson = commentJsonObj.getJSONObject("pic.large");
                    comment.setImageUrl(largeJson.getString("url"));
                }
                //博主
                JSONObject userJson = jsonObject.getJSONObject("user");
                ConcurrentHashMap
            }
        }
        return Collections.EMPTY_LIST;
    }
}

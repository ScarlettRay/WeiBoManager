package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.Blog;
import xyz.iamray.weibomanger.pojo.Comment;
import xyz.iamray.weibomanger.pojo.WeiBoer;

import java.util.Collections;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetMobalHotCommentAction extends AbstractJsonObjectCrawlerAction<Blog> {

    public static GetMobalHotCommentAction INSTANCE = new GetMobalHotCommentAction();


    @Override
    public Blog crawl(JSONObject jsonObject, CrawlMes crawlMes) {
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
                //评论的评论
                JSONArray subCommentArray = commentJsonObj.getJSONArray("comments");
                for (Object  : subCommentArray) {

                }
                //博主
                comment.setWeiBoer(getWeiBoerFromJson(jsonObject.getJSONObject("user")));
            }
        }
        return Collections.EMPTY_LIST;
    }

    public WeiBoer getWeiBoerFromJson(JSONObject userJson){
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid(userJson.getString("id"));
        weiBoer.setProfileUrl(userJson.getString("profile_url"));
        return weiBoer;
    }
}

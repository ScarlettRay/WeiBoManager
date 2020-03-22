package xyz.iamray.weiboapi.spider.action.mobal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.Comment;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetMobalHotCommentAction extends AbstractJsonObjectCrawlerAction<List<Comment>> {

    public static GetMobalHotCommentAction INSTANCE = new GetMobalHotCommentAction();


    @Override
    public List<Comment> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        List<Comment> comments = new ArrayList<>();
        if(jsonObject.getInteger("ok") == 1){
            JSONArray commentArray = (JSONArray) JSONPath.eval(jsonObject,"$.data.data");
            for (Object o : commentArray) {
                JSONObject commentJsonObj = (JSONObject) o;
                Comment comment = getCommentFromJson(commentJsonObj);
                //评论的评论
                Object subCommentObjs = commentJsonObj.get("comments");
                if(subCommentObjs instanceof JSONArray){
                    JSONArray subCommentArray = (JSONArray)subCommentObjs;
                    if(!subCommentArray.isEmpty()){
                        List<Comment> subComments = new ArrayList<>();
                        for (Object subCommentObj : subCommentArray) {
                            Comment subComment = getCommentFromJson((JSONObject) subCommentObj);
                            subComments.add(subComment);
                        }
                        comment.setSubComment(subComments);
                    }
                }

                //博主
                comment.setWeiBoer(getWeiBoerFromJson(commentJsonObj.getJSONObject("user")));
                comments.add(comment);
            }
        }
        return comments;
    }

    private WeiBoer getWeiBoerFromJson(JSONObject userJson){
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid(userJson.getString("id"));
        weiBoer.setProfileUrl(userJson.getString("profile_url"));
        return weiBoer;
    }

    private Comment getCommentFromJson(JSONObject commentJsonObj){
        Comment comment = new Comment();
        comment.setId(commentJsonObj.getString("id"));
        comment.setMid(commentJsonObj.getString("mid"));
        comment.setText(commentJsonObj.getString("text"));
        if(commentJsonObj.getJSONObject("pic.large") != null){
            JSONObject largeJson = commentJsonObj.getJSONObject("pic.large");
            comment.setImageUrl(largeJson.getString("url"));
        }
        return comment;
    }
}

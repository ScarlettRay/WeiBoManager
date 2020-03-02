package xyz.iamray.weibomanger.spider.action;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.Comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetHotCommentAction extends AbstractJsonObjectCrawlerAction<List<Comment>> {

    public static GetHotCommentAction INSTANCE = new GetHotCommentAction();

    @Override
    public List<Comment> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        if(jsonObject.getInteger("code") == 100000){
            String html = jsonObject.getJSONObject("data").getString("html");
            Document doc = Jsoup.parse(html);
            Elements els = doc.select("div[node-type=root_comment]");
            List<Comment> commentList = new ArrayList<>(els.size());
            for(Element el:els){
                Comment comment = new Comment();
                Element text = el.getElementsByClass("WB_text").get(0);
                comment.setContent(text.html());
                String praiseNum = el.select("span[node-type=like_status] em").get(1).text();
                comment.setPraiseNum(Integer.parseInt(praiseNum));
                commentList.add(comment);
            }
            return commentList;
        }

        return Collections.EMPTY_LIST;
    }
}

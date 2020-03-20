package xyz.iamray.weibomanger.spider.action.mobal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weibomanger.pojo.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 16:35:06
 * <p>
 */
public class GetMobalWeiBoByUrlAction extends AbstractJsonObjectCrawlerAction<List<Blog>> {

    public static final GetMobalWeiBoByUrlAction INSTANCE = new GetMobalWeiBoByUrlAction();

    @Override
    public List<Blog> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        List<Blog> blogs = new ArrayList<>();
        if(jsonObject.getInteger("ok") == 1){
            JSONArray cards = (JSONArray) JSONPath.eval(jsonObject,"$.data.cards");
            for (Object o: cards) {
                JSONObject mblog = ((JSONObject)o).getJSONObject("mblog");
                Blog blog = new Blog();
                blog.setMid(mblog.getString("mid"));
                blog.setReason(mblog.getString("text"));
                //图片
                JSONArray pics = mblog.getJSONArray("pics");
                List<String> imgs = new ArrayList<>();
                for (Object pic : pics) {
                    imgs.add((String) JSONPath.eval(pic,"$.large.url"));
                }
                blog.setImagePaths(imgs);
            }
        }
        return blogs;
    }

}

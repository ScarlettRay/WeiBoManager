package xyz.iamray.weiboapi.spider.action.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import xyz.iamray.action.impl.AbstractJsonObjectCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray
 * @create 2020-03-19 16:35:06
 * <p>
 */
public class GetMobileWeiBoByUrlAction extends AbstractJsonObjectCrawlerAction<List<Blog>> {

    public static final GetMobileWeiBoByUrlAction INSTANCE = new GetMobileWeiBoByUrlAction();

    @Override
    public List<Blog> crawl(JSONObject jsonObject, CrawlMes crawlMes) {
        List<Blog> blogs = new ArrayList<>();
        if(jsonObject.getInteger("ok") == 1){
            JSONArray cards = (JSONArray) JSONPath.eval(jsonObject,"$.data.cards");
            for (Object o: cards) {
                if(((JSONObject)o).getInteger("card_type")!=9)continue;
                JSONObject mblog = ((JSONObject)o).getJSONObject("mblog");
                Blog blog = new Blog();
                blog.setMid(mblog.getString("mid"));
                blog.setReason(mblog.getString("text"));
                //图片
                JSONArray pics = mblog.getJSONArray("pics");
                if(pics != null){
                    List<String> imgs = new ArrayList<>();
                    for (Object pic : pics) {
                        imgs.add((String) JSONPath.eval(pic,"$.large.url"));
                    }
                    blog.setImagePaths(imgs);
                }
                blogs.add(blog);
            }
        }
        return blogs;
    }

}

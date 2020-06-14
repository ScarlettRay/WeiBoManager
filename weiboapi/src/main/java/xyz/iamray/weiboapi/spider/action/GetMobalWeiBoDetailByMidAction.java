package xyz.iamray.weiboapi.spider.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import xyz.iamray.action.impl.AbstractStringCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.utils.RegexRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetMobalWeiBoDetailByMidAction extends AbstractStringCrawlerAction<Blog> {

    public static final GetMobalWeiBoDetailByMidAction INSTANCE = new GetMobalWeiBoDetailByMidAction();

    @Override
    public Blog crawl(String string, CrawlMes crawlMes) {
        Matcher ma = RegexRepo.RENDER_DATA_REGEX.matcher(string);
        String blogJsonStr = null;
        if(ma.find()){
            blogJsonStr = ma.group();
        }else{
            return null;
        }
        JSONArray blogArray = JSON.parseArray(blogJsonStr);
        if(blogArray.isEmpty()){
            return null;
        }
        JSONObject blogJson = blogArray.getJSONObject(0);

        return buildBlog(blogJson);
    }

    private Blog buildBlog(JSONObject blogJson){
        Blog blog = new Blog();
        JSONObject status = blogJson.getJSONObject("status");
        blog.setMid(status.getString("mid"));
        blog.setReason(status.getString("text"));
        blog.setSendTime(new Date(status.getString("created_at")));
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid(String.valueOf(JSONPath.eval(status,"$.user.id")));
        blog.setWeiBoer(weiBoer);
        //设置图片
        JSONArray picsArray = status.getJSONArray("pics");
        List<String> pics = new ArrayList<>();
        for (Object o : picsArray) {
            pics.add((String)JSONPath.eval(o,"$.large.url"));
        }
        blog.setImagePaths(pics);
        return blog;
    }
}

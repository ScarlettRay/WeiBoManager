package xyz.iamray.weiboapi.spider.action.mobile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import xyz.iamray.action.impl.AbstractStringCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.PrizeRequirement;
import xyz.iamray.weiboapi.common.Pair;
import xyz.iamray.weiboapi.pojo.PrizeBlog;
import xyz.iamray.weiboapi.utils.RegexRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author winray
 * @since v1.0.1
 */
public class GetPrizeRequirementsAction extends AbstractStringCrawlerAction<PrizeBlog> {

    public static final GetPrizeRequirementsAction INSTANCE = new GetPrizeRequirementsAction();

    @Override
    public PrizeBlog crawl(String string, CrawlMes crawlMes) {
        Matcher ma = RegexRepo.LOTTERY_REGEX.matcher(string);
        JSONObject jsonObject = null;
        if(ma.find()){
            jsonObject = JSON.parseObject(ma.group());
        }else{
            return null;
        }
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        List<Pair<PrizeRequirement,String>> tuples = new ArrayList<>();
        if (jsonArray.isEmpty())return null;
        JSONObject object = jsonArray.getJSONObject(0);
        if(object.getInteger("status") == 1){
            //已开奖
            return null;
        }
        String filters = object.getString("filter");
        String[] filterStrs = filters.split("；");
        for (String filterStr : filterStrs) {
            Pair<PrizeRequirement,String> tuple = PrizeRequirement.getRequirement(filterStr);
            tuples.add(tuple);
        }
        PrizeBlog blog = new PrizeBlog();
        blog.setRequirements(tuples);
        return blog;
    }
}

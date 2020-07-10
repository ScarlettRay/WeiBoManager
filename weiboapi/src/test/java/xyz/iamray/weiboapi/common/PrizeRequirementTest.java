package xyz.iamray.weiboapi.common;

import org.junit.Test;
import xyz.iamray.weiboapi.api.APIManager;
import xyz.iamray.weiboapi.api.ContextBuilder;
import xyz.iamray.weiboapi.api.bridge.impl.ExecuteRequirementsBridgeAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.LoginAPI;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.PrizeBlog;
import xyz.iamray.weiboapi.pojo.WeiBoer;
import xyz.iamray.weiboapi.utils.LoginUtil;
import xyz.iamray.weiboapi.utils.SpiderPool;
import xyz.iamray.weiboapi.utils.TestConstant;

import java.util.Arrays;
import java.util.List;


public class PrizeRequirementTest {

    @Test
    public void test(){
        PrizeBlog prizeBlog = new PrizeBlog();
        Blog blog = new Blog();
        blog.setMid("4517298339496530");
        WeiBoer weiBoer = new WeiBoer();
        weiBoer.setUid("6004281123");
        blog.setWeiBoer(weiBoer);
        List<Pair<PrizeRequirement,String>> list = Arrays.asList(
                new Pair(PrizeRequirement.FOLLOW_ME,""),
                new Pair(PrizeRequirement.PRAISE_BLOG,""),
                new Pair(PrizeRequirement.FORWRAD_BLOG,""),
                new Pair(PrizeRequirement.CUE_SOMEONE,""),
                new Pair(PrizeRequirement.FORWARD_KEY_WORD,"转发关键字：#为仝卓办理虚假转学手续6人被处理#"),
                new Pair(PrizeRequirement.FOLLOW_OTHER,"关注:@韩墨-"),
                new Pair(PrizeRequirement.COMMENT_BLOG,"评论"));
        prizeBlog.setRequirements(list);
        prizeBlog.setBlog(blog);
        Context context = ContextBuilder.buildContext(SpiderPool.executorService);
        context.setProperty(ExecuteRequirementsBridgeAPI.NICK_NAME,"梨视频");

        List<String> apis = Arrays.asList(LoginAPI.INSTANCE.getNumber(),ExecuteRequirementsBridgeAPI.INSTANCE.getNumber());
        LoginUtil.createSession();
        APIManager.call(prizeBlog,apis, TestConstant.UID,context);
    }
}

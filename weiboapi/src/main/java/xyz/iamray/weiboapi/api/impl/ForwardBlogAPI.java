package xyz.iamray.weiboapi.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.spider.action.ForwardBlogAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 转发微博的api
 */
public class ForwardBlogAPI implements API<Blog,Blog> {

    public static final ForwardBlogAPI INSTANCE = new ForwardBlogAPI();

    public static final String HAS_FORWRAD_MIDS = "ForwardBlogAPI-HasForwardKey";

    @Override
    public String getNumber() {
        return APINumber.FORWARDBLOGAPI;
    }

    @Override
    public R<Blog> exe(Blog blog, Context context) {
        List<String> mids = context.getProperty(HAS_FORWRAD_MIDS, ArrayList.class);
        if(!WeiBoUtil.isNull(mids) && mids.contains(blog.getMid()))return R.ok(blog);//已经转发过了

        String url = AutoWeiBoSpiderConstant.Forward_WeiBo_URL.replace("{}",context.getUid())+System.currentTimeMillis();
        Map<String,String> postBody = PostBodyBuildUtil.buildForwardParam(blog.getReason(),blog.getMid());
        Result<Blog> re = PostSpider.make().defaultThreadPool()
                .setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(url, postBody, ForwardBlogAction.getInstance(),context.getHttpClient())
                .start();
        if(mids == null){
            mids = new ArrayList<>();
        }
        mids.add(blog.getMid());
        context.setProperty(HAS_FORWRAD_MIDS,mids);

        return WeiBoUtil.dealResult(re);
    }
}

package xyz.iamray.flow.impl.ffp;

import xyz.iamray.core.SimpleSpider;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.Result;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.PrizeBlog;
import xyz.iamray.weiboapi.spider.action.mobile.GetPrizeRequirementsAction;
import xyz.iamray.weiboapi.utils.WeiBoUtil;

/**
 * @author winray
 * @since v1.0.1
 * 获取微博的抽奖要求
 */
public class GetPrizeRequirementsAPI implements API<Blog, PrizeBlog> {

    public static final GetPrizeRequirementsAPI INSTANCE = new GetPrizeRequirementsAPI();

    @Override
    public String getNumber() {
        return "GetPrizeRequirementsAPI";
    }

    @Override
    public R<PrizeBlog> exe(Blog blog, Context context) {
        String url = AutoWeiBoSpiderConstant.LOTTERY_URL + blog.getMid();
        SimpleSpider spider = SimpleSpider.make();
        spider.customThreadPool(context.getExecutorService(),true);
        Result<PrizeBlog> re = spider.setRequestHeader(SpiderConstant.DefaultHeader)
                .setStarterConfiger(url, GetPrizeRequirementsAction.INSTANCE).start();
        if(re.getObj() != null){
            re.getObj().setBlog(blog);
        }
        return WeiBoUtil.dealResult(re);
    }

}

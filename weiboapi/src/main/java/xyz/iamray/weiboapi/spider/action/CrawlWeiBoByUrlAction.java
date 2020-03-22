package xyz.iamray.weiboapi.spider.action;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.iamray.action.impl.AbstractDocumentCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.PropCrawlAction;
import xyz.iamray.weiboapi.pojo.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray
 * @create 2020-03-18 16:05:38
 * <p>一般针对热榜的微博抓取
 */
public class CrawlWeiBoByUrlAction extends AbstractDocumentCrawlerAction<List<Blog>> {

    public static final CrawlWeiBoByUrlAction INSTANCE = new CrawlWeiBoByUrlAction();

    private static final PropCrawlAction[] propCrawlActions = new PropCrawlAction[]{
            PropCrawlAction.P_WB_TEXT,
            PropCrawlAction.MID,
            PropCrawlAction.IMGS,
            //PropCrawlAction.FORWARD_NUM,
            //PropCrawlAction.COMMENT_NUM,
            //PropCrawlAction.PRAISE_NUM,
            PropCrawlAction.FROWARD
    };

    @Override
    public List<Blog> crawl(Document doc, CrawlMes crawlMes) {
        Elements els = doc.select("div[action-type=feed_list_item]");//获取全部列表
        //自定义要抓取的属性
        PropCrawlAction[] customPropActions = getAttr("cutsom_prop",PropCrawlAction[].class);
        if(customPropActions == null || customPropActions.length == 0)customPropActions = propCrawlActions;
        List<Blog> blogs = new ArrayList<>();
        for (Element el : els) {
            Blog blog = new Blog();
            for (PropCrawlAction action : customPropActions) {
                action.crawl(el,blog);
            }
            blogs.add(blog);
        }
        return blogs;
    }
}

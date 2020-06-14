package xyz.iamray.weiboapi.spider.action;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.iamray.action.impl.AbstractDocumentCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.PropCrawlAction;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.utils.RegexRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author winray
 * @since v1.0.1
 * 抓取博主主页的微博，使用的API是 {@link xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant#USER_HOME_WEIBO_URL}
 */
public class CrawlWeiBoInUserPageAction extends AbstractDocumentCrawlerAction<List<Blog>> {

    public static final CrawlWeiBoInUserPageAction INSTANCE = new CrawlWeiBoInUserPageAction();

    private static final PropCrawlAction[] propCrawlActions = new PropCrawlAction[]{
            PropCrawlAction.WB_TEXT,
            PropCrawlAction.MID,
            PropCrawlAction.IMGS,
            PropCrawlAction.SEND_TIME,
            PropCrawlAction.FORWARD_NUM,
            PropCrawlAction.COMMENT_NUM,
            PropCrawlAction.PRAISE_NUM,
            PropCrawlAction.FROWARD
    };

    @Override
    public List<Blog> crawl(Document document, CrawlMes crawlMes) {
        Elements els = document.getElementsByTag("script");
        if(els.isEmpty())return Collections.EMPTY_LIST;
        String text = els.get(0).html();
        Matcher ma = RegexRepo.HTML_REGEX.matcher(text);
        String htmlStr = null;
        if(ma.find()){
            htmlStr = ma.group();
        }else {
            return Collections.EMPTY_LIST;
        }
        if (htmlStr != null) {
            Document weiboDoc = Jsoup.parse(htmlStr);
            Elements feedEls = weiboDoc.select("div[action-type=feed_list_item]");//获取全部列表
            //自定义要抓取的属性
            PropCrawlAction[] customPropActions = getAttr("cutsom_prop", PropCrawlAction[].class);
            if (customPropActions == null || customPropActions.length == 0) customPropActions = propCrawlActions;
            List<Blog> blogs = new ArrayList<>();
            for (Element el : feedEls) {
                Blog blog = new Blog();
                for (PropCrawlAction action : customPropActions) {
                    action.crawl(el, blog);
                }
                blogs.add(blog);
            }
            return blogs;
        }
        return Collections.EMPTY_LIST;
    }
}

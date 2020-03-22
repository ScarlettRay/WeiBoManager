package xyz.iamray.weiboapi.spider.action;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.iamray.action.impl.AbstractDocumentCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.common.PropCrawlAction;
import xyz.iamray.weiboapi.pojo.Blog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winray
 * @since v1.0.1
 */
@Slf4j
public class CrawlWeiBoAction extends AbstractDocumentCrawlerAction<List<Blog>> {

    public static CrawlWeiBoAction INSTANCE = new CrawlWeiBoAction();

    private static final PropCrawlAction[] propCrawlActions = new  PropCrawlAction[]{
            PropCrawlAction.WB_TEXT,
            PropCrawlAction.MID,
            PropCrawlAction.IMGS,
            PropCrawlAction.SEND_TIME,
            PropCrawlAction.FORWARD_NUM,
            PropCrawlAction.COMMENT_NUM,
            PropCrawlAction.PRAISE_NUM,
            PropCrawlAction.FROWARD
    };

    private static final Pattern REGEX = Pattern.compile("(?<=html\":\")[\\s\\S]*(?=\"})");

    @Override
    public List<Blog> crawl(Document document, CrawlMes crawlMes){
        Matcher ma = REGEX.matcher(document.html());
        if(ma.find()){
            Document doc = Jsoup.parse(ma.group(0));
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

        return Collections.EMPTY_LIST;
    }

}

package xyz.iamray.weiboapi.spider.action;

import lombok.extern.slf4j.Slf4j;
import xyz.iamray.action.impl.AbstractStringCrawlerAction;
import xyz.iamray.repo.CrawlMes;
import xyz.iamray.weiboapi.pojo.Group;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winray
 * @since v1.0.1
 * {@link xyz.iamray.weiboapi.api.impl.GetWeiBoersFromGroupChatAPI}
 */
@Slf4j
public class GetWeiBoersFromGroupChatAction extends AbstractStringCrawlerAction<Group> {

    private static Pattern UID_PATTERN = Pattern.compile("(?<=from_uid\":)\\d+(?=,)");

    public static final GetWeiBoersFromGroupChatAction INSTANCE = new GetWeiBoersFromGroupChatAction();

    @Override
    public Group crawl(String s, CrawlMes crawlMes) {
        log.info("GetWeiBoersFromGroupChatAction:" + s);
        Matcher ma = UID_PATTERN.matcher(s);
        List<WeiBoer> weiBoers = new ArrayList<>();
        while(ma.find()){
            WeiBoer weiBoer = new WeiBoer();
            weiBoer.setUid(ma.group(0));
            weiBoers.add(weiBoer);
        }
        Group chatGroup = getAttr("chat_group", Group.class);
        chatGroup.setWeiBoers(weiBoers);
        return chatGroup;
    }
}

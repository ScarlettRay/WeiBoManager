package xyz.iamray.weibomanger.common;

import org.jsoup.nodes.Element;
import xyz.iamray.weibomanger.pojo.Blog;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winray
 * @since v1.0.1
 * 针对某个属性定义的抓取动作
 */
public enum PropCrawlAction {

    WB_TEXT(){
        @Override
        public void crawl(Element el, Blog blog) {
            blog.setReason(el.selectFirst("div[node-type=feed_list_content]").text());
        }
    },
    MID(){
        @Override
        public void crawl(Element el, Blog blog) {
            blog.setSendTime(new Date(Long.parseLong(el.selectFirst("a[node-type=feed_list_item_date]").attr("date"))));
        }
    },
    IMGS(){

        public Pattern imageListRegex = Pattern.compile("clear_picSrc=(.*?)&");

        @Override
        public void crawl(Element el, Blog blog) {
            Element ulElement = el.selectFirst("ul[node-type=fl_pic_list]");

            if (ulElement != null) {
                String imageUrl = null;
                try {
                    imageUrl = URLDecoder.decode(ulElement.attr("action-data"), "utf8");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                Matcher ma = imageListRegex.matcher(imageUrl);
                if (ma.find()) {
                    blog.setImagePaths(Arrays.asList(ma.group(1).split(",")));
                }
            }
        }
    },
    SEND_TIME(){
        @Override
        public void crawl(Element el, Blog blog) {
            Date date = new Date(Long.parseLong(el.selectFirst("a[node-type=feed_list_item_date]").attr("date")));
            blog.setSendTime(date);
        }
    },
    FORWARD_NUM(){
        @Override
        public void crawl(Element el, Blog blog) {
            blog.setForwardNum(getNum(el.selectFirst("span[node-type=forward_btn_text]").getElementsByTag("em").get(1).text()));
        }
    },
    COMMENT_NUM(){
        @Override
        public void crawl(Element el, Blog blog) {
            blog.setCommentNum(getNum(el.selectFirst("span[node-type=comment_btn_text]").getElementsByTag("em").get(1).text()));
        }
    },
    PRAISE_NUM(){
        @Override
        public void crawl(Element el, Blog blog) {
            blog.setPraiseNum(getNum(el.selectFirst("span[class=pos] span[node-type=like_status]").getElementsByTag("em").get(1).text()));
        }
    },
    //原博主的微博内容
    FROWARD(){
        @Override
        public void crawl(Element el, Blog blog) {
            if (el.hasAttr("isforward")) {
                blog.setIsforward(true);
                String minfo = el.attr("minfo");
                blog.setOuid(minfo.substring(3, minfo.indexOf("&")));
                blog.setOmid(el.attr("omid"));
                blog.setOcontent(el.selectFirst("div[node-type=feed_list_reason]").text());
                blog.setOsendTime(new Date(Long.parseLong(el.select("a[node-type=feed_list_item_date]").get(1).attr("date"))));

                Element wbinfo = el.selectFirst("a[node-type=feed_list_originNick]");
                blog.setOweiboerNickName(wbinfo.attr("nick-name"));
                blog.setOweiboerUrl(wbinfo.attr("href"));
            }
        }
    };

    int getNum(String str){
        if(Character.isDigit(str.toCharArray()[0])){
            return Integer.parseInt(str);
        }else{
            return 0;
        }
    }

    public abstract void crawl(Element el, Blog blog);
}

package xyz.iamray.weibomanger.common;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
            blog.setMid(el.attr("mid"));
        }
    },
    IMGS(){

        public Pattern imageListRegex = Pattern.compile("clear_picSrc=(.*?)&");

        @Override
        public void crawl(Element el, Blog blog) {
            Element mediaBox = el.selectFirst("div[class=WB_detail]>div[class=WB_media_wrap clearfix]>div[class=media_box]");
            if(mediaBox != null){
                //有媒体资源，图片或者视频
                Elements ulElements = mediaBox.getElementsByTag("ul");
                if (!ulElements.isEmpty() && ulElements.get(0).hasAttr("action-data")) {
                    Element ulElement = ulElements.get(0);
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
    },
    P_WB_TEXT(){
        @Override
        public void crawl(Element el, Blog blog) {
            blog.setReason(el.selectFirst("p[node-type=feed_list_content]").text());
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

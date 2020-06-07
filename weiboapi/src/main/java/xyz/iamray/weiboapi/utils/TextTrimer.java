package xyz.iamray.weiboapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winray
 * @since v1.0.1
 */
public class TextTrimer {

    private static final Pattern SPANPATTERN = Pattern.compile("(<span class=\"url-icon\">.*?</span>)");

    private static final Pattern HTMLPATTERN = Pattern.compile("<[^>]+>.*?<[^>]+>");

    private static final Pattern TEXTPATTERN = Pattern.compile("(?<=>).*?(?=<)");

    private static final Pattern IMGPATTERN = Pattern.compile("\\[.{1,}?\\]");

    /**
     * 去除html代碼，如果是emojy表情则替换
     * @return
     */
    public static String trimHtml(String text){
        //emojy表情替换
        Matcher ma = SPANPATTERN.matcher(text);
        while (ma.find()){
            String span = ma.group();
            Matcher imgma = IMGPATTERN.matcher(span);
            StringBuilder sb = new StringBuilder();
            while (imgma.find()){
                sb.append(imgma.group());
            }
            text = text.replace(span,sb.toString());
        }
        //去除标签
        Matcher ma1 = HTMLPATTERN.matcher(text);
        while (ma1.find()){
            String html = ma1.group();
            Matcher textma = TEXTPATTERN.matcher(html);
            if(textma.find()){
                text = text.replace(html,textma.group());
            }
        }
        return text;
    }

    /*
    public static void main(String[] args) {
        System.out.println(trimHtml("test <a href='/n/语清潇-XZ'>@语清潇-XZ</a><span class=\"url-icon\"><img alt=[跪了] src=\"//h5.sinaimg.cn/m/emoticon/icon/default/d_guile-7b3e474f7f.png\" style=\"width:1em; height:1em;\" /></span> <a href='/n/语清潇-XZ'>@语清潇-XZ</a>test"));
    }
     */
}

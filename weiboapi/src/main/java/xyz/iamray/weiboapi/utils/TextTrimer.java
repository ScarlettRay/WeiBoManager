package xyz.iamray.weiboapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winray
 * @since v1.0.1
 */
public class TextTrimer {

    private static final Pattern SPANPATTERN = Pattern.compile("(<span class=\"url-icon\">.*?</span>)");

    private static final Pattern IMGPATTERN = Pattern.compile("\\[.{1,}?\\]");

    /**
     * 去除html代碼，如果是emojy表情则替换
     * TODO
     * @return
     */
    public static String trimHtml(String text){
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
        return text;
    }
}

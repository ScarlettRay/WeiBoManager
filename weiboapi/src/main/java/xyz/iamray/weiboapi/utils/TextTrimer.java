package xyz.iamray.weiboapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winray
 * @since v1.0.1
 */
public class TextTrimer {

    private static final Pattern SPANPATTERN = Pattern.compile("(?<=<span class=\"url-icon\">).*(?=</span>)");

    /**
     * 去除html代碼，如果是emojy表情则替换
     * TODO
     * @return
     */
    public static String trimHtml(String text){
        Matcher ma = SPANPATTERN.matcher(text);
        while (ma.find()){

        }
        return text;
    }
}

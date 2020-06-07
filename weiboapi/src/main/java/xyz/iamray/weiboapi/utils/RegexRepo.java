package xyz.iamray.weiboapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author winray
 * @since v1.0.1
 * 正则表达式库
 */
public class RegexRepo {

    public static final Pattern JSON_REGEX = Pattern.compile("(?<=\\()[\\s\\S]*(?=\\))");

    public static void main(String[] args) {
        String str = "parent.FM.view({\" test\ntest \"})";
        Matcher ma = JSON_REGEX.matcher(str);
        if(ma.find()){
            System.out.println(ma.group());
        }
    }


}

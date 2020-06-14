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

    public static final Pattern HTML_REGEX = Pattern.compile("(?<=html\":\")[\\s\\S]*(?=\")");

    public static final Pattern RENDER_DATA_REGEX = Pattern.compile("(?<=\\$render_data = )[\\s\\S]*(?=\\[0\\])");

    public static final Pattern LOTTERY_REGEX = Pattern.compile("(?<=window.__DATA__ = )[\\s\\S]*(?=;</script>)");

    public static void main(String[] args) {
        String str = "    <script type=\"text/javascript\">window.__DATA__ = {\n" +
                "    \"total\": null,\n" +
                "    \"list\": [\n" +
                "        {\n" +
                "            \"name\": \"武汉小龙虾热干面\",\n" +
                "            \"total\": 5,\n" +
                "            \"amount\": 0,\n" +
                "            \"type\": 0,\n" +
                "            \"filter\": \"点赞微博；关注我\",\n" +
                "            \"time\": \"2020年06月16日 22:40\",\n" +
                "            \"status\": 0,\n" +
                "            \"result_url\": \"https://event.weibo.com/yae/event/lottery/result?pageid=100140E9437671&id=7578490\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"三星A90\",\n" +
                "            \"total\": 1,\n" +
                "            \"amount\": 0,\n" +
                "            \"type\": 0,\n" +
                "            \"filter\": \"转发微博；关注我\",\n" +
                "            \"time\": \"2020年06月16日 22:30\",\n" +
                "            \"status\": 0,\n" +
                "            \"result_url\": \"https://event.weibo.com/yae/event/lottery/result?pageid=100140E9437671&id=7563866\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"weibo\": {\n" +
                "        \"mid\": \"4511143818902057\",\n" +
                "        \"created_at\": \"2020-06-01 23:06:02\",\n" +
                "        \"text\": \"我又来送手机啦啦啦啦啦！\\n\\n转发+关注，送一台三星A90！\\n点赞里再抽五位送小龙虾热干面！\\n6月16号开！@微博抽奖平台 \\n\\n不过滤！祝大家六月好运666！[好爱哦]\\n#为湖北助力一夏# \u200B http://t.cn/A62Nd2Q3 \u200B\",\n" +
                "        \"reposts_count\": 24113,\n" +
                "        \"comments_count\": 5445,\n" +
                "        \"attitudes_count\": 13657,\n" +
                "        \"wb_type\": 0,\n" +
                "        \"wb_img\": \"http://wx3.sinaimg.cn/bmiddle/006oEVRLly1gfcvn2resyj30n00n0gq3.jpg\",\n" +
                "        \"img_type\": 0,\n" +
                "        \"user\": {\n" +
                "            \"id\": 5861184617,\n" +
                "            \"profile_image_url\": \"https://tvax1.sinaimg.cn/crop.0.0.828.828.180/006oEVRLly8gdim2zeun6j30n00n075h.jpg?KID=imgbed,tva&Expires=1592136832&ssig=getLqPP7dd\",\n" +
                "            \"name\": \"搞机少女\",\n" +
                "            \"detail\": \"VX：ya913896321\",\n" +
                "            \"verified_type\": 0\n" +
                "        }\n" +
                "    },\n" +
                "    \"cur_page\": \"1\",\n" +
                "    \"total_page\": 0\n" +
                "};</script>";
        Matcher ma = LOTTERY_REGEX.matcher(str);
        if(ma.find()){
            System.out.println(ma.group());
        }
    }


}

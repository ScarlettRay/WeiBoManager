package xyz.iamray.weiboapi.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuwenrui
 * @date 2018/8/25
 */
public class PostBodyBuildUtil {
    private PostBodyBuildUtil(){
    }


    public static Map<String,String> buildAddGroupParam(String name, String desc, Boolean ispublic){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("ispublic",ispublic.toString());
        map.put("desc",desc);
        map.put("_t","0");
        return map;
    }

    public static Map<String,String> buildGroupAddParam(String gid, String uid){
        Map<String,String> map = new HashMap<>();
        map.put("user",uid);
        map.put("type","s");
        map.put("gid","[{\"gid\":\""+gid+"\"}]");
        map.put("_t","0");
        return map;
    }

    public static Map<String,String> buildPrivateMesParam(String uid, String text){
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("location","msgdialog");
        map.put("module","module");
        map.put("style_id","1");
        map.put("text",text);
        map.put("_t","0");
        return map;
    }

    public static Map<String,String> buildFollowedParam(String uid){
        Map<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("f","1");
        map.put("refer_flag","1005050001_");
        map.put("location","page_100505_home");
        map.put("oid",uid);
        map.put("wforce","1");
        map.put("nogroup","1");
        map.put("refer_from","profile_headerv6");
        map.put("template","7");
        map.put("isrecommend","1");
        map.put("_t","0");
        return map;
    }

    @Deprecated
    public static Map<String,String> buildGroupChatParam(String gid, String text){
        Map<String,String> map = new HashMap<>();
        map.put("location","msgdialog");
        map.put("module","msgissue");
        map.put("style_id","1");
        map.put("text",text);
        map.put("gid",gid);
        map.put("tovfids","");
        map.put("fids","");
        map.put("type","2");
        map.put("_t","0");
        map.put("el","[object HTMLDivElement]");
        return map;
    }

    public static Map<String,String> buildGroupChatBody(String gid, String text){
        Map<String,String> map = new HashMap<>();
        map.put("content",text);
        map.put("id",gid);
        map.put("media_type","0");
        map.put("is_encoded","0");
        map.put("source","209678993");
        return map;
    }


        /**
         * 构建发送微博的请求体
         * @param reason
         * @param mid
         * @return
         */
    public static Map<String,String> buildForwardParam(String reason, String mid){
        Map<String,String> map = new HashMap<>();
        map.put("pic_src","");
        map.put("pic_id","");
        map.put("appkey","");
        map.put("mid",mid);
        map.put("style_type","");
        map.put("mark","");
        map.put("reason",reason);
        map.put("location","v6_content_home");
        map.put("pdetail","");
        map.put("module","");
        map.put("page_module_id","");
        map.put("refer_sort","");
        map.put("rank","0");
        map.put("rankid","0");
        map.put("group_source","group_gid");
        map.put("isReEdit","false");
        map.put("_t","0");
        map.put("rid","");

        return map;
    }

    /**
     * 构建转发微博的请求体
     * @param text
     * @return
     */
    public static Map<String,String> buildSendBlogParam(String text, List<String> pics){
        Map<String,String> map = new HashMap<>();
        map.put("location","v6_content_home");
        map.put("text",text);
        map.put("appkey","");
        map.put("style_type","1");
        map.put("pic_id","");
        map.put("tid","");
        map.put("pdetail","");
        map.put("mid","");
        map.put("isReEdit","false");
        map.put("rank","0");
        map.put("rankid","");
        map.put("module","stissue");
        map.put("pub_source","main_");
        map.put("pub_type","dialog");
        map.put("isPri","0");
        map.put("_t","0");
        if(pics != null && pics.size() > 0){
            map.put("pic_id", WeiBoUtil.strjoin(pics,"|"));
            map.put("updata_img_num", String.valueOf(pics.size()));
        }
        return map;
    }


    public static Map<String,String> buildLoginParam(String su, String servertime, String nonce, String sp, String rsakv){
        Map<String,String> map = new HashMap<>();
        map.put("gateway","1");
        map.put("from","");
        map.put("savestate","7");
        map.put("userticket","1");
        map.put("ssosimplelogin","1");
        map.put("vsnf","1");
        map.put("vsnval","");
        map.put("su",su);
        map.put("service","miniblog");
        map.put("servertime",servertime);
        map.put("nonce",nonce);
        map.put("pwencode","rsa2");
        map.put("sp",sp);
        map.put("encoding","UTF-8");
        map.put("url","https://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack");
        map.put("returntype","META");
        map.put("rsakv",rsakv);
        map.put("sr","1440*900");
        map.put("prelt","493");
        return map;
    }

    /**
     * 图片上传请求体
     * @param base64
     * @return
     */
    public static Map<String,String> buildSendWeiBoParam(String base64){
        Map<String,String> map = new HashMap<>();
        map.put("b64_data",base64);
        return map;
    }

    public static Map<String,String> buildPraiseWeiBoParam(String mid){
        Map<String,String> map = new HashMap<>();
        map.put("mid",mid);
        map.put("location","");
        map.put("action_code","13");
        map.put("_t","0");
        return map;
    }

    public static Map<String,String> buildCommentParam(String mid,String content){
        Map<String,String> map = new HashMap<>();
        map.put("act","post");
        map.put("mid",mid);
        //map.put("uid",);
        map.put("forward","0");
        map.put("isroot","0");
        map.put("content",content);
        map.put("pageid","weibo");
        map.put("dom","javascript:void(0)");
        //map.put("ouid",);
        map.put("action_code","31");
        map.put("_t","0");
        return map;
    }



}

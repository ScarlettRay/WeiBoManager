package xyz.iamray.weiboapi.common.constant;

/**
 * Created by liuwenrui on 2018/4/15
 * auto 微博 spider常量
 */
public class AutoWeiBoSpiderConstant {

    //传递的参数名
    public static final String  NEED_NEWGROUP = "needNewGroup";//是否需要新建分组

    //get 方法 openAPI
    public static final String GET_FOLLOWS_URL = "https://api.weibo.com/2/friendships/friends.json";


    //移动端微博的请求api {}的内容为 domain+uid
    public static final String GET_FOLLOES_URL_MOBAL = "https://m.weibo.cn/api/container/getSecond?containerid={}_-_FOLLOWERS&page=";

    //
    public static final String MY_UID = "5945738590";

    public static final String  My_UID_WITHDOMAIN = "1005055945738590";

    public static final Integer PAGE_COUNT = 10;

    //微博列表的MobalAPI
    public static final String WEIBO_URL = "https://m.weibo.cn/api/container/getIndex?type=uid&value={}&containerid=107603{}";

    //网页微博列表api
    public static final String WEIBO_WEB_URL = "https://weibo.com/u/{uid}?profile_ftype=1&is_all=1#_0";

    public static final String WEIBO_WEB_URL_HOT = "https://weibo.com/u/{uid}?is_search=0&visible=0&is_hot=1&is_tag=0&profile_ftype=1&page=";

    //图片数大于5的才爬
    public static final Integer PIC_SIZE = 5;

    /**
     * 微博预登陆接口
     */
    public static final String WEIBO_PRELOGINURL = "http://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController.preloginCallBack&su={}&rsakt=mod&client=ssologin.js(v1.4.19)&_=1364875106625";

    /**
     * 微博登陆接口
     */
    public static final String WEIBO_LOGINURL = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)";

    /**
     * 获取微博验证码
     */
    public static final String WEIBO_CODEURL = "http://login.sina.com.cn/cgi/pin.php?s=0&p={p}";

    /**
     * 发送微博的URL
     */
    public static final String SEND_BLOG_URL = "https://weibo.com/aj/mblog/add?ajwvr=6&__rnd=";

    /**
     * 我的微博主页URL
     */
    public static final String MyWeiBO_ProFile = "https://weibo.com/u/5945738590/home";

    /**
     * 转发微博的URL {uid}
     */
    public static final String Forward_WeiBo_URL = "https://weibo.com/aj/v6/mblog/forward?ajwvr=6&domain={}&__rnd=";

    /**
     * 摄影组下的关注
     */
    public static final String SHEYING_FOLLOW_URL = "https://weibo.com/p/{uid}/myfollow?gid=3984393204979718&Pl_Official_RelationMyfollow__92_page=";

    /**
     * 骚话组下的关注
     */
    public static final String SAOHUA_FOLLOW_URL = "https://weibo.com/p/{uid}/myfollow?gid=4231209502641627&Pl_Official_RelationMyfollow__92_page=";

    public static final String MingXing_Follow_URL = "https://weibo.com/p/1005055945738590/myfollow?t=1&gid=4025574361414188&cfs=&Pl_Official_RelationMyfollow__92_page=";
    /**
     * 继续加载更多的url
     */
    public static final String LOAD_MORE_URL = "https://weibo.com/p/aj/v6/mblog/mbloglist?ajwvr=6&domain={domain}&profile_ftype=1&is_all=1&pl_name=Pl_Official_MyProfileFeed__25&id={pageid}&script_uri=/u/{uid}&feed_type=0&page={page}&pre_page={page}&domain_op={domain}&pagebar=";

    /**
     * 发送群消息的url
     */
    public static final String GROUP_CHAT_ADD_URL= "https://weibo.com/aj/message/groupchatadd?_wv=5&ajwvr=6&__rnd=";

    /**
     * 获取微博群上求粉的账号
     */
    public static final String Following_FromQueryMes_URL = "https://api.weibo.com/groupchat/query_messages.json?id={gid}&count=3&is_encoded=1&convert_emoji=1&is_pc=1&source=209678993&__rnd=";

    /**
     * 关注Url
     */
    public static final String Followed_URL = "https://weibo.com/aj/f/followed?ajwvr=6&__rnd=";

    /**
     * 私信的url
     */

    public static final String SendPrivateMes_URL = "https://weibo.com/aj/message/add?ajwvr=6&__rnd=";

    /**
     * 要求回粉的text
     */
    public static final String AskForFollowingPrivateMes = "你好，麻烦回一下粉 [doge][doge]";

    /**
     * 分组url
     */
    public static final String UpdateGroup_URL = "https://weibo.com/aj/f/group/update?ajwvr=6&__rnd=";

    /**
     * 私信URL
     */
    public static final String Message_URL = "https://weibo.com/messages";

    public static final String ADD_GROUP_URL = "https://weibo.com/aj/f/group/add?ajwvr=6&__rnd=";

    /**
     * 上传图片的URL POST
     * 需要替换的是URL(weibo.com/u/uid)
     * nick(用户名)
     */
    public static final String Img_UPLOAD_URL = "https://picupload.weibo.com/interface/pic_upload.php?mime=image%2Fjpeg&data=base64&url={url}&markpos=1&logo=1&nick={nick}&marks=0&app=miniblog&s=rdxt&pri=null&file_source=1&cb=https%3A%2F%2Fweibo.com%2Faj%2Fstatic%2Fupimgback.html%3F_wv%3D5%26callback%3DSTK_ijax_";

    public static final String RPOFILE_URL_WITH_PAGE = "https://weibo.com/{uid}?is_all=1&is_tag=0&page={page}";

    //博主微博列表
    public static final String WEIBO_HOME_URL = "https://weibo.com/p/{uid}/home?pids=Pl_Official_MyProfileFeed__23&is_all=1&page={page}&ajaxpagelet=1";

    //微博热门评论
    public static final String WEIBO_HOT_COMMENT_URL = "https://weibo.com/aj/v6/comment/small?ajwvr=6&act=list&mid={mid}&isMain=true&comment_type=0&_t=0&__rnd=&comment_type=0&_t=0&__rnd=";

    //移动版网页微博热门评论
    public static final String WEIBO_MOBAL_HOT_COMMENT_URL = "https://m.weibo.cn/comments/hotflow?mid={mid}";
}

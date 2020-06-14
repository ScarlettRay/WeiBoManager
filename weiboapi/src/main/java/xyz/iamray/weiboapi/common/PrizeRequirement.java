package xyz.iamray.weiboapi.common;

import xyz.iamray.weiboapi.api.APIManager;
import xyz.iamray.weiboapi.api.bridge.impl.ExecuteRequirementsBridgeAPI;
import xyz.iamray.weiboapi.api.context.Context;
import xyz.iamray.weiboapi.api.impl.FollowWeiboerAPI;
import xyz.iamray.weiboapi.api.impl.ForwardBlogAPI;
import xyz.iamray.weiboapi.api.impl.PraiseWeiBoAPI;
import xyz.iamray.weiboapi.api.impl.mobile.GetUidByNickNameAPI;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.pojo.Blog;
import xyz.iamray.weiboapi.pojo.PrizeBlog;
import xyz.iamray.weiboapi.pojo.WeiBoer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author winray
 * @since v1.0.1
 *抽奖要求
 */
public enum PrizeRequirement {

    //转发微博
    FORWRAD_BLOG(){
        @Override
        public boolean exe(PrizeBlog prizeBlog, String filter, Context context) {
            //构建转发文字
            StringBuilder sb = new StringBuilder("参与抽奖，谢谢。");
            for (Tuple<PrizeRequirement, String> requirement : prizeBlog.getRequirements()) {
                if (requirement.A.equals(FORWARD_KEY_WORD)){
                    sb.append(requirement.B);
                }else if(requirement.A.equals(CUE_SOMEONE)){
                    String name = context.getProperty(ExecuteRequirementsBridgeAPI.NICK_NAME,String.class);
                    sb.append("@" + name);
                }
            }
            Blog blog = new Blog();
            blog.setMid(blog.getMid());
            blog.setReason(sb.toString());
            R<Blog> r = APIManager.callSingle(blog, ForwardBlogAPI.INSTANCE.getNumber(),null,context);
            return r.getStatus() != Status.ERR;
        }
    },
    //关注我
    FOLLOW_ME(){
        @Override
        public boolean exe(PrizeBlog prizeBlog, String filter, Context context) {
            String uid = prizeBlog.getBlog().getWeiBoer().getUid();
            WeiBoer weiBoer = new WeiBoer();
            weiBoer.setUid(uid);
            R<WeiBoer> r = APIManager.callSingle(weiBoer, FollowWeiboerAPI.INSTANCE.getNumber(),null,context);
            return r.getStatus() != Status.ERR;
        }
    },
    //关注其他人
    FOLLOW_OTHER(){
        @Override
        public boolean exe(PrizeBlog prizeBlog, String filter, Context context) {
            String nickName = filter.replace("关注:@","").trim();
            List<String> apis = new ArrayList<>();
            apis.add(GetUidByNickNameAPI.INSTANCE.getNumber());
            apis.add(FollowWeiboerAPI.INSTANCE.getNumber());
            R<WeiBoer> r = APIManager.call(nickName,apis,null,context);
            return r.getStatus() != Status.ERR;
        }
    },
    //转发关键字
    FORWARD_KEY_WORD(){
        @Override
        public boolean exe(PrizeBlog prizeBlog, String filter, Context context) {
            return true;
        }
    },
    //点赞微博
    PRAISE_BLOG(){
        @Override
        public boolean exe(PrizeBlog prizeBlog, String filter, Context context) {
            R<WeiBoer> r = APIManager.callSingle(prizeBlog.getBlog(), PraiseWeiBoAPI.INSTANCE.getNumber(),null,context);
            return r.getStatus() != Status.ERR;
        }
    },
    //@某位好友
    CUE_SOMEONE(){
        @Override
        public boolean exe(PrizeBlog prizeBlog, String filter, Context context) {
            return true;
        }
    };


    public static Tuple<PrizeRequirement,String> getRequirement(String string){
        if(string.contains("转发微博")){
            return new Tuple<>(FORWRAD_BLOG,null);
        }else if(string.contains("关注我")){
            return new Tuple<>(FOLLOW_ME,null);
        }else if(string.contains("点赞微博")){
            return new Tuple<>(PRAISE_BLOG,null);
        }else if(string.contains("关注:@")){
            return new Tuple<>(FOLLOW_OTHER,string);
        }else if (string.contains("@1个好友")){
            return new Tuple<>(CUE_SOMEONE,null);
        }else if (string.contains("关键字")){
            return new Tuple<>(FORWARD_KEY_WORD,string);
        }
        throw new WbException("未找到对应的抽奖类型：" + string);
    }

    public abstract boolean exe(PrizeBlog prizeBlog, String filter, Context context);
}

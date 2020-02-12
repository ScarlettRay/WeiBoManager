package xyz.iamray.weibomanger.api;

import xyz.iamray.weibomanger.common.R;

/**
 * @author winray
 * @since v1.0.1
 * 微博管理机器人的接口调用标准定义
 */
public interface API<T,E> {

    R<T> exe(E param, Context context);

    /*
    //登录
    R login(WeiBoer weiBoer);

    //根据微博Id爬取热门评论
    R getWeiBoHotCommentByWeiBoId();

    //根据用户Id爬取微博
    R crawlWeiBoByUserId();

    //发微博
    R deliverWeiBo();

    //发表评论
    R deliverComment();

    //发送到群
    R sendMessageToGroup();

    //关注
    R follow();

    //取关
    R unfollow();

    //点赞
    R praise();

    //转发
    R forward();
     */
}

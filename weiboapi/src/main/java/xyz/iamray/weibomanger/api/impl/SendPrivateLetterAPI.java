package xyz.iamray.weibomanger.api.impl;

import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.constant.Constant;
import xyz.iamray.weibomanger.pojo.PrivateLetter;
import xyz.iamray.weibomanger.spider.action.SendPrivateLetterAction;
import xyz.iamray.weibomanger.utils.PostBodyBuildUtil;

import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 私信api
 */
public class SendPrivateLetterAPI implements API<PrivateLetter,PrivateLetter> {

    @Override
    public R<PrivateLetter> exe(PrivateLetter letter, Context context) {
        Map<String,String> postBody = PostBodyBuildUtil.buildPrivateMesParam(letter.getUid(),letter.getContent());
        Result<PrivateLetter> re = PostSpider.make().defaultThreadPool().setRequestHeader(Constant.COMMON_HEADER)
                .setStarterConfiger(AutoWeiBoSpiderConstant.SendPrivateMes_URL+System.currentTimeMillis(),
                        postBody, SendPrivateLetterAction.INSTANCE,context.getHttpClient())
                .start();
        return R.ok(re.getObj());
    }
}

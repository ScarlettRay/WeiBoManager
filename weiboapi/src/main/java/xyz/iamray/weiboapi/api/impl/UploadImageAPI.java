package xyz.iamray.weiboapi.api.impl;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.link.http.HttpClientTool;
import xyz.iamray.repo.NormalCrawlMes;
import xyz.iamray.weiboapi.api.API;
import xyz.iamray.weiboapi.api.APINumber;
import xyz.iamray.weiboapi.api.Context;
import xyz.iamray.weiboapi.common.R;
import xyz.iamray.weiboapi.common.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weiboapi.common.constant.Constant;
import xyz.iamray.weiboapi.common.exception.WbException;
import xyz.iamray.weiboapi.spider.action.UploadImageAction;
import xyz.iamray.weiboapi.utils.PostBodyBuildUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author winray
 * @since v1.0.1
 * 上传图片api
 */
public class UploadImageAPI implements API<String,String> {

    public final static UploadImageAPI INSTANCE = new UploadImageAPI();

    @Override
    public String getNumber() {
        return APINumber.UPLOADIMAGEAPI;
    }

    @Override
    public R<String> exe(String imagePath, Context context) {
        String url = AutoWeiBoSpiderConstant.Img_UPLOAD_URL
                .replace("{url}",context.getWeiBoer().getProfileUrl())
                .replace("{nick}",context.getWeiBoer().getNickName())
                +System.currentTimeMillis();
        String imageBase64 = null;
        //判断图片位置
        if(imagePath.startsWith("http")){
            Map<String,String> Header = context.getProperty("header", HashMap.class);
            byte[] bytes = HttpClientTool.defaultGet(imagePath, Header,new NormalCrawlMes(),byte[].class);
            if(bytes != null){
                imageBase64 = Base64.getEncoder().encodeToString(bytes);
            }else{
                throw new WbException("Read time out!");
            }
        }else{
            try {
                FileInputStream in = new FileInputStream(new File(imagePath));
                imageBase64 = Base64.getEncoder().encodeToString(IOUtils.toByteArray(in));
            } catch (IOException e) {
                throw new WbException(e.getMessage());
            }
        }
        if(imageBase64 != null){
            Map<String,String> postBody = PostBodyBuildUtil.buildSendWeiBoParam(imageBase64);
            PostSpider postSpider = PostSpider.make();
            postSpider.customThreadPool(context.getExecutorService(),true);
            Result<String> result = postSpider.setRequestHeader(Constant.COMMON_HEADER)
                    .setStarterConfiger(url,postBody, UploadImageAction.INSTANCE,context.getHttpClient())
                    .setListenHttpStatus(HttpStatus.SC_MOVED_TEMPORARILY)
                    .start();
            return R.ok(result.getObj());
        }else{
            throw new WbException("不能获取图片资源，请检查图片链接是否有效！");
        }

    }
}

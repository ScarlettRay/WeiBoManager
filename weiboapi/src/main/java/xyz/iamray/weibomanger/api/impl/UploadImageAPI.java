package xyz.iamray.weibomanger.api.impl;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import xyz.iamray.core.PostSpider;
import xyz.iamray.link.Result;
import xyz.iamray.link.http.HttpClientTool;
import xyz.iamray.repo.NormalCrawlMes;
import xyz.iamray.weibomanger.api.API;
import xyz.iamray.weibomanger.api.APINumber;
import xyz.iamray.weibomanger.api.Context;
import xyz.iamray.weibomanger.common.R;
import xyz.iamray.weibomanger.common.exception.WbException;
import xyz.iamray.weibomanger.constant.AutoWeiBoSpiderConstant;
import xyz.iamray.weibomanger.constant.Constant;
import xyz.iamray.weibomanger.spider.action.UploadImageAction;
import xyz.iamray.weibomanger.utils.PostBodyBuildUtil;

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
    @Override
    public APINumber getNumber() {
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
            postSpider.setCumstomizeExecutorService(context.getExecutorService());
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

package xyz.iamray.weibomanger.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import xyz.iamray.core.HttpConstant;
import xyz.iamray.core.SpiderConstant;
import xyz.iamray.link.http.HttpClientTool;
import xyz.iamray.repo.NormalCrawlMes;
import xyz.iamray.weibomanger.common.exception.WbException;
import xyz.iamray.weibomanger.common.constant.AutoWeiBoSpiderConstant;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuwenrui
 * @date 2018/5/26
 * 用户登录的核心方法
 */
public class WeiBoLoginService {

    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CHARSET_GBK = "GBK";
    public static final String RSA_ALGORITHM = "RSA";
    private static Pattern prejsonstr = Pattern.compile("(?<=\\().*(?=\\))");
    private static Pattern urlPattern = Pattern.compile("https://weibo.*&retcode=0");
    private static Pattern uniqueidPattern = Pattern.compile("uniqueid\":\"(\\d+)");
    private static Pattern failLoginReason = Pattern.compile("(?<=&reason=).*(?=\")");

    private WeiBoLoginMes weiBoLoginMes = null;


    public  void preLogin(String username,String password,WeiBoLoginMes weiBoLoginMes) throws UnsupportedEncodingException {
        this.weiBoLoginMes = weiBoLoginMes;
        weiBoLoginMes.setSu(Base64.getEncoder().encodeToString(URLEncoder.encode(username,CHARSET_UTF8).getBytes(CHARSET_UTF8)));
        String  jsonStr =  HttpClientTool.get(AutoWeiBoSpiderConstant.WEIBO_PRELOGINURL.replace("{}",weiBoLoginMes.getSu()),
                SpiderConstant.DefaultHeader,new NormalCrawlMes(),weiBoLoginMes.getWeiboHttpClient(),String.class);
        Matcher m = prejsonstr.matcher(jsonStr);
        JSONObject jsonObject = null;
        if(m.find()){
            jsonObject = JSON.parseObject(m.group(0));
        }
        //检查是否需要验证码
        if(jsonObject.getInteger("showpin") != null && jsonObject.getInteger("showpin") == 1){
            weiBoLoginMes.setNeedValid(true);
            weiBoLoginMes.setCodeURL(AutoWeiBoSpiderConstant.WEIBO_CODEURL.replace("{p}",jsonObject.getString("pcid")));
            weiBoLoginMes.setPreLoginJsonObject(jsonObject);
        }else {
            //不需要，下一步
            afterPreLogin(jsonObject,weiBoLoginMes.getSu(),password);
        }

    }

    public void afterPreLogin(JSONObject jsonObject,String su,String password) throws UnsupportedEncodingException {
        String serverTime = jsonObject.getString("servertime");
        String nonce = jsonObject.getString("nonce");
        String pubkey = jsonObject.getString("pubkey");
        String rsakv = jsonObject.getString("rsakv");

        BigInteger pubKeyInteger = new BigInteger(pubkey,16);
        BigInteger padding = new BigInteger("10001",16);

        String mes = serverTime+'\t'+nonce+'\n'+password;

        String sp = encrypt(mes.getBytes(CHARSET_UTF8),getPublicKey(pubKeyInteger,padding));


        List postParam =  buildLoginParam(su,serverTime,nonce,sp,rsakv);

        Login(postParam);
    }

    //公钥加密
    public static String encrypt(byte[] content, PublicKey publicKey){
        Cipher cipher;//java默认"RSA"="RSA/ECB/PKCS1Padding"
        try {
            cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return parseByte2HexStr(cipher.doFinal(content));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PublicKey getPublicKey(BigInteger b1,BigInteger b2){
        //X509EncodedKeySpec keySpec=new X509EncodedKeySpec(publicKey);
        RSAPublicKeySpec rsaPubKS = new RSAPublicKeySpec(b1,b2);
        KeyFactory keyFactory= null;
        try {
            keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
            return keyFactory.generatePublic(rsaPubKS);
        } catch (Exception e) {
            throw new WbException(e);
        }
    }


    /**
     * 正式登陆
     * @param paramList
     */
    public void Login(List paramList) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(AutoWeiBoSpiderConstant.WEIBO_LOGINURL);
        post.setHeader(HttpConstant.Header.USER_AGENT, SpiderConstant.ChromeUserAgent);
        // 构建消息实体
        //url格式编码
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(paramList, CHARSET_UTF8);
        post.setEntity(uefEntity);

        try (CloseableHttpResponse respone = weiBoLoginMes.getWeiboHttpClient().execute(post)) {
            if (respone.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                post.abort();
            }
            HttpEntity responeEntity = respone.getEntity();
            String html = IOUtils.toString(responeEntity.getContent(), CHARSET_GBK);
            Matcher ma = urlPattern.matcher(html);
            String lastLoginUrl = null;
            if (ma.find()) {
                lastLoginUrl = ma.group(0);
            }
            if(lastLoginUrl!=null){
                lastLogin(lastLoginUrl);
            }else{
                //登录失败，获取失败信息
                Matcher failMa = failLoginReason.matcher(html);
                if(failMa.find()){
                    weiBoLoginMes.setFailReason(URLDecoder.decode(failMa.group(0),CHARSET_GBK));
                }
            }

        } catch (IOException e) {
            throw new WbException(e);
        }
    }

    public void lastLogin(String loginUrl){
        HttpGet get = new HttpGet(loginUrl);
        get.setHeader(HttpConstant.Header.USER_AGENT,SpiderConstant.ChromeUserAgent);
        try(CloseableHttpResponse respone = weiBoLoginMes.getWeiboHttpClient().execute(get)){
            if (respone.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                get.abort();
            }
            HttpEntity responeEntity = respone.getEntity();
            String html = IOUtils.toString(responeEntity.getContent(),CHARSET_GBK);
            Matcher ma = uniqueidPattern.matcher(html);
            if(ma.find()){
                weiBoLoginMes.setUid(ma.group(1));
                weiBoLoginMes.setProfileUrl(AutoWeiBoSpiderConstant.WEIBO_WEB_URL.replace("{uid}",weiBoLoginMes.getUid()));
            }else{
                weiBoLoginMes.setFailReason("WeiBoLoginService.lastLogin:"+html);
            }

        } catch (IOException e) {
            throw new WbException(e);
        }
    }


    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    private List<NameValuePair> buildLoginParam(String su, String servertime, String nonce, String sp, String rsakv){
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("gateway","1"));
        list.add(new BasicNameValuePair("from",""));
        list.add(new BasicNameValuePair("savestate","7"));
        list.add(new BasicNameValuePair("userticket","1"));
        list.add(new BasicNameValuePair("ssosimplelogin","1"));
        list.add(new BasicNameValuePair("vsnf","1"));
        list.add(new BasicNameValuePair("vsnval",""));
        list.add(new BasicNameValuePair("su",su));
        list.add(new BasicNameValuePair("service","miniblog"));
        list.add(new BasicNameValuePair("servertime",servertime));
        list.add(new BasicNameValuePair("nonce",nonce));
        list.add(new BasicNameValuePair("pwencode","rsa2"));
        list.add(new BasicNameValuePair("sp",sp));
        list.add(new BasicNameValuePair("encoding","UTF-8"));
        list.add(new BasicNameValuePair("url","https://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack"));
        list.add(new BasicNameValuePair("returntype","META"));
        list.add(new BasicNameValuePair("rsakv",rsakv));
        list.add(new BasicNameValuePair("sr","1440*900"));
        list.add(new BasicNameValuePair("prelt","493"));
        return list;
    }
}

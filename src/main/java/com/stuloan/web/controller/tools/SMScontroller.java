package com.stuloan.web.controller.tools;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Created by suyx on 2018/4/23 0023.
 * 发短信
 */
public class SMScontroller {
    public static void main(String[] args)throws Exception
    {

        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", "本站用户名"),new NameValuePair("Key", "接口安全秘钥"),new NameValuePair("smsMob","手机号码"),new NameValuePair("smsText","验证码：8888")};
        post.setRequestBody(data);

        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode);
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        System.out.println(result); //打印返回消息状态


        post.releaseConnection();


//        String paramdata = map.get("paramdata") + "";
//        Map<String, String> params = new HashMap<String, String>();
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//        params.put("qzurl", URLEncoder.encode(map.get("qzurl") + ""));
//        params.put("createid", map.get("createid") + "");
//        params.put("custocken", custocken);
//        params.put("dataid", map.get("dataid") + "");
//        params.put("paramdata", paramdata);
//        if(StringUtils.equals(paramdata,"0")){
//            params.put("routeway", AuthConfigData.QZ_BQ_YC_ROUTEWAY);
//        }else{
//            params.put("routeway", "");
//        }
//        ResultBase result = HttpRequest.doPost05(groupqzUrl, headers, params, "utf-8", "utf-8");

    }
}

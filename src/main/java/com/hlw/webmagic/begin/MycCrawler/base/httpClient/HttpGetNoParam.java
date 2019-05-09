package com.hlw.webmagic.begin.MycCrawler.base.httpClient;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @program: BookGrab
 * @description: 不带参数的httpClient请求
 * @author: houlongwang
 * @create: 2019-05-09 21:09
 **/

public class HttpGetNoParam {
    public static void main(String[] args) throws Exception {
        //1.创建浏览器
        CloseableHttpClient client=HttpClients.createDefault();
//        2.创建浏览器请求
        HttpGet httpGet=new HttpGet("http://www.baidu.com");
        //3.发送请求，处理返回值
        CloseableHttpResponse response=null;
        try {
            response=client.execute(httpGet);
            if ((200==response.getStatusLine().getStatusCode())) {
                System.out.println(response.getEntity());
                System.out.println(EntityUtils.toString(response.getEntity()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
    }
}



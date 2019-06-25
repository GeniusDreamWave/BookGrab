package com.hlw.webmagic.begin.beginFour;

import com.hlw.webmagic.begin.beginThree.helper.DownUtils;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: BookGrab
 * @description: 我的页面控制类
 * @author: houlongwang
 * @create: 2019-05-03 18:22
 **/
public class MyPageProcesser2 implements PageProcessor {

    private Site site = Site.me().setCharset("UTF-8").setSleepTime(100).setRetryTimes(3);

    public void process(Page page) {
        List<String> all = page
                .getHtml()
                .links()
                .regex("https://manhua\\.dmzj\\.com/\\w+/\\d+.shtml")
                .all();
        System.out.println(all);
        Iterator<String> iterator = all.iterator();
        if (iterator.hasNext()) {
            String url=iterator.next();
            System.out.println("处理前 ："+url);
            url=url+"#@page=1";
            System.out.println("处理后 ："+url);
            iterator.remove();
            all.add(url);
        }
        String testText = page
                .getHtml()
                .css("body > div:nth-child(7) > div.display_middle")
                .toString();
        System.out.println("text:"+testText);
        //将所有的页面的加入队列
        page.addTargetRequests(all);
        String imgSrc = page
                .getHtml()
                .xpath("//*[@id=\"center_box\"]")
                .toString();
        System.out.println("漫画下载地址："+imgSrc);
        String imgUrl = page
                .getHtml()
                .xpath("/html/body/div[4]/div[3]/div/div[1]/div[2]/div/div[4]/table/tbody/tr[1]/td")
                .toString();
        System.out.println("文章作者："+imgUrl);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MyPageProcesser2())
                .addPipeline(new ConsolePipeline())
                .addUrl("https://manhua.dmzj.com/sxzyys")
                .run();
    }
}



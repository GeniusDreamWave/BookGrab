package com.hlw.webmagic.begin.MyComic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-05-08 23:04
 **/
public class MyComicProcesser implements PageProcessor {
    private Site site = Site.me()
            .setCharset("UTF-8")
            .setSleepTime(3)
            .setRetryTimes(3);

    public void process(Page page) {
//        /xieemanhua/2016080234414.html

        String location = page.getHtml()
                .xpath("//*[@id=\"tc1\"]")
                .toString();
        System.out.println(location);
        if (location!=null&&location!=""){
            List<String> pageLink = page.getHtml()
                    .regex("/xieemanhua/2016080234414\\.html")
                    .all();
            page.addTargetRequests(pageLink);
        }else {
            String imgUrl = page.getHtml()
                    .css(".tcontent > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1)")
                    .css("img", "src")
                    .toString();
            if (null!=imgUrl){
                page.putField("imgUrl",imgUrl);
            }
            String nextLink = page.getHtml()
                    .css(".pn_r")
                    .regex("/xieemanhua/\\d+\\.html")
                    .toString();
            System.out.println("xiayieg"+nextLink);
            if (nextLink!=null){
                page.addTargetRequest(nextLink);
            }
        }


    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MyComicProcesser())
                .addPipeline(new ConsolePipeline())
                .addPipeline(new MyComicPipeline())
                .addUrl("https://www.youquba.net/xieemanhua/")
                .run();
    }
}



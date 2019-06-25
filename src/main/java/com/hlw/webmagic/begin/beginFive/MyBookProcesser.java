package com.hlw.webmagic.begin.beginFive;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: BookGrab
 * @description: 我的书籍处理器
 * @author: houlongwang
 * @create: 2019-05-04 19:30
 **/
public class MyBookProcesser implements PageProcessor {
    private Site site = Site.me().setDomain("http://www.snjh216.com/qingchun/").setSleepTime(1);
//    private List<BookModelDo> modelDoList=new CopyOnWriteArrayList<BookModelDo>();
    public void process(Page page) {
        List<String> all = page.getHtml()
                .links()
                .regex("https://m\\.shubaoq\\.com/info-\\d+/")
                .all();
        System.out.println("所有匹配到的链接"+all);
        page.addTargetRequests(all);
//        BookModelDo bookModelDo=new BookModelDo();
        String storyName = page.getHtml()
                .xpath("/html/body/div[4]/div[1]/div[2]/h2/a/text()")
                .toString();
        System.out.println("小说名称："+storyName);
//        bookModelDo.setStoryName(storyName);
        page.putField("storyName",storyName);
        String storyWriter= page.getHtml()
                .xpath("/html/body/div[4]/div[1]/div[2]/p[3]/a/text()")
                .toString();
        System.out.println("小说作者："+storyWriter);
        page.putField("storyWriter",storyWriter);
        List<String> storySectionList=page.getHtml()
                .css("body > div.cover > ul > li > a")
                .all();
        System.out.println("小说总章节："+storySectionList);
        page.putField("storySectionList",storySectionList);

        /*
        * 小说每个章节名称
        * */
        String storySectionOne=page.getHtml()
                .xpath("//*[@id=\"nr_title\"]/text()")
                .toString();
        System.out.println("小说章节："+storySectionOne);
        page.putField("storySectionOne",storySectionOne);

        List<String> storySectionLink= page.getHtml()
                .css("body > div.cover > ul > li > a")
                .css("a","href")
                .all();
        System.out.println("小说链接："+storySectionLink);

        page.addTargetRequests(storySectionLink);
        String storyContextName=page
                .getHtml()
                .xpath("//*[@id=\"_52mb_h1\"]/text()")
                .toString();
        System.out.println("小说名称"+storyContextName);
        page.putField("storyContextName",storyContextName);

        String str=page.getHtml()
                .xpath("//*[@id=\"nr1\"]/text()")
                .toString();
        System.out.println("文章内容："+str);
        page.putField("str",str);
        /**
         * 获取下一页的链接
         */
        String nextPageLink=page.getHtml()
                .xpath("//*[@id=\"pb_next\"]")
                .css("a","href")
                .toString();
        System.out.println("下一页的链接："+nextPageLink);
        page.addTargetRequest(nextPageLink);
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MyBookProcesser())
                .addPipeline(new MyBookPipeline2())
                .addUrl("https://m.shubaoq.com/")
                .run();
    }
}



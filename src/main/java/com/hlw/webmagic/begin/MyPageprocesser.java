package com.hlw.webmagic.begin;

import com.hlw.util.HtmlFilterUtil;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-05-02 00:20
 **/
@Slf4j
public class MyPageprocesser implements PageProcessor {

    //*[@id="list"]

    private Site site = Site.me().setCharset("UTF-8").setSleepTime(100).setRetryTimes(3);


    public void process(Page page) {
//        http://www.xbiquge.la/10/10489/
        List<String> links = page.getHtml().links()
                .regex("http://www\\.xbiquge\\.la/\\d+").all();
        page.addTargetRequests(links);
        page.putField("list", HtmlFilterUtil.filterHtml(page.getHtml().xpath("//*[@id=\"list\"]").toString()));
        page.putField("作者",HtmlFilterUtil.filterHtml(page.getHtml().xpath("//*[@id=\"info\"]/p[1]").toString()));
        page.putField("zuozhe",HtmlFilterUtil.filterHtml(page.getHtml().xpath("//*[@id=\"info\"]/h1").toString()) );
    }


    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        log.info("世界的位置开始：{}","begin");
        Spider.create(new MyPageprocesser())
                .addUrl("http://www.xbiquge.la/10/10489/")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new MyPipeline())
                .run();
    }
}





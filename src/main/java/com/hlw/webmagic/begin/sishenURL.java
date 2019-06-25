package com.hlw.webmagic.begin;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-06-01 14:05
 **/
@Slf4j
public class sishenURL implements PageProcessor {
    //*[@id="list"]

    private Site site = Site
            .me()
            .setCharset("UTF-8")
            .setSleepTime(100)
            .setRetryTimes(3);

    public void process(Page page) {
        String string = page.getHtml().toString();
        System.out.println("该页面：{"+string+"}");
        List<String> all = page
                .getHtml()
                .css("body > div.wrapper > div.page_content > div.anime-videodown > div.downlist > ul")
                /*.css("li:nth-child(2) > div.adds > input")*/
                .css("input", "value")
                .all();
        Set<String> set = new HashSet<String>(all);
        log.info("查找到参数:{}",all);
        log.info("查找到实用参数:{}",set.size());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new sishenURL())
                .addUrl("http://www.dm432.com/JapanCartoon/sishen/")
                .run();
    }
}



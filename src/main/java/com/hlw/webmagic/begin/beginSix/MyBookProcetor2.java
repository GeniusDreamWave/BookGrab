package com.hlw.webmagic.begin.beginSix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-05-05 12:38
 **/
public class MyBookProcetor2 implements PageProcessor {
    private Site site = Site.me().setDomain("https://www.dmzj.com").setSleepTime(1);
    // 第一步： 设置chromedriver地址。一定要指定驱动的位置。

    public void process(Page page) {
        System.out.println("进程运行");
        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        // 第二步：初始化驱动
        WebDriver driver = new ChromeDriver();
        System.out.println("页面获取"+driver);
        driver.get("https://www.dmzj.com/view/huoguojiazudiyiji/88908.html#@page=1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement webElement = driver.findElement(By
                .cssSelector("body > div.comic_wraCon.autoHeight"));
//        WebElement webElement = driver.findElement(By.xpath("//div[@class='table-responsive sse_table_T05']"));
        String str = webElement.getAttribute("outerHTML");
        System.out.println(str);

        Html html = new Html(str);
        System.out.println(html
                .css("div.comic_wraCon.autoHeight > img")
                .css("img","src")
                .toString());
        /*String companyCode = html.xpath("//tbody/tr[1]/td/text()").get();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = html.xpath("//tbody/tr[3]/td/text()").get().split("/")[0];

        String stockCode = html.xpath("//tbody/tr[2]/td/text()").get().split("/")[0];
        String name = html.xpath("//tbody/tr[5]/td/text()").get().split("/")[0];
        String department = html.xpath("//tbody/tr[14]/td/text()").get().split("/")[0];
        System.out.println(companyCode);
        System.out.println(stockCode);
        System.out.println(name);
        System.out.println(department);
        driver.close();*/
        driver.close();
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MyBookProcetor2())
                .addPipeline(new ConsolePipeline())
                .addUrl("https://www.dmzj.com/view/huoguojiazudiyiji/88908.html#@page=1")
                .run();
    }
}



package com.hlw.webmagic.begin.beginSix;

import com.hlw.webmagic.begin.beginFive.MyBookPipeline2;
import com.hlw.webmagic.begin.beginFive.MyBookProcesser;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @program: BookGrab
 * @description: 页面书籍抓取
 * @author: houlongwang
 * @create: 2019-05-05 11:59
 **/
public class MyBookProccetor implements PageProcessor {
    private Site site = Site.me().setDomain("https://www.dmzj.com").setSleepTime(1);
    // 第一步： 设置chromedriver地址。一定要指定驱动的位置。

    public void process(Page page) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        // 第二步：初始化驱动
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.dmzj.com/view/huoguojiazudiyiji/88908.html#@page=1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement webElement = driver.findElement(By.cssSelector("body > div.comic_wraCon.autoHeight > img"));
//        WebElement webElement = driver.findElement(By.xpath("//div[@class='table-responsive sse_table_T05']"));
        String str = webElement.getAttribute("outerHTML");
        System.out.println(str);

        Html html = new Html(str);
        System.out.println(html
                .css("body > div.comic_wraCon.autoHeight > img")
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
        Spider spider = Spider.create(new MyBookProccetor())
                .addPipeline(new ConsolePipeline());
        if (spider==null){
            System.out.println("fuck");
        }
        spider.addUrl("https://www.dmzj.com/view/huoguojiazudiyiji/88908.html#@page=1")
                .run();
//        // 第三步：获取目标网页
//        driver.get("http://blog.csdn.net/wgyscsf/article/details/52835845");
//        // 第四步：解析。以下就可以进行解了。使用webMagic、jsoup等进行必要的解析。
//        System.out.println("Page title is: " + driver.getTitle());
//        System.out.println("Page title is: " + driver.getPageSource());

    }
}



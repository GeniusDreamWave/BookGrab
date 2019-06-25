package com.hlw.webmagic.begin.beginSeven;

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

import java.util.List;

/**
 * @program: BookGrab
 * @description: 我的页面连接提取中心
 * @author: houlongwang
 * @create: 2019-05-05 12:55
 **/
public class MyPageLinkProcesser implements PageProcessor {
    private Site site = Site.me().setDomain("https://www.dmzj.com").setSleepTime(200);
    // 第一步： 设置chromedriver地址。一定要指定驱动的位置。

    public void process(Page page) {
//        https://www.dmzj.com/info/maotouyingshuibuzhuoyouzhongniguolaiya.html
        List<String> all = page.getHtml()
                .regex("https://www\\.dmzj\\.com/info/\\w+\\.html")
                .all();
        page.addTargetRequests(all);
        String begin = page.getHtml()
                .css("a", "href")
                .regex("第\\d话")
                .toString();

        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        // 第二步：初始化驱动
        WebDriver driver = new ChromeDriver();
        driver.get(begin);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String otherpage = driver.findElement(By.cssSelector("body > div.btmBtnBox")).getAttribute("outerHTML");
        WebElement webElement = driver.findElement(By.cssSelector("body > div.comic_wraCon.autoHeight"));
//        WebElement webElement = driver.findElement(By.xpath("//div[@class='table-responsive sse_table_T05']"));
        String str = webElement.getAttribute("outerHTML");
        System.out.println("其他图片"+otherpage);
        System.out.println(str);

        Html html = new Html(str);
        String imgOne = html
                .css("body > div.comic_wraCon.autoHeight > img")
                .css("img", "src")
                .toString();
        System.out.println(imgOne);
        page.putField("imgOne",imgOne);

        List<String> all1 = new Html(otherpage).css("#page_select")
                .css("option", "value")
                .all();
        String nextpageLink = new Html(otherpage).css("div.btmBtnBox > a")
                .css("a", "href")
                .toString();
        System.out.println("下一章的连接"+nextpageLink);
        driver.get(nextpageLink);
        System.out.println(all1);
        page.putField("other",all1);
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
        Spider.create(new MyPageLinkProcesser())
                .addPipeline(new ConsolePipeline())
                .addUrl("https://www.dmzj.com")
                .run();
    }
}



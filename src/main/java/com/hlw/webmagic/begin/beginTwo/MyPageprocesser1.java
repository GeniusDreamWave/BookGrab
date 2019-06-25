package com.hlw.webmagic.begin.beginTwo;

import com.hlw.util.HtmlFilterUtil;
import com.hlw.webmagic.begin.MyPipeline;
import com.hlw.webmagic.begin.beginTwo.helper.DownUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;
import java.util.List;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-05-02 00:20
 **/
@Slf4j
public class MyPageprocesser1 implements PageProcessor {


    //*[@id="list"]

    private Site site = Site.me().setCharset("UTF-8").setSleepTime(100).setRetryTimes(3);


    public void process(Page page) {
        List<String> links = page.getHtml().links()
//        https://manhua.dmzj.com/sxzyys
                .regex("/sxzyys/\\d+\\.shtml").all();
        System.out.println("匹配到的链接："+links);
        page.addTargetRequests(links);
        String txtName=page
                .getHtml()
                .xpath("/html/body/div[10]/img")
                .css("img","src")
                .toString();
        System.out.println("请求图片的url："+txtName);
//        Boolean result=DownUtils.downloadPicture(txtName,"D:\\studyProject\\BookGrab\\src\\main\\resources\\img\\first.jpg");
        page
                .putField(
                        "img",txtName
                );
    }


    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        log.info("世界的位置开始：{}", "begin");
        Spider.create(new MyPageprocesser1())
                .addUrl("https://manhua.dmzj.com/sxzyys")
                .addPipeline(new MyPipeline()).run();
    }
}





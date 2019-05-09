package com.hlw.webmagic.begin.beginThree;

import com.hlw.webmagic.begin.MyPipeline;
import com.hlw.webmagic.begin.beginThree.helper.DownUtils;
import com.hlw.webmagic.begin.beginTwo.MyPipeline1;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

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
//        http://www.xbiquge.la/10/10489/
        List<String> links = page.getHtml().links()
                .regex("https://\\.manhua\\.dmzj\\.com/\\d+").all();
        page.addTargetRequests(links);
        String imgurl=page
                .getHtml()
                .xpath("//*[@id=\"fmimg\"]")
                .css("img", "src").toString();
        System.out.println("请求图片的url："+imgurl);
        Boolean result= DownUtils.downloadPicture(imgurl,"D:\\studyProject\\BookGrab\\src\\main\\resources\\img\\first.jpg");
        page
                .putField(
                        "img",result
                );
    }


    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        log.info("世界的位置开始：{}", "begin");
        Spider
                .create(new MyPageprocesser1())
                .addUrl("http://www.xbiquge.la/10/10489/")
                .addPipeline(new MyPipeline1()).run();
    }
}





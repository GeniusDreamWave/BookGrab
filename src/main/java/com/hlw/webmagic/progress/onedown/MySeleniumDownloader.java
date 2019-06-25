package com.hlw.webmagic.progress.onedown;

import com.hlw.webmagic.begin.beginSix.MyBookProcetor2;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-05-06 21:18
 **/
public class MySeleniumDownloader  {
    public static void main(String[] args) {
        SeleniumDownloader seleniumDownloader1 = new SeleniumDownloader("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        Spider.create(new MyBookProcetor2())
                .setDownloader(seleniumDownloader1)
                .addUrl("www.baidu.com")
                .run();
    }
}
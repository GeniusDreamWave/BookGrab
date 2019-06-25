package com.hlw.webmagic.begin.begineight;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;

/**
 * @program: BookGrab
 * @description: 我的模拟下载中心
 * @author: houlongwang
 * @create: 2019-05-06 20:22
 **/
public class MyMockDownloader implements Downloader {

    public Page download(Request request, Task task) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        // 第二步：初始化驱动
        WebDriver driver = new ChromeDriver();
        driver.get(request.getUrl());
        return null;
    }

    public void setThread(int i) {

    }
}



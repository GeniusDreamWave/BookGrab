package com.hlw.webmagic.begin.beginSix;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @program: BookGrab
 * @description: 模拟浏览器拿去数据
 * @author: houlongwang
 * @create: 2019-05-05 12:46
 **/
public class mockBrowsor {
    public static void main(String[] args) {
        // 第一步： 设置chromedriver地址。一定要指定驱动的位置。
        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        // 第二步：初始化驱动
        WebDriver driver = new ChromeDriver();

    }
}



package com.hlw.webmagic.progress.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * @program: BookGrab
 * @description: 火狐浏览器工具
 * @author: houlongwang
 * @create: 2019-05-07 12:37
 **/
public class FireFoxToolBegin {
    public static void main(String[] args) {
        WebDriver driver;   //声明WebDriver
        System.setProperty("webdriver.firefox.marionette", "D:\\UsuallySoft\\firefox\\firefox.exe");
        //指定Firefox浏览器的路径
        String Url = "https://www.baidu.com";   //百度的地址
        driver =new FirefoxDriver();        //new一个FirefoxDriver()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);    //设置隐式等待10秒钟
        driver.get(Url);    //打开百度首页
        driver.manage().window().maximize();    //把浏览器窗口最大化

        try {
            Thread.sleep(3000);     //让线程等待3秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();  //退出driver
    }
}



package com.hlw.webmagic.begin.beginThree;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * @program: BookGrab
 * @description: 漫画页面模型
 * @author: houlongwang
 * @create: 2019-05-03 17:37
 **/
@Data
@TargetUrl(value = "https://manhua\\.dmzj\\.com/\\d")
public class CartoonPageModel {
    /**
     * 漫画名称
     */
    @ExtractBy("/html/body/div[5]/div[2]/div[1]/div[1]/div[2]/span[1]/a/h1/text()")
    private String cartoonName;
    /**
     * 漫画作者
     */
    @ExtractBy("/html/body/div[5]/div[3]/div/div[1]/div[2]/div/div[4]/table/tbody/tr[3]/td/a/text()")
    private String cartoonWriter;
    /**
     * 漫画书
     */
    private List<CartoonBook> cartoonBook;
}



package com.hlw.webmagic.begin.beginThree;

import us.codecraft.webmagic.model.annotation.ExtractBy;

import java.util.List;

/**
 * @program: BookGrab
 * @description: 漫画书
 * @author: houlongwang
 * @create: 2019-05-03 18:09
 **/
public class CartoonBook {
    /**
     * 漫画版本
     */
    @ExtractBy("/html/body/div[5]/div[2]/div[1]/div[2]/div/h2/text()")
    private String cartoonVersion;
    /**
     * 漫画内容
     */
    private List<CartoonContext> cartoonContext;
}



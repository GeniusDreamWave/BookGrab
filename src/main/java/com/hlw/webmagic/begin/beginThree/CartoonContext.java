package com.hlw.webmagic.begin.beginThree;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * @program: BookGrab
 * @description:漫画类型
 * @author: houlongwang
 * @create: 2019-05-03 17:41
 **/
@Data
public class CartoonContext {
    /**
     * 章节名称
     */
    @ExtractBy("/html/body/div[5]/div[2]/div[1]/div[4]/ul/li[1]/a")
    private String sectionName;
    /**
     * 图片列表
     */
    @ExtractBy(value = "/html/body/div[5]/div[2]/div[1]/div[2]/div/h2/text()",multi = true)
    private List<String> imgList;
}



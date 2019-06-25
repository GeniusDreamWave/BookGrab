package com.hlw.webmagic.myTest.model;

import lombok.Data;
import lombok.Setter;

/**
 * @program: BookGrab
 * @description: 文本主体
 * @author: houlongwang
 * @create: 2019-05-03 00:19
 **/
@Data
public class TextDomainResp {
    /**
     * 章节
     */
    private String chacper;
    /**
     * 每章名称
     */
    private String smallTheme;
    /**
     * 每章内容
     */
    private String storyContext;
}



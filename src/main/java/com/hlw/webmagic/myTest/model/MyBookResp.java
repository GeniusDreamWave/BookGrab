package com.hlw.webmagic.myTest.model;

import java.util.List;

/**
 * @program: BookGrab
 * @description: 书籍对象
 * @author: houlongwang
 * @create: 2019-05-03 00:16
 **/
public class MyBookResp {
    /**
     * 作者
     */
    private String writer;
    /**
     * 书籍内容
     */
    private List<TextDomainResp> content;
}
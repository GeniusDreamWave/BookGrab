package com.hlw.webmagic.begin.beginFive;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: BookGrab
 * @description: 书籍模型
 * @author: houlongwang
 * @create: 2019-05-04 20:39
 **/
@Data
public class BookModelDo {
    /**
     * 小说名称
     */
    private String storyName;
    /**
     * 小说作者
     */
    private String storeWriter;
    /**
     * 章节列表
     */
    private ConcurrentHashMap<String,List<String>> storySectionList;
}



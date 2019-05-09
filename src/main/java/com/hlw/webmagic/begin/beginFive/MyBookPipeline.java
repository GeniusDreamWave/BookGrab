package com.hlw.webmagic.begin.beginFive;

import org.apache.commons.collections.list.CursorableLinkedList;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: BookGrab
 * @description: 我的书籍文件解析处理 单本书籍的处理
 * @author: houlongwang
 * @create: 2019-05-04 20:38
 **/
public class MyBookPipeline implements Pipeline {
    /**
     * 存放书籍的列表
     */
    private List<BookModelDo> bookModelDos=new CursorableLinkedList();
    /**
     * 存放章节的列表
     */
    private List<ConcurrentHashMap<String,List<String>>> listConcurrentHashMap=new ArrayList<ConcurrentHashMap<String, List<String>>>();

    public BookModelDo bookModelDo=new BookModelDo();
    ConcurrentHashMap<String,List<String>> list=new ConcurrentHashMap<String,List<String>>();

    public void process(ResultItems resultItems, Task task) {
        System.out.println("开始");
        String storyName=resultItems.get("storyName");
        String storyWriter=resultItems.get("storyWriter");
        String storySectionOne=resultItems.get("storySectionOne");
//        List<String> list=resultItems.get("storySectionList");
        String storyContextName=resultItems.get("storyContextName");
        String str=resultItems.get("str");
//        System.out.println(
//                "++++"+storyName+
//                "++++"+storyWriter+
//                "++++"+storySectionOne+
//                "++++"+storyContextName+
//                "++++"+str
//        );
//        create(storyName,storyWriter);
//        getSection(storySectionOne,storyContextName,str);
//        System.out.println("书籍内容"+bookModelDos);
//        System.out.println("章节内容"+listConcurrentHashMap);

        if ("欲成欢 第一部全集 第三书包网".equals(storyName)){
            bookModelDo.setStoryName(storyName);
            bookModelDo.setStoreWriter(storyWriter);
        }
        if (null==storyWriter&bookModelDo.getStoryName().equals(storyContextName)){
            if (list.contains(storySectionOne)){
                list.get(storySectionOne).add(str);
            }else {
                List strlist=new ArrayList<String>();
                strlist.add(str);
                list.put(storySectionOne,strlist);
                bookModelDo.setStorySectionList(list);
            }
        }
        System.out.println("欲承欢："+bookModelDo);
        if (bookModelDo.getStorySectionList()!=null) {
            System.out.println("list的长度："+bookModelDo.getStorySectionList().size());
        }
    }

    /**
     * 创建小说
     * @param storeName
     */
    private void create(String storeName,String storyWriter){
        BookModelDo bookModelDo=null;
        if (storeName!=null){
            bookModelDo=new BookModelDo();
            bookModelDo.setStoryName(storeName);
            bookModelDo.setStoreWriter(storyWriter);
            bookModelDos.add(bookModelDo);
        }
    }

    /**
     * 第二章页面，小说名称，小说章节，小说内容
     * @param storySectionOne
     * @param storyContextName
     * @param str
     * @return
     */
    private void getSection(String storySectionOne,String storyContextName,String str) {
        if (storySectionOne != null & null != str & null != storyContextName) {
            for (BookModelDo bookModelDo : bookModelDos) {
                if (bookModelDo.getStoryName() == storyContextName) {
                    System.out.println("遍历进入");
                    ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<String, List<String>>();
                    for (ConcurrentHashMap<String, List<String>> hashMap : listConcurrentHashMap) {
                        if (hashMap.contains(storySectionOne)) {
                            List<String> list=hashMap.get(storySectionOne);
                            list.add(str);
                        }
                        return;
                    }
                    List list=new CursorableLinkedList();
                    list.add(str);
                    map.put(storySectionOne, list);
                    listConcurrentHashMap.add(map);
                    bookModelDo.setStorySectionList(map);
                }
            }
        }
    }
}



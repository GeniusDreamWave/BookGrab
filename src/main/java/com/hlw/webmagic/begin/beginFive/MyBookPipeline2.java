package com.hlw.webmagic.begin.beginFive;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.apache.commons.collections.list.CursorableLinkedList;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: BookGrab
 * @description: 我的书籍文件解析处理 多本书
 * @author: houlongwang
 * @create: 2019-05-04 20:38
 **/
public class MyBookPipeline2 implements Pipeline {
    /**
     * 存放书籍的列表
     */
    private List<BookModelDo> bookModelDos=new CursorableLinkedList();
    /**
     * 存放章节的列表
     */
    private ConcurrentHashMap<String,ConcurrentHashMap<String,List<String>>> listConcurrentHashMap=new ConcurrentHashMap<String,ConcurrentHashMap<String,List<String>>>();

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

        if (null!=storyName){
            BookModelDo bookModelDo1=new BookModelDo();
            bookModelDo1.setStoryName(storyName);
            bookModelDo1.setStoreWriter(storyWriter);
            bookModelDos.add(bookModelDo1);
        }
        for (BookModelDo modelDo : bookModelDos) {
            if (modelDo.getStoryName().equals(storyContextName)){
                if (null==storyWriter){
                    if (modelDo.getStorySectionList()==null){
                        System.out.println("进入+++");
                        ConcurrentHashMap map=new ConcurrentHashMap();
                        List list=new ArrayList<String>();
                        list.add(str);
                        map.put(storySectionOne,list);
                        modelDo.setStorySectionList(map);
                    }else {
                        System.out.println("进入----");
                        if (modelDo.getStorySectionList().contains(storySectionOne)){
                            modelDo.getStorySectionList().get(storySectionOne).add(str);
                        }else {
                            List list=new ArrayList<String>();
                            list.add(str);
                            modelDo.getStorySectionList().put(storySectionOne,list);
                        }
                    }
                }
            }
            System.out.println("实际对象："+modelDo);
            if (modelDo.getStorySectionList()!=null){
                System.out.println("实际数量"+modelDo.getStorySectionList().size());
                for (Map.Entry<String, List<String>> stringListEntry : modelDo.getStorySectionList().entrySet()) {
                    System.out.println("每章长度："+stringListEntry.getValue().size());
                }
                saveBook(modelDo);
            }
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

    private void saveBook(BookModelDo bookModelDo){
        String str="";
        str=str+bookModelDo.getStoryName()+bookModelDo.getStoreWriter();
        for (Map.Entry<String, List<String>> entry : bookModelDo.getStorySectionList().entrySet()) {
            str=str+entry.getKey();
            for (String s : entry.getValue()) {
                str=str+entry.getKey()+s;
            }
        }
        System.out.println("该书籍内容"+str);
        writeFileToSave(str,bookModelDo.getStoryName());
    }
    /**
     * 字符流保存数据
     * @param str
     * @param storyName
     */
    public void writeFileToSave(String str,String storyName){
        File file = new File("D:\\studyProject\\BookGrab\\src\\main\\resources\\"+storyName+".txt");
        FileWriter fw=null;
        BufferedWriter bw=null;
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            fw=new FileWriter(file.getAbsoluteFile(),false);
            bw=new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
    /**
     * 第二章页面，小说名称，小说章节，小说内容
     * @param storySectionOne
     * @param storyContextName
     * @param str
     * @return
     */
//    private void getSection(String storySectionOne,String storyContextName,String str) {
//        if (storySectionOne != null & null != str & null != storyContextName) {
//            for (BookModelDo bookModelDo : bookModelDos) {
//                if (bookModelDo.getStoryName() == storyContextName) {
//                    System.out.println("遍历进入");
//                    ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<String, List<String>>();
//                    for (ConcurrentHashMap<String, List<String>> hashMap : listConcurrentHashMap) {
//                        if (hashMap.contains(storySectionOne)) {
//                            List<String> list=hashMap.get(storySectionOne);
//                            list.add(str);
//                        }
//                        return;
//                    }
//                    List list=new CursorableLinkedList();
//                    list.add(str);
//                    map.put(storySectionOne, list);
////                    listConcurrentHashMap.add(map);
//                    bookModelDo.setStorySectionList(map);
//                }
//            }
//        }
//    }
}



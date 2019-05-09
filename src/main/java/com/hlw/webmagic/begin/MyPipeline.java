package com.hlw.webmagic.begin;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;
import java.util.Map;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-05-02 00:48
 **/
public class MyPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        System.out.println("task:{"+task+"}");
        System.out.println("站点是：" + task.getSite());
        System.out.println("uuid是" + task.getUUID());
        System.out.println("resultItems:{" + resultItems + "}");
        Map<String, Object> all = resultItems.getAll();
        for (Map.Entry<String, Object> entry : all.entrySet()) {
            if ("list".equals(entry.getKey())){
                String list = (String) entry.getValue();
                String text2=list.replaceAll("[<br>]{0,}","").replaceAll("(?m)^\\s*$(\\n|\\r\\n)", "   ");
                resultItems.put("list",text2);
            }
            if ("作者".equals(entry.getKey())){
                String s = entry.getValue().toString().replace("&nbsp;", "");
                resultItems.put("作者",s);
            }
            writeFileToSave("key:" + entry.getKey() + "valuse:" + entry.getValue());
            System.out.println("key:" + entry.getKey() + "valuse:" + entry.getValue());
        }
    }


    /***
     * delete CRLF; delete  empty line ;delete blank lines
     *
     * @param input
     * @return
     */
    private static String deleteCRLFOnce(String input) {

        return input.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");

    }

    /**
     * delete CRLF; delete  empty line ;delete blank lines
     *
     * @param input
     * @return
     */
    public static String deleteCRLF(String input) {
        input=deleteCRLFOnce(input);
        return deleteCRLFOnce(input);
    }
    public void writeFileToSave(String str){
        File file = new File("D:\\studyProject\\BookGrab\\src\\main\\resources\\my.txt");
        FileWriter fw=null;
        BufferedWriter bw=null;
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            fw=new FileWriter(file.getAbsoluteFile(),true);
            bw=new BufferedWriter(fw);
            bw.write(str);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}



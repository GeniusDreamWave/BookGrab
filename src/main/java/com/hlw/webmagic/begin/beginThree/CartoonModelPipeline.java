package com.hlw.webmagic.begin.beginThree;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

/**
 * @program: BookGrab
 * @description: 漫画模型处理
 * @author: houlongwang
 * @create: 2019-05-03 17:35
 **/
public class CartoonModelPipeline implements PageModelPipeline<CartoonPageModel> {

    public void process(CartoonPageModel cartoonPageModel, Task task) {
        System.out.println("爬取的结果："+cartoonPageModel);
    }
}



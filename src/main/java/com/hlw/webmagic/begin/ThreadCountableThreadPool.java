package com.hlw.webmagic.begin;

import us.codecraft.webmagic.thread.CountableThreadPool;

/**
 * @program: BookGrab
 * @description:
 * @author: houlongwang
 * @create: 2019-05-12 01:00
 **/
public class ThreadCountableThreadPool  {
    public static void main(String[] args) {
        CountableThreadPool countableThreadPool = new CountableThreadPool(5);
//        countableThreadPool.execute();
    }
}



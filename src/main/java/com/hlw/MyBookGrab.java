package com.hlw;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * @program: BookGrab
 * @description: 数据爬取内容
 * @author: houlongwang
 * @create: 2019-04-30 17:30
 **/
@Gecco(matchUrl="https://github.com/{user}/{project}", pipelines="consolePipeline")
public class MyBookGrab implements HtmlBean {
    private static final long serialVersionUID = -7127412585200687225L;

    @RequestParameter("user")
    private String user;

    @RequestParameter("project")
    private String project;

    @Text
    @HtmlField(cssPath=".repository-meta-content")
    private String title;

    @Text
    @HtmlField(cssPath=".pagehead-actions li:nth-child(2) .social-count")
    private int star;

    @Text
    @HtmlField(cssPath=".pagehead-actions li:nth-child(3) .social-count")
    private int fork;

    @Html
    @HtmlField(cssPath=".entry-content")
    private String readme;

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getFork() {
        return fork;
    }

    public void setFork(int fork) {
        this.fork = fork;
    }

    public static void main(String[] args) {
        GeccoEngine.create()
                .classpath("com.hlw.MyBookGrab")
                .start("https://github.com/xtuhcy/gecco")
                .thread(1)
                .interval(2000)
                .loop(true)
                .mobile(false)
                .start();
    }
}



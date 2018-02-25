package com.sapling.spiderMans.douban;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
@Service
public class DoubanSpriders {
    public static void main(String[] args) {
        List<HttpRequest> urls=new ArrayList<HttpRequest>();
        String baseUrl="https://www.douban.com/people/leftfm/";
        for (int i = 0; i < 1; i++) {
            HttpGetRequest url=new HttpGetRequest(baseUrl+i);
            urls.add(url);
        }
        GeccoEngine.create()
        //开始抓取的页面地址
        .start(baseUrl)
        .classpath("com.sapling.spiderMans")
        //开启几个爬虫线程
        .thread(1)
        //单个爬虫每次抓取完一个请求后的间隔时间
        .interval(2000)
        .start();
    }
}

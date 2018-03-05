package com.sapling.spiderMans.service.douban.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.google.common.collect.Lists;
import com.sapling.spiderMans.dao.DoubanUserDao;
import com.sapling.spiderMans.pojo.common.ResultBean;
import com.sapling.spiderMans.pojo.douban.DoubanUser;
import com.sapling.spiderMans.pojo.douban.DoubanUserInfo;
import com.sapling.spiderMans.service.douban.DoubanSpiderService;
import com.sapling.spiderMans.util.CommonFactory;
import com.sapling.spiderMans.util.PubThreadPoolExecutor;
import com.sapling.spiderMans.util.Util;

@Service
public class DoubanSpoiderServiceImpl implements DoubanSpiderService {
    protected final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private DoubanUserDao doubanUserDao;
    //    @Autowired
    //    private AmqpTemplate amqpTemplate;

    // @Autowired
    // private DoubanUserInfoDao doubanUserInfoDao;
    /**
     * 抓取用户信息
     */
    @Override
    public void doubanBegin() {
        List<HttpRequest> urls = new ArrayList<HttpRequest>();
        String baseUrl = "https://www.douban.com/group/blabla/members?start=";
        for (int i = 0; i < 17270; i++) {
            HttpGetRequest url = new HttpGetRequest(baseUrl + i * 35);
            urls.add(url);
        }
        GeccoEngine.create().classpath("com.sapling.spiderMans")
        // 开始抓取的页面地址
        .start(urls)
        // 开启几个爬虫线程
        .thread(5)
        // 单个爬虫每次抓取完一个请求后的间隔时间
        .interval(5000).start();
    }

    @Override
    public void doubanNotesBegin() {
        long total = doubanUserDao.countAll();
        if (total == 0) {
            logger.info("no log");
            return;
        }
        DoubanUserInfo doubanUserInfo = new DoubanUserInfo();
        int times = (int) (total / 10000l);
        doubanUserInfo.setLimit(10000);
        // List<HttpRequest> noteUrls=new ArrayList<HttpRequest>();
        // List<HttpRequest> albumUrls=new ArrayList<HttpRequest>();
        // List<HttpRequest> broadcastUrls=new ArrayList<HttpRequest>();
        // List<HttpRequest> workUrls=new ArrayList<HttpRequest>();
        // List<HttpRequest> likeUrls=new ArrayList<HttpRequest>();
        // List<HttpRequest> doulieAllUrls=new ArrayList<HttpRequest>();
        // List<HttpRequest> doulieCollections=new ArrayList<HttpRequest>();
        List<HttpRequest> mainUrls = new ArrayList<>();
        List<List<HttpRequest>> lists = new ArrayList<>();
        // Map<String, List<HttpRequest>> map=new HashMap<>();
        for (int i = 0; i < times + 1; i++) {
            doubanUserInfo.setPage(1);
            List<DoubanUserInfo> list = new ArrayList<>();
            // =doubanUserInfoDao.queryByPage(doubanUserInfo);
            for (DoubanUserInfo doubanUserInfoIn : list) {
                String mainUrl = doubanUserInfoIn.getMainPageUrl();
                mainUrls.add(new HttpGetRequest(mainUrl));
                // noteUrls.add(new HttpGetRequest(mainUrl+"note"));
                // albumUrls.add(new HttpGetRequest(mainUrl+"photos"));
                // likeUrls.add(new HttpGetRequest(mainUrl+"likes/"));
                // broadcastUrls.add(new HttpGetRequest(mainUrl+"statuses"));
                // doulieAllUrls.add(new HttpGetRequest(mainUrl+"doulists/all"));
                // doulieCollections.add(new HttpGetRequest(mainUrl+"doulists/collect"));
                // workUrls.add(new HttpGetRequest(mainUrl+"works"));
            }
            // lists.add(albumUrls);
            // lists.add(broadcastUrls);
            // lists.add(workUrls);
            // lists.add(likeUrls);
            // lists.add(doulieAllUrls);
            // lists.add(doulieCollections);
            lists.add(mainUrls);
            for (List<HttpRequest> urls : lists) {
                PubThreadPoolExecutor.getInstance().getPoolExecutorInstance().execute(new Runnable() {
                    @Override
                    public void run() {
                        GeccoEngine.create().classpath("com.sapling.spiderMans").start(urls).thread(5).interval(5000)
                        .start();
                    }
                });
            }
        }
    }

    /**
     * 抓取主页信息
     */
    @Override
    public void doubanUserInfo() {
        long total = doubanUserDao.countAll();
        if (total == 0) {
            logger.info("no log");
            return;
        }
        DoubanUserInfo doubanUserInfo = new DoubanUserInfo();
        int times = (int) (total / 10000l);
        doubanUserInfo.setLimit(10000);
        List<HttpRequest> mainUrls = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            doubanUserInfo.setPage(1);
            List<DoubanUserInfo> list = new ArrayList<>();
            // doubanUserInfoDao.queryByPage(doubanUserInfo);
            for (DoubanUserInfo doubanUserInfoIn : list) {
                String mainUrl = doubanUserInfoIn.getMainPageUrl();
                mainUrls.add(new HttpGetRequest(mainUrl));
            }
            PubThreadPoolExecutor.getInstance().getPoolExecutorInstance().execute(new Runnable() {
                @Override
                public void run() {
                    GeccoEngine.create().classpath("com.sapling.spiderMans").start(mainUrls).thread(5).interval(5000)
                    .start();
                }
            });
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sapling.spiderMans.service.douban.DoubanSpiderService#
     * queryDoubanUserByPage()
     */
    @Override
    public ResultBean queryDoubanUserByPage(DoubanUser doubanUser) {
        logger.info("进入主方法");
        ResultBean resultBean = CommonFactory.getDefaultResultBean();
        doubanUserDao=null;
        long size = doubanUserDao.count(doubanUser);
        resultBean.setCount(size);
        if (size != 0) {
            List<DoubanUser> doubanUsers = doubanUserDao.queryByPage(doubanUser);
            resultBean.setData(doubanUsers);
        }
        return resultBean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sapling.spiderMans.service.douban.DoubanSpiderService#
     * queryDoubanUserByPageChart(com.sapling.spiderMans.pojo.douban.DoubanUser)
     */
    @Override
    public ResultBean queryDoubanUserByPageChart(DoubanUser doubanUser) {
        ResultBean resultBean = CommonFactory.getDefaultResultBean();
        List<DoubanUser> doubanUsers = doubanUserDao.queryChart(doubanUser);
        List<String> list1=Lists.newArrayList();
        List<Long> list2=Lists.newArrayList();
        long countF=0;
        for (DoubanUser doubanUser2 : doubanUsers) {
            String location = doubanUser2.getLocation();
            if(Util.isEmpty(location)) 
                location="无地区";
            long count = doubanUser2.getCount();
            if(!location.matches("[\u4e00-\u9fa5]+")) {
                countF+=count;
                continue;
            }
            list1.add(location);
            list2.add(count);
        }
        list1.add(0, "海外");
        list2.add(0, countF);
        List<Object> list3 = Lists.newArrayList();
        list3.add(list1);
        list3.add(list2);
        resultBean.setData(list3);
        //        }
        return resultBean;
    }
    public static void main(String[] args) {
        String location="ss ss";
        System.out.println(location.matches("[\u4e00-\u9fa5]+"));
    }

    /* (non-Javadoc)
     * @see com.sapling.spiderMans.service.douban.DoubanSpiderService#sendMqMessage(java.lang.String, java.lang.String)
     */
    //    @Override
    //    public void sendMqMessage(String queueName, String message) {
    //        amqpTemplate.convertAndSend(queueName, "sssss"+new Date().toString());
    //    }
}

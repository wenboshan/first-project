package com.sapling.spiderMans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapling.spiderMans.pojo.common.ResultBean;
import com.sapling.spiderMans.pojo.douban.DoubanUser;
import com.sapling.spiderMans.service.douban.DoubanSpiderService;
import com.sapling.spiderMans.util.CommonFactory;

@Controller
@RequestMapping("douban/spider")
public class DoubanController extends BaseAction{
    @Autowired
    private DoubanSpiderService doubanSpiderService;
    //    @Autowired
    //    private AmqpTemplate amqpTemplate;
    /**
     * 定时器开启,第一个版本的定时器
     */
    @RequestMapping("firstStart")
    @Transactional()
    public void douBanBegin() {
        try {
            logger.info("DoubanController douBanBegin start");
            doubanSpiderService.doubanBegin();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("DoubanController douBanBegin error"+e);
        }
    }
    /**
     * 定时器开启，爬取用户日记信息
     */
    @RequestMapping("doubanNoteStart")
    public void douBanNoteBegin() {
        try {
            logger.info("DoubanController douBanNoteBegin start");
            doubanSpiderService.doubanNotesBegin();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("DoubanController douBanNoteBegin error"+e);
        }
    }
    /**
     * 定时器开启，爬去用户信息
     */
    @RequestMapping("doubanUserStart")
    public void douBanMainBegin() {
        try {
            logger.info("DoubanController douBanMainBegin start");
            doubanSpiderService.doubanNotesBegin();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("DoubanController douBanNoteBegin error"+e);
        }
    }
    @RequestMapping("doubanUser")
    @ResponseBody
    public ResultBean doubanUser(DoubanUser doubanUser) {
        ResultBean resultBean =null;
        logger.info("DoubanController doubanUser start");
        try {
            resultBean=doubanSpiderService.queryDoubanUserByPage(doubanUser);
            return resultBean;
        } catch (Exception e) {
            logger.error("DoubanController doubanUser error"+e);
            return CommonFactory.getFailResultBean();
        }

    }
    @RequestMapping("doubanUserChart")
    @ResponseBody
    public ResultBean doubanUserChart(DoubanUser doubanUser) {
        ResultBean resultBean =null;
        logger.info("DoubanController doubanUserChart start");
        try {
            resultBean=doubanSpiderService.queryDoubanUserByPageChart(doubanUser);
            return resultBean;
        } catch (Exception e) {
            logger.error("DoubanController doubanUserChart error"+e);
            return CommonFactory.getFailResultBean();
        }

    }
}

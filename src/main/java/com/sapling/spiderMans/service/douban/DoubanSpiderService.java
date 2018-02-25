package com.sapling.spiderMans.service.douban;

import com.sapling.spiderMans.pojo.common.ResultBean;
import com.sapling.spiderMans.pojo.douban.DoubanUser;

public interface DoubanSpiderService {
    void doubanBegin();
    void doubanUserInfo();
    void doubanNotesBegin();
    ResultBean queryDoubanUserByPage(DoubanUser doubanUser);
    ResultBean queryDoubanUserByPageChart(DoubanUser doubanUser);
    void sendMqMessage(String queueName,String message);
}

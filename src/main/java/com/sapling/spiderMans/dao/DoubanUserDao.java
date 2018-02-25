package com.sapling.spiderMans.dao;

import java.util.List;

import com.sapling.spiderMans.pojo.douban.DoubanUser;
import com.sapling.spiderMans.pojo.douban.DoubanUserInfo;

public interface DoubanUserDao extends BaseDao<DoubanUser>{
    /**
     * 批量插入数据   
     * @param doubanUsers
     */
    void batchCreate(List<DoubanUser> doubanUsers);
    /**
     * 修改数据
     * @param userNotesr
     */
    void updateUserNotes(DoubanUserInfo userNotes);
    /**
     * 查询所有数据数量
     * @return
     */
    long countAll();
    /**
     * 查询相关用户信息
     * @param doubanUserInfo
     * @return
     */
    List<DoubanUser> queryByPageByInfo(DoubanUserInfo doubanUserInfo);
    /**
     * 用户主页信息
     * @param doubanUserInfo
     * @return
     */
    List<DoubanUser> queryDoubanUserInfoByPageByInfo(DoubanUserInfo doubanUserInfo);
    /**
     * 查询用户数据信息
     * @param DoubanUser
     * @return
     */
    List<DoubanUser> queryChart(DoubanUser DoubanUser);
}

package com.sapling.spiderMans.douban;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.sapling.spiderMans.pojo.douban.DoubanUserInfo;
import com.sapling.spiderMans.util.Util;
//@PipelineName("doubanUserInfoPipeLine")
public class DoubanUserInfoPipeLine implements Pipeline<DoubanUserInfo>{
    //    DoubanUserInfoDao doubanUserInfoDao=SpringBeanUtils.context.getBean(DoubanUserInfoDao.class);
    @Override
    public void process(DoubanUserInfo userInfo) {
        String note = userInfo.getNote();
        userInfo.setUserId(note.substring(30, note.lastIndexOf("/")));
        userInfo.setAlbum(Util.isEmpty(userInfo.getAlbum())?"0":"1");
        userInfo.setBroadcast(Util.isEmpty(userInfo.getBroadcast())?"0":"1");
        userInfo.setDoulie(Util.isEmpty(userInfo.getDoulie())?"0":"1");
        userInfo.setLike(Util.isEmpty(userInfo.getLike())?"0":"1");
        userInfo.setNote(Util.isEmpty(note)?"0":"1");
        userInfo.setWork(Util.isEmpty(userInfo.getWork())?"0":"1");
        String trim = userInfo.getUserInfo().trim();
        userInfo.setJoinTime(trim.substring(trim.indexOf(">")));
        //        doubanUserInfoDao.updateUserNotes(userInfo);
    }
}

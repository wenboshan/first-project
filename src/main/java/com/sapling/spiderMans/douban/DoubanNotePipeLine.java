package com.sapling.spiderMans.douban;

import java.util.List;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.sapling.spiderMans.pojo.douban.DoubanNote;
import com.sapling.spiderMans.pojo.douban.DoubanNoteInfo;
import com.sapling.spiderMans.util.SpStringUtils;
@PipelineName("doubanNotePipeLine")
public class DoubanNotePipeLine implements Pipeline<DoubanNote>{
    //    //    DoubanNoteDao doubanNoteDao=SpringBeanUtils.context.getBean(DoubanNoteDao.class);
    //    RedisCacheManager redisCacheManager=SpringBeanUtils.context.getBean(RedisCacheManager.class);
    @Override
    public void process(DoubanNote doubanNote) {
        List<DoubanNoteInfo> doubanNoteInfos=doubanNote.getDoubanNoteInfos();
        String userId = doubanNote.getUserId();
        String substring = userId.substring(30, userId.lastIndexOf("/"));
        for (DoubanNoteInfo doubanNoteInfo : doubanNoteInfos) {
            doubanNoteInfo.setUserId(substring);
            doubanNoteInfo.setText(SpStringUtils.filterEmoji(doubanNoteInfo.getText(), "(emoji)"));
            doubanNoteInfo.setTitle(SpStringUtils.filterEmoji(doubanNoteInfo.getTitle(), "(emoji)"));
        }
        doubanNote.setDoubanNoteInfos(null);
        //        doubanNoteDao.batchCreate(doubanNoteInfos);
    }
}

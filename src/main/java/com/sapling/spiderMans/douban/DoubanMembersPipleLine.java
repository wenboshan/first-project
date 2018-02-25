package com.sapling.spiderMans.douban;

import java.util.List;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.sapling.spiderMans.pojo.douban.DoubanUser;
import com.sapling.spiderMans.pojo.douban.GroupHtml;
//@PipelineName("doubanSpiderPileLine")
public class DoubanMembersPipleLine implements Pipeline<GroupHtml> {
    //    @Autowired
    //    private DoubanUserDao doubanUserDao;
    @Override
    public void process(GroupHtml groupHtml) {
        List<DoubanUser> memberUrls=groupHtml.getMembers();
        //        for (DoubanUser urlBean : memberUrls) {
        ////            urlBean.setNoteUrl(urlBean.getMainPageUrl()+"notes");
        //        }
        //        doubanUserDao.batCreate(groupHtml);
    }
}

package com.sapling.spiderMans.pojo.douban;

import java.util.List;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="https://www.douban.com/group/blabla/members?start={page}", pipelines={"consolePipeline", "doubanSpiderPileLine"})

public class GroupHtml implements HtmlBean{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @HtmlField(cssPath =".name")
    private List<DoubanUser> members;

    public List<DoubanUser> getMembers() {
        return members;
    }

    public void setMembers(List<DoubanUser> members) {
        this.members = members;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

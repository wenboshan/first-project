package com.sapling.spiderMans.pojo.douban;

import java.util.List;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;
@Gecco(matchUrl="https://www.douban.com/people/{member}/notes", pipelines={"consolePipeline", "doubanNotePipeLine"})
public class DoubanNote implements HtmlBean{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @HtmlField(cssPath=".note-container")
    private List<DoubanNoteInfo> doubanNoteInfos;
    @Href
    @HtmlField(cssPath=".content a")
    private String userId;



    public List<DoubanNoteInfo> getDoubanNoteInfos() {
        return doubanNoteInfos;
    }

    public void setDoubanNoteInfos(List<DoubanNoteInfo> doubanNoteInfos) {
        this.doubanNoteInfos = doubanNoteInfos;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}

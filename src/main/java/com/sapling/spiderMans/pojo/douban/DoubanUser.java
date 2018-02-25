package com.sapling.spiderMans.pojo.douban;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
//@Gecco(matchUrl="https://www.douban.com/people/{member}/notes", pipelines={"consolePipeline", "doubanNotePipeLine"})
public class DoubanUser extends baseBean implements HtmlBean {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Text
    @HtmlField(cssPath = "a")
    private String userName;
    private String noteUrl;
    @HtmlField(cssPath = "span")
    private String location;
    @Attr("href")
    @HtmlField(cssPath = "a")
    private String mainPageUrl;
    private String headImage;
    private String userId;
    private long id;
    private long count;

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMainPageUrl() {
        return mainPageUrl;
    }

    public void setMainPageUrl(String mainPageUrl) {
        this.mainPageUrl = mainPageUrl;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}

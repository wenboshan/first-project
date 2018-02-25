package com.sapling.spiderMans.pojo.douban;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
//@Gecco(matchUrl="https://www.douban.com/note/{id}", pipelines={"consolePipeline", "doubanNoteInfoPipeLine"})
public class DoubanNoteInfo implements HtmlBean{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @HtmlField(cssPath=".note-header-container h3>a")
    private String title;
    @HtmlField(cssPath=".note-header-container .pub-date")
    private String time;
    @Attr("src")
    @HtmlField(cssPath=".note a img")
    private String img;
    private String userId;
    @Text
    @HtmlField(cssPath=".note")
    private String text;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
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
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}

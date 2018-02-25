package com.sapling.spiderMans.pojo.douban;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="https://www.douban.com/people/{member}/", pipelines={"consolePipeline", "doubanUserInfoPipeLine"})
public class DoubanUserInfo extends baseBean implements HtmlBean{
    /**
     * 
     */
    private long id;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    private static final long serialVersionUID = 1L;
    private String userId;
    @Href
    @HtmlField(cssPath="a:containsOwn(日记)")
    private String note; 
    @Href
    @HtmlField(cssPath="a:containsOwn(相册)")
    private String album;
    @Href
    @HtmlField(cssPath="a:containsOwn(喜欢)")
    private String like;
    @Href
    @HtmlField(cssPath="a:containsOwn(广播)")
    private String broadcast;
    @Href
    @HtmlField(cssPath="a:containsOwn(豆列)")
    private String doulie;
    @Href
    @HtmlField(cssPath="a:containsOwn(作品)")
    private String work;
    @HtmlField(cssPath="#display")
    private String dispaly;//签名
    @Attr("src")
    @HtmlField(cssPath="img[class=userface]")
    private String imageUrl;
    @HtmlField(cssPath="#intro_display")
    private String introDisplay;//个人简介
    @HtmlField(cssPath=".user-info div")
    private String userInfo;
    @HtmlField(cssPath=".user-info a")
    private String location;
    private String joinTime;
    private String mainPageUrl;
    public String getMainPageUrl() {
        return mainPageUrl;
    }
    public void setMainPageUrl(String mainPageUrl) {
        this.mainPageUrl = mainPageUrl;
    }
    public String getUserInfo() {
        return userInfo;
    }
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getJoinTime() {
        return joinTime;
    }
    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getLike() {
        return like;
    }
    public void setLike(String like) {
        this.like = like;
    }
    public String getBroadcast() {
        return broadcast;
    }
    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }
    public String getDoulie() {
        return doulie;
    }
    public void setDoulie(String doulie) {
        this.doulie = doulie;
    }
    public String getWork() {
        return work;
    }
    public void setWork(String work) {
        this.work = work;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getDispaly() {
        return dispaly;
    }
    public void setDispaly(String dispaly) {
        this.dispaly = dispaly;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getIntroDisplay() {
        return introDisplay;
    }
    public void setIntroDisplay(String introDisplay) {
        this.introDisplay = introDisplay;
    }
}

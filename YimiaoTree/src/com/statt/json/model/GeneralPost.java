
package com.statt.json.model;

import java.util.ArrayList;
import java.util.List;

public class GeneralPost {
    protected String id;
    protected String title;
    protected String publishDate;
    protected String replyCount;
    protected String content;
    protected String viewCount;
    protected String userName;
    // if the post is top post
    protected String state;
    protected String city;
    protected String userUrl;
    protected List<ImagePost> imageVos = new ArrayList<ImagePost>();

    public List<ImagePost> getImageVos() {
        return imageVos;
    }

    public void setImageVos(List<ImagePost> imageVos) {
        this.imageVos = imageVos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(String replyCount) {
        this.replyCount = replyCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }

}

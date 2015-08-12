
package com.statt.json.model;

import java.util.ArrayList;
import java.util.List;

public class ReplyPost {
    private String content;
    private String id;
    private String loc;
    private String publisherId;
    private String reDate;
    private String url;
    private String userName;
    private List<ImagePost> endVos = new ArrayList<ImagePost>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getReDate() {
        return reDate;
    }

    public void setReDate(String reDate) {
        this.reDate = reDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ImagePost> getEndVos() {
        return endVos;
    }

    public void setEndVos(List<ImagePost> endVos) {
        this.endVos = endVos;
    }

}

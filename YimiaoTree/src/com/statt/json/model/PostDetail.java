
package com.statt.json.model;

import java.util.ArrayList;
import java.util.List;

public class PostDetail extends GeneralPost {
    private String publisherId;
    protected List<ReplyPost> replyVos = new ArrayList<ReplyPost>();

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public List<ReplyPost> getReplyVos() {
        return replyVos;
    }

    public void setReplyVos(List<ReplyPost> replyVos) {
        this.replyVos = replyVos;
    }

}

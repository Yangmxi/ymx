
package com.statt.serializable;

import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {

    /**
     * Post for BBS
     * Date: 2015/08/06
     */
    private static final long serialVersionUID = 8495056067273593008L;
    private boolean mIsTop;
    private String mTitle;
    private String mContent;
    private Parent mParent;
    private String mDate;
    private String mClicksCount;
    private String mReplyCount;
    private String mId;
    private ArrayList<String> mImagePath;

    public Post(boolean mIsTop, String mTitle, String mContent,
            Parent mParent, String mDate, String mClicksCount,
            String mReplyCount, ArrayList<String> mImagePath, String id) {
        this.mIsTop = mIsTop;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mParent = mParent;
        this.mDate = mDate;
        this.mClicksCount = mClicksCount;
        this.mReplyCount = mReplyCount;
        this.mImagePath = mImagePath;
        this.mId = id;
    }

    public Post(boolean isTop, String title, String id) {
        this.mIsTop = isTop;
        this.mTitle = title;
        this.mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getClicksCount() {
        return mClicksCount;
    }

    public void setClicksCount(String mClicksCount) {
        this.mClicksCount = mClicksCount;
    }

    public String getReplyCount() {
        return mReplyCount;
    }

    public void setReplyCount(String mReplyCount) {
        this.mReplyCount = mReplyCount;
    }

    public boolean isTop() {
        return mIsTop;
    }

    public void setTop(boolean mIsTop) {
        this.mIsTop = mIsTop;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public Parent getParent() {
        return mParent;
    }

    public void setParent(Parent mParent) {
        this.mParent = mParent;
    }

    public ArrayList<String> getImagePath() {
        return mImagePath;
    }

    public void setImagePath(ArrayList<String> mImagePath) {
        this.mImagePath = mImagePath;
    }

}

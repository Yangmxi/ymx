
package com.statt.util;

import java.util.ArrayList;

import android.net.Uri;

public class Post {

    private boolean mIsTop;
    private String mTitle;
    private String mContent;
    private Parent mParent;
    private String mDate;
    private int mClicksCount;
    private int mReplyCount;
    private ArrayList<Uri> mImagePath;

    public Post(boolean mIsTop, String mTitle, String mContent,
            Parent mParent, String mDate, int mClicksCount,
            int mReplyCount, ArrayList<Uri> mImagePath) {
        this.mIsTop = mIsTop;
        this.mTitle = mTitle;
        this.mContent = mContent;
        this.mParent = mParent;
        this.mDate = mDate;
        this.mClicksCount = mClicksCount;
        this.mReplyCount = mReplyCount;
        this.mImagePath = mImagePath;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public int getClicksCount() {
        return mClicksCount;
    }

    public void setClicksCount(int mClicksCount) {
        this.mClicksCount = mClicksCount;
    }

    public int getReplyCount() {
        return mReplyCount;
    }

    public void setReplyCount(int mReplyCount) {
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

    public ArrayList<Uri> getImagePath() {
        return mImagePath;
    }

    public void setImagePath(ArrayList<Uri> mImagePath) {
        this.mImagePath = mImagePath;
    }

}

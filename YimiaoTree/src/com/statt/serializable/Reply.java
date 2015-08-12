
package com.statt.serializable;

import java.io.Serializable;

public class Reply implements Serializable {

    /**
     * Reply for BBS
     * Date: 2015/08/06
     */
    private static final long serialVersionUID = 4587323119849245257L;
    private Parent mParent;
    private int mFloor;
    private String mDate;
    private String mContent;

    public Reply(Parent mParent, String mDate, String mContent) {
        this.mParent = mParent;
        this.mDate = mDate;
        this.mContent = mContent;
    }

    public Reply(Parent mParent, int mFloor, String mDate, String mContent) {
        this(mParent, mDate, mContent);
        this.mFloor = mFloor;
    }

    public Parent getParent() {
        return mParent;
    }

    public void setParent(Parent mParent) {
        this.mParent = mParent;
    }

    public int getFloor() {
        return mFloor;
    }

    public void setFloor(int mFloor) {
        this.mFloor = mFloor;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

}

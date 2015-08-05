
package com.statt.util;

import android.widget.ImageView;

public class Baby {

    private String mName;
    // day of age.
    private int mAge;
    private boolean mSex;
    private ImageView mAvatar;

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    public boolean ismSex() {
        return mSex;
    }

    public void setSex(boolean mSex) {
        this.mSex = mSex;
    }

    public ImageView getAvatar() {
        return mAvatar;
    }

    public void setAvatar(ImageView mAvatar) {
        this.mAvatar = mAvatar;
    }

}

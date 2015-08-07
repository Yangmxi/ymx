
package com.statt.serializable;

import java.io.Serializable;

import android.widget.ImageView;

public class Baby implements Serializable {

    /**
     * This is Baby information. include name, age, sex, avatar
     * Date: 2015/08/06
     */
    private static final long serialVersionUID = 3513253904283377123L;
    private String mName;
    // day of age.
    private int mAge;
    private boolean mSex;
    private String mAvatar;

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

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }

}

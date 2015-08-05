
package com.statt.util;

import android.net.Uri;

public class Parent {

    private String mName;
    private String mPhoneNum;
    private String mPlace;
    private Uri mAvatar;

    public Parent(String name, String phoneNum, String place, Uri avatar) {
        this.mName = name;
        this.mPhoneNum = phoneNum;
        this.mPlace = place;
        this.mAvatar = avatar;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPhoneNum() {
        return mPhoneNum;
    }

    public void setPhoneNum(String mPhoneNum) {
        this.mPhoneNum = mPhoneNum;
    }

    public String getPlace() {
        return mPlace;
    }

    public void setPlace(String mPlace) {
        this.mPlace = mPlace;
    }

    public Uri getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Uri mAvatar) {
        this.mAvatar = mAvatar;
    }

}

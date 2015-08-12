
package com.statt.serializable;

import java.io.Serializable;

public class Parent implements Serializable {

    /**
     * This is parent class, include name, phoneNum, place, avatar
     * Date: 2015/08/06
     */
    private static final long serialVersionUID = -4721650620446356901L;
    private String mLoginID;
    private String mName;
    private String mPhoneNum;
    private String mPlace;
    private String mAvatar;

    public Parent(String name, String phoneNum, String place, String avatar) {
        this(name, place, avatar);
        this.mPhoneNum = phoneNum;

    }

    public Parent(String name, String place, String avatar) {
        this.mName = name;
        this.mPlace = place;
        this.mAvatar = avatar;
    }

    public void setLoginID(String id) {
        this.mLoginID = id;
    }

    public String getLoginID() {
        return this.mLoginID;
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

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }

}

package com.bandari.naveen.samplemd.adapters;

import android.graphics.drawable.Drawable;

/**
 * Created by suresh on 8/3/18.
 */

public class Place {
    private String name;
    private String desciption;
    private Drawable avatar;

    public String getName() {
        return name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {

        this.name = name;
    }

    public boolean isFavourite = false;

}

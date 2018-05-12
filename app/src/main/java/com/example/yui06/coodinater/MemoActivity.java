package com.example.yui06.coodinater;

import android.graphics.Bitmap;
import io.realm.RealmObject;

public class MemoActivity extends RealmObject{

    public byte[] picture;

    public String color;

    public String content;

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

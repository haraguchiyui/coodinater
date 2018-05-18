package com.example.yui06.coodinater;



import android.content.res.Resources;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.ByteArrayOutputStream;

import io.realm.RealmObject;

public class Memo extends RealmObject {

    public byte[] white;
    public byte[] picture=white;

    public String color = "";

    public String content = "";

    public Memo(byte[] data) {

        picture = data;

    }

    public Memo(){
        picture=white;
        color="";
        content="";
    }



}

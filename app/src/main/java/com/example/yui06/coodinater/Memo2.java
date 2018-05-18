package com.example.yui06.coodinater;

import io.realm.RealmObject;

public class Memo2 extends RealmObject {

    public byte[] white;
    public byte[] picture2=white;

    public String color2 = "";

    public String content2 = "";

    public String updateDate1;

    public Memo2(byte[] data) {

        picture2 = data;

    }

    public Memo2(){
        picture2=white;
        color2="";
        content2="";
    }


}

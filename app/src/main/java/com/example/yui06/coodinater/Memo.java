package com.example.yui06.coodinater;



import io.realm.RealmObject;

public class Memo extends RealmObject {


    public byte[] white;

    public byte[] picture=white ;

    public String color = "";

    public String content = "";

    public Memo() {

        this.white = R.drawable.white;
    }
}

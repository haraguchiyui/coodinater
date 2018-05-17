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

    public byte[] picture=white ;

    public String color = "";

    public String content = "";

    public Memo() {



//疑問 getContext()にエラーが起きたため、getContextメソッドを作りました。
// return nullで返しているからnull pointerExceptionだと推測していますが、だとすると具体的に何を入れたらいいのかがわかりません。



        Resources res=this.getContext().getResources();
        Bitmap bmp=BitmapFactory.decodeResource(res,R.drawable.white);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,baos);
        white=baos.toByteArray();

        this.white = white;
    }

    private Context getContext() {
        return null;
    }


}

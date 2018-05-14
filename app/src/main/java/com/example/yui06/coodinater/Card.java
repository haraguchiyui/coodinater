package com.example.yui06.coodinater;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Card{

    public Memo memo1;
    public Memo memo2;
    public Memo memo3;

    public Card(Memo memo1, Memo memo2, Memo memo3) {
        this.memo1 = memo1;
        this.memo2 = memo2;
        this.memo3 = memo3;
    }
}



package com.example.yui06.coodinater;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class Closet2Activity extends AppCompatActivity {

    FloatingActionButton add2;
    List<CardBottom> bCards;
    MemoAdapter memo2Adapter;
    public Realm realm;
    public ListView listView2;
    Bitmap bitmap2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet2);


        add2 = (FloatingActionButton) findViewById(R.id.add2);
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2;
                intent2 = new Intent(getApplicationContext(), Edit2Activity.class);
                startActivity(intent2);
            }


        });


    }

    public void setMemoList() {
        realm = Realm.getDefaultInstance();
        //Realmからデータ読み取り
        RealmResults<Memo2> results = realm.where(Memo2.class).findAll();
        List<Memo2> items2 = realm.copyFromRealm(results);
        ArrayList<Memo> arrayList2 = new ArrayList<>();
        //Memoから取り出した情報をすべてitemsに入れる...pictureはbyte型
        arrayList2.addAll(items2);

        Resources res=getResources();
        Bitmap bmp=BitmapFactory.decodeResource(res,R.drawable.white);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,baos);

        byte[] data = baos.toByteArray();

        ArrayList<Memo> tmp2 = new ArrayList<>();

        for (int i = 0; i < arrayList2.size(); i++) {

            tmp2.add(arrayList2.get(i));

            if (i % 3 == 2) {
                bCards.add(new CardBottom(tmp2.get(0), tmp2.get(1), tmp2.get(2)));
                tmp2.clear();
            }

            if (i == arrayList2.size() - 1) {
                if (tmp2.size() == 1) {
                    bCards.add(new CardBottom(tmp2.get(0), new Memo2(data), new Memo2(data)));
                } else if (tmp2.size() == 2) {
                    bCards.add(new Card(tmp2.get(0), tmp2.get(1), new Memo2(data)));
                }
            }
            MemoAdapter memoAdapter = new MemoAdapter(this, R.layout.activity_card_bottom,bCards);

            listView2.setAdapter(memoAdapter);
        }
    }
    @Override
    protected void onResume () {
        super.onResume();
        setMemoList();
    }
    @Override
    protected void onDestroy () {
        super.onDestroy();
        realm.close();
    }
}


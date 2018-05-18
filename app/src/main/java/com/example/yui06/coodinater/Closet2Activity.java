

package com.example.yui06.coodinater;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Memo2Adapter memo2Adapter;
    public Realm realm;
    public ListView listView2;
    Bitmap bitmap2;
    public byte[] data1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet2);

        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.white);
        listView2 = (ListView)findViewById(R.id.listView2);

        bCards = new ArrayList<CardBottom>();

        memo2Adapter = new Memo2Adapter(this, R.layout.activity_card_bottom, bCards);
        listView2.setAdapter(memo2Adapter);
        realm = Realm.getDefaultInstance();


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
        bCards.clear();

        //Realmからデータ読み取り
        RealmResults<Memo2> results1 = realm.where(Memo2.class).findAll();
        Log.e("TAGTAG", String.valueOf(results1.size()));
        List<Memo2> items1 = realm.copyFromRealm(results1);
        ArrayList<Memo2> arrayList2 = new ArrayList<>();
        //Memoから取り出した情報をすべてitemsに入れる...pictureはbyte型
        arrayList2.addAll(items1);

        Resources res=getResources();
        Bitmap bmp1=BitmapFactory.decodeResource(res,R.drawable.white);

        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        bmp1.compress(Bitmap.CompressFormat.PNG,100,baos1);

        data1 = baos1.toByteArray();

        ArrayList<Memo2> tmp2 = new ArrayList<>();

        for (int i = 0; i < arrayList2.size(); i++) {

            tmp2.add(arrayList2.get(i));

            if (i % 3 == 2) {
                bCards.add(new CardBottom(tmp2.get(0), tmp2.get(1), tmp2.get(2)));
                tmp2.clear();
            }

            if (i == arrayList2.size() - 1) {
                if (tmp2.size() == 1) {
                    bCards.add(new CardBottom(tmp2.get(0), new Memo2(data1), new Memo2(data1)));
                } else if (tmp2.size() == 2) {
                    bCards.add(new CardBottom(tmp2.get(0), tmp2.get(1), new Memo2(data1)));
                }
            }
            memo2Adapter.notifyDataSetChanged();
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



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
        RealmResults<Memo2> results = realm.where(Memo2.class).findAll();
        Log.e("TAGTAG", String.valueOf(results.size()));
        List<Memo2> items = realm.copyFromRealm(results);
        ArrayList<Memo2> arrayList2 = new ArrayList<>();
        //Memoから取り出した情報をすべてitemsに入れる...pictureはbyte型
        arrayList2.addAll(items);

        Resources res=getResources();
        Bitmap bmp=BitmapFactory.decodeResource(res,R.drawable.white);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,baos);

        byte[] data = baos.toByteArray();

        ArrayList<Memo2> tmp2 = new ArrayList<>();

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
                    bCards.add(new CardBottom(tmp2.get(0), tmp2.get(1), new Memo2(data)));
                }
            }
            memo2Adapter.notifyDataSetChanged();
        }

        finish();
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

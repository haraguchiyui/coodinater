package com.example.yui06.coodinater;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ClosetActivity extends AppCompatActivity {

    FloatingActionButton add;


    List<Card> mCards;
    MemoAdapter memoAdapter;

    public Realm realm;
    public ListView listView;




    Bitmap bitmap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        realm=Realm.getDefaultInstance();
        bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.white);
        listView=(ListView)findViewById(R.id.listView);

        mCards=new ArrayList<Card>();
        mCards.add(new Card(bitmap,bitmap,bitmap));

        memoAdapter=new MemoAdapter(this,R.layout.activity_card,mCards);
        listView.setAdapter(memoAdapter);



        add=(FloatingActionButton)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2;
                intent2 = new Intent(getApplicationContext(),EditActivity.class);
                startActivity(intent2);

            }


            });
    }


    public void setMemoList(){
        //Realmからデータ読み取り

        RealmResults<Memo> results=realm.where(Memo.class).findAll();
        List<Memo> items=realm.copyFromRealm(results);


        ArrayList<Bitmap> arrayList = new ArrayList<>();


        //pictureをbitmapに変換
        // arrayListにBitmapいれる
        // adapterつなぐ


        for (int i=0 ;i<items.size();i++) {

            Bitmap bmp = null;
//            byte[] picture=null;
            if (bmp != null) {
                bmp = BitmapFactory.decodeByteArray(items.get(i).picture, 0, items.get(i).picture.length);

            }

            arrayList.add(bmp);
        }

        //カードに三個ずつ写真いれる

        for (int i=0;i<arrayList.size();i++){


            if (i % 3 ==0){
                Card card=new Card(arrayList.get(0),bitmap,bitmap);
                mCards.add(card);
            }

            if (i % 3 ==1){
                Card card=new Card(arrayList.get(0),arrayList.get(1),bitmap);
                mCards.add(card);

            }

            if (i % 3 == 2){
                Card card = new Card(arrayList.get(0),arrayList.get(1),arrayList.get(2));
                mCards.add(card);

                arrayList.clear();
            }

            MemoAdapter memoAdapter=new MemoAdapter(this,R.layout.activity_card,mCards);

            listView.setAdapter(memoAdapter);

        }



    }




    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }
}

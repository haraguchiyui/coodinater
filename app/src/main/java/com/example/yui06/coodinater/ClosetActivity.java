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

    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    List<Card> mCard;

    LayoutInflater layoutInflater;


    Realm realm;
    public ListView listView;

    List<Card> mCards;
    MemoAdapter memoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        imageView6=(ImageView)findViewById(R.id.imageView6);

        realm= Realm.getDefaultInstance();
        listView=(ListView)findViewById(R.id.listView);
        mCards=new ArrayList<Card>();

        mCards.add(new Card(imageView4,imageView5,imageView6));

        memoAdapter= new MemoAdapter(this,R.layout.activity_card,mCards);
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

        //memoActivityに入ってる中身もってくる
        RealmResults<MemoActivity> results=realm.where(MemoActivity.class).findAll();
        //realmに保存されてるviewに入る中身もってくる
        List<MemoActivity> items =realm.copyFromRealm(results);

        MemoAdapter memoAdapter= new MemoAdapter(this,R.layout.activity_card,items);

        listView.setAdapter(memoAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();

        setMemoList();

    }



    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }


}

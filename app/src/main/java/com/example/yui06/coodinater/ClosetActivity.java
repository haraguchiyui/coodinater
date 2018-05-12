package com.example.yui06.coodinater;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ClosetActivity extends AppCompatActivity {

    FloatingActionButton add;

    Realm realm;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        realm= Realm.getDefaultInstance();
        listView=(ListView)findViewById(R.id.listView);

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

        //realmから読み取る
        RealmResults<MemoActivity> results=realm.where(MemoActivity.class).findAll();
        List<MemoActivity> items =realm.copyFromRealm(results);

        MemoAdapter memoAdapter= new MemoAdapter(this,R.layout.activity_closet,items);

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

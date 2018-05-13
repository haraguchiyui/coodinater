package com.example.yui06.coodinater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class RandomActivity extends AppCompatActivity {

    /*Realm realm;
    public List<Card> mCards;
    int number;

    ImageView imageView2;
    ImageView imageView3;
    Button again;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        again=(Button)findViewById(R.id.again);


        realm=Realm.getDefaultInstance();

        RealmResults<Memo> results=realm.where(Memo.class).findAll();
        List<Memo> items =realm.copyFromRealm(results);

        Random random=new Random();
        number=random.nextInt(mCards.size());



    }











    @Override
    public void onDestroy(){
        super.onDestroy();

        realm.close();
    }
}
*/
}
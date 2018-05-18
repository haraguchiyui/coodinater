package com.example.yui06.coodinater;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class randomActivity extends AppCompatActivity {

        public Realm realm;
        ImageView imageTops;
        ImageView imageBottoms;
        int number;
        Bitmap bmp;
        ArrayList<Bitmap> arrayPicture = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_random);

            imageTops = (ImageView) findViewById(R.id.imageTops);
            imageBottoms = (ImageView) findViewById(R.id.imageBottoms);


            realm = Realm.getDefaultInstance();
            //Realmからデータ読み取り
            RealmResults<Memo> results = realm.where(Memo.class).findAll();
            List<Memo> items = realm.copyFromRealm(results);
            ArrayList<Memo> clothArray = new ArrayList<>();
            //Memoから取り出した情報をすべてitemsに入れる...pictureはbyte型
            clothArray.addAll(items);

            Log.d("item", String.valueOf(items.size()));


            //itemsの写真データだけをbitmap型でリストに


            for (int i = 0; i < items.size(); i++) {

                bmp = null;

                bmp = BitmapFactory.decodeByteArray(items.get(i).picture, 0, items.get(i).picture.length);

                arrayPicture.add(bmp);

            }

            //



            selectCloth();




        }

        void selectCloth(){

            Random random = new Random();
            number = random.nextInt(arrayPicture.size());

            imageTops.setImageBitmap(arrayPicture.get(number));

        }

        @Override
        protected void onDestroy(){
            super.onDestroy();
            realm.close();
        }


        public void again(View v){
           selectCloth();

        }
}


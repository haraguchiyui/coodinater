package com.example.yui06.coodinater;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
        int number1;
        Bitmap bmp;
        Bitmap bmp1;
        ArrayList<Bitmap> arrayPicture = new ArrayList<>();
        ArrayList<Bitmap> arrayPicture2 = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_random);

            imageTops = (ImageView) findViewById(R.id.imageTops);
            imageBottoms = (ImageView) findViewById(R.id.imageBottoms);


            realm = Realm.getDefaultInstance();
            //Realmからデータ読み取り
            RealmResults<Memo> results = realm.where(Memo.class).findAll();
            RealmResults<Memo2> results1=realm.where(Memo2.class).findAll();

            List<Memo> items = realm.copyFromRealm(results);
            List<Memo2> items2=realm.copyFromRealm(results1);

            ArrayList<Memo> clothArray = new ArrayList<>();
            ArrayList<Memo2> clothArray2=new ArrayList<>();
            //Memoから取り出した情報をすべてitemsに入れる...pictureはbyte型
            clothArray.addAll(items);
            clothArray2.addAll(items2);

            Log.d("item", String.valueOf(items.size()));




            for (int i = 0; i < items.size(); i++) {

//closetに服が登録されてないとき、toastをだしてmainに戻りたい....
                if (items.size()<0){
                    Toast.makeText(this,"MY CLOSETに服を登録してください",Toast.LENGTH_LONG).show();
                    finish();
                }else if(items2.size()<0){
                    Toast.makeText(this,"MY CLOSETに服を登録してください",Toast.LENGTH_LONG).show();
                    finish();
                }else{


                    //pictureをbitmap型にしてlistに。
                bmp = null;
                bmp1=null;

                bmp = BitmapFactory.decodeByteArray(items.get(i).picture, 0, items.get(i).picture.length);
                bmp1=BitmapFactory.decodeByteArray(items2.get(i).picture2,0,items2.get(i).picture2.length);

                arrayPicture.add(bmp);
                arrayPicture2.add(bmp1);}


            }

            selectCloth();

        }

        void selectCloth(){

            Random random = new Random();
            number = random.nextInt(arrayPicture.size());
            imageTops.setImageBitmap(arrayPicture.get(number));


            Random random1=new Random();
            number1 = random.nextInt(arrayPicture2.size());
            imageBottoms.setImageBitmap(arrayPicture2.get(number1));

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


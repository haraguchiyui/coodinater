package cloth.apple.yui06.coordistcloset;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
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
    public Memo memo1,memo2,memo3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cloth.apple.yui06.coordistcloset.R.layout.activity_closet);

        bitmap = BitmapFactory.decodeResource(getResources(), cloth.apple.yui06.coordistcloset.R.drawable.white);
        listView = (ListView) findViewById(cloth.apple.yui06.coordistcloset.R.id.listView);

        mCards = new ArrayList<Card>();

       // mCards.add(new Card(memo1,memo2,memo3));

        memoAdapter = new MemoAdapter(this, cloth.apple.yui06.coordistcloset.R.layout.activity_card, mCards);
        listView.setAdapter(memoAdapter);

        add = (FloatingActionButton) findViewById(cloth.apple.yui06.coordistcloset.R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2;
                intent2 = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(intent2);
            }


        });
    }

    public void setMemoList() {
        mCards.clear();
        realm = Realm.getDefaultInstance();
        //Realmからデータ読み取り
        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        Log.e("TAGTAG", String.valueOf(results.size()));
        List<Memo> items = realm.copyFromRealm(results);
        ArrayList<Memo> arrayList = new ArrayList<>();
        //Memoから取り出した情報をすべてitemsに入れる...pictureはbyte型
        arrayList.addAll(items);

        Resources res=getResources();
        Bitmap bmp=BitmapFactory.decodeResource(res, cloth.apple.yui06.coordistcloset.R.drawable.white);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,baos);

        byte[] data = baos.toByteArray();

        ArrayList<Memo> tmp = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {

            tmp.add(arrayList.get(i));

            if (i % 3 == 2) {
                mCards.add(new Card(tmp.get(0), tmp.get(1), tmp.get(2)));
                tmp.clear();
            }

            if (i == arrayList.size() - 1) {
                if (tmp.size() == 1) {
                    mCards.add(new Card(tmp.get(0), new Memo(data), new Memo(data)));
                } else if (tmp.size() == 2) {
                    mCards.add(new Card(tmp.get(0), tmp.get(1), new Memo(data)));
                }
            }
        }

        memoAdapter.notifyDataSetChanged();

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

        //pictureをbitmapに変換
        // arrayListにBitmapいれる
        // adapterつなぐ




       /* for (int i=0 ;i<items.size();i++) {

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

        }*/

package com.example.yui06.coodinater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relative1,relative2,relative3,relative4,relative5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        relative1 =(RelativeLayout)findViewById(R.id.relative1);
        //relative2 =(RelativeLayout)findViewById(R.id.relative2);
        //relative3=(RelativeLayout)findViewById(R.id.relative3);
        //relative4=(RelativeLayout)findViewById(R.id.relative4);
        //relative5=(RelativeLayout)findViewById(R.id.relative5) ;

        Calendar cal= Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if(month>=2){
            if(month<=9){
                relative1.setBackgroundColor(Color.rgb(255, 153, 102));
            }else {
                relative1.setBackgroundColor(Color.rgb(51,153,255));
            }
        }else{
            relative1.setBackgroundColor(Color.rgb(51,153,255));
        }

    }

    public void randomStart(View v){
        Intent intent= new Intent(this,RandomActivity.class);
        startActivity(intent);
    }

    public void myCloset(View v){
        Intent intent1=new Intent(this,ClosetActivity.class);
        startActivity(intent1);
    }
}

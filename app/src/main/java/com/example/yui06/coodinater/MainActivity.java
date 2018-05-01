package com.example.yui06.coodinater;

import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relative1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relative1 =(RelativeLayout)findViewById(R.id.relative1);

        Calendar cal= Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if (month<=2){
            int colorId=getResources().getColor(R.color.FFCC80);


        }

    }
}

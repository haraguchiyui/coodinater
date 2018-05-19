package cloth.apple.yui06.coordistcloset;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relative1,relative2,relative3,relative4,relative5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cloth.apple.yui06.coordistcloset.R.layout.activity_main);

        relative1 =(RelativeLayout)findViewById(cloth.apple.yui06.coordistcloset.R.id.relative1);
        relative2 =(RelativeLayout)findViewById(cloth.apple.yui06.coordistcloset.R.id.relative2);
        relative3=(RelativeLayout)findViewById(cloth.apple.yui06.coordistcloset.R.id.relative3);
        relative4=(RelativeLayout)findViewById(cloth.apple.yui06.coordistcloset.R.id.relative4);
        relative5=(RelativeLayout)findViewById(cloth.apple.yui06.coordistcloset.R.id.relative5);

        /*Calendar cal= Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if(month>=2){
            if(month<=9){
                relative1.setBackgroundColor(Color.rgb(255, 153, 102));
            }else {
                relative1.setBackgroundColor(Color.rgb(51,153,255));
            }
        }else{
            relative1.setBackgroundColor(Color.rgb(51,153,255));
        }*/

    }

    public void randomStart(View v){
        Intent intent= new Intent(this,randomActivity.class);
        startActivity(intent);
    }

    public void Tops(View v){
        Intent intent=new Intent(this,ClosetActivity.class);
        startActivity(intent);
    }

    public void bottoms(View v){
        Intent intent=new Intent(this,Closet2Activity.class);
        startActivity(intent);
    }
}

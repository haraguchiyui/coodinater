package com.example.yui06.coodinater;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class Edit2Activity extends AppCompatActivity {


    static final int REQUEST_CODE_GALLERY = 1;
    static final int REQUEST_CODE_CAMERA = 2;

    public Realm realm;

    public EditText colorEditText2;
    public EditText contentEditText2;


    ImageView imageView2;
    Button addData2;

    byte[] picture2;

    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit2);

        imageView2 = (ImageView) findViewById(R.id.imageView2);

        colorEditText2 = (EditText) findViewById(R.id.colorEditText2);
        contentEditText2 = (EditText) findViewById(R.id.contentEditText2);
        addData2=(Button)findViewById(R.id.addData);


        new AlertDialog.Builder(Edit2Activity.this)
                .setTitle("画像を選択")
                .setPositiveButton(
                        "カメラロール",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, REQUEST_CODE_GALLERY);
                            }
                        })
                .setNegativeButton(
                        "カメラ",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, REQUEST_CODE_CAMERA);

                            }
                        })
                .show();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_GALLERY) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(intent.getData());
                    Bitmap bmp2 = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    imageView2.setImageBitmap(bmp2);
                    //byte型に変換
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmp2.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    picture2=baos.toByteArray();
                } catch (Exception e) {
                    Toast.makeText(this, "エラー", Toast.LENGTH_LONG).show();
                }



            } else if (requestCode == REQUEST_CODE_CAMERA) {
                Bitmap bmp2 = (Bitmap) intent.getExtras().get("data");
                imageView2.setImageBitmap(bmp2);
                //byte型に変換
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bmp2.compress(Bitmap.CompressFormat.JPEG,100,baos);
                picture2=baos.toByteArray();

            }

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(Edit2Activity.this, "CANCEL", Toast.LENGTH_SHORT).show();
        }


    }

    public void save(final String color2, final String content2, final byte[] picture2,final String updateDate1) {

        realm = Realm.getDefaultInstance();

        //疑問
        //executeTransactionがいつなぜ線がひかれたのか。またこれがsaveやaddDataメソッドのエラー原因？？

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo2 memo2= realm.createObject(Memo2.class);
                memo2.picture2 = picture2;
                memo2.updateDate1= updateDate1;
                memo2.color2 = color2;
                memo2.content2 = content2;

            }
        });

    }

    public void AddData2(View view) {

        String color2 = colorEditText2.getText().toString();

        String content2 = contentEditText2.getText().toString();

        Date date1=new Date();
        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.JAPANESE);
        String updateDate1=sdf1.format(date1);


        save(color2,content2,picture2,updateDate1);

        Log.d("保存",String.valueOf(content2));


        finish();



    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }
}

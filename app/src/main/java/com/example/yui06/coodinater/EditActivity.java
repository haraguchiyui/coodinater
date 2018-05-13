package com.example.yui06.coodinater;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import io.realm.Realm;

import static android.provider.MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;

public class EditActivity extends AppCompatActivity {

    static final int REQUEST_CODE_GALLERY = 1;
    static final int REQUEST_CODE_CAMERA = 2;

    public Realm realm;

    public EditText colorEditText;
    public EditText contentEditText;


    ImageView imageView;

    byte[] picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        realm = Realm.getDefaultInstance();

        imageView = (ImageView) findViewById(R.id.imageView);
        colorEditText = (EditText) findViewById(R.id.colorEditText);
        contentEditText = (EditText) findViewById(R.id.contentEditText);


        new AlertDialog.Builder(EditActivity.this)
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
                    Bitmap bmp1 = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                    imageView.setImageBitmap(bmp1);
                    //byte型に変換
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmp1.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    picture=baos.toByteArray();
                } catch (Exception e) {
                    Toast.makeText(this, "エラー", Toast.LENGTH_LONG).show();
                }



            } else if (requestCode == REQUEST_CODE_CAMERA) {
                Bitmap bmp1 = (Bitmap) intent.getExtras().get("data");
                imageView.setImageBitmap(bmp1);
                //byte型に変換
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bmp1.compress(Bitmap.CompressFormat.JPEG,100,baos);
                picture=baos.toByteArray();

            }

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(EditActivity.this, "CANCEL", Toast.LENGTH_SHORT).show();
        }


    }

    public void save(final String color, final String content, final byte[] picture) {


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.picture = picture;
                memo.color = color;
                memo.content = content;

            }
        });

    }

    public void create(View view) {

        String color = colorEditText.getText().toString();

        String content = contentEditText.getText().toString();


        save(color,content,picture);

        finish();




    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }
}
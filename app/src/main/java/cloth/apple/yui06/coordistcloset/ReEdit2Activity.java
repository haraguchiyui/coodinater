package cloth.apple.yui06.coordistcloset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import io.realm.Realm;

import static cloth.apple.yui06.coordistcloset.Edit2Activity.REQUEST_CODE_CAMERA;
import static cloth.apple.yui06.coordistcloset.Edit2Activity.REQUEST_CODE_GALLERY;

public class ReEdit2Activity extends AppCompatActivity {

    Realm realm;

    EditText colorText1;
    EditText contentText1;
    ImageView imageView31;

    Memo2 memo2;

    byte[] Rpicture2;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cloth.apple.yui06.coordistcloset.R.layout.activity_re_edit2);

        realm=Realm.getDefaultInstance();

        colorText1=(EditText)findViewById(cloth.apple.yui06.coordistcloset.R.id.colorText1);
        contentText1=(EditText)findViewById(cloth.apple.yui06.coordistcloset.R.id.contentText1);
        imageView31=(ImageView)findViewById(cloth.apple.yui06.coordistcloset.R.id.imageView31);

        memo2=realm.where(Memo2.class).equalTo("updateDate",getIntent().getStringExtra("updateDate")).findFirst();

        bitmap= BitmapFactory.decodeByteArray(memo2.picture2,0,memo2.picture2.length);

        colorText1.setText(memo2.color2);
        contentText1.setText(memo2.content2);
        imageView31.setImageBitmap(bitmap);
        imageView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ReEdit2Activity.this)
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
        });
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
                    imageView31.setImageBitmap(bmp1);
                    //byte型に変換
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmp1.compress(Bitmap.CompressFormat.JPEG,100,baos);
                    Rpicture2=baos.toByteArray();
                } catch (Exception e) {
                    Toast.makeText(this, "エラー", Toast.LENGTH_LONG).show();
                }



            } else if (requestCode == REQUEST_CODE_CAMERA) {
                Bitmap bmp1 = (Bitmap) intent.getExtras().get("data1");
                imageView31.setImageBitmap(bmp1);
                //byte型に変換
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                bmp1.compress(Bitmap.CompressFormat.JPEG,100,baos);
                Rpicture2=baos.toByteArray();

            }

        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(ReEdit2Activity.this, "CANCEL", Toast.LENGTH_SHORT).show();
        }


    }

    public void reEdit(View view){
        final String color =colorText1.getText().toString();
        final String content=contentText1.getText().toString();


        /*Bitmap bmp = ((BitmapDrawable)imageView3.getDrawable()).getBitmap();
        final byte[] bytes;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
        bytes=baos.toByteArray();*/

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                memo2.color2=color;
                memo2.content2=content;
                memo2.picture2=Rpicture2;
            }
        });

        finish();
    }
    public void delete(View view){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                memo2.deleteFromRealm();
            }
        });

        finish();

    }


}

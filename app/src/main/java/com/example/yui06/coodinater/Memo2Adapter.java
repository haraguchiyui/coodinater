package com.example.yui06.coodinater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Memo2Adapter extends ArrayAdapter<CardBottom> {


    public LayoutInflater layoutInflater2;
    public List<CardBottom> bCards;

    Bitmap bmp2;

    ArrayList<Memo> tmp2 = new ArrayList<>();

    ArrayList<Bitmap> picture2 = new ArrayList<>();

    Context context1;


    public Memo2Adapter(Context context, int resource, List<CardBottom> objects) {
        super(context, resource, objects);
        layoutInflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        bCards = objects;
        //mCardsに入ってるmemo型items,pictureはbyte型→bitmap型に変換してimageViewに表示させたい


        for (int i = 0; i < tmp2.size(); i++) {


            bmp2 = null;
            if (bmp2 != null) {
                bmp2 = BitmapFactory.decodeByteArray(tmp2.get(i).picture, 0, tmp2.get(i).picture.length);
            }
            picture2.add(bmp2);


        }
    }

    @Override
    public int getCount() {

        return bCards.size();


    }

    @Override
    public CardBottom getItem(int position) {

        return bCards.get(position);
    }


    public static class ViewHolder {
        ImageView icon4;
        ImageView icon5;
        ImageView icon6;

        public ViewHolder(View view) {
            icon4 = (ImageView) view.findViewById(R.id.image_view4);
            icon5 = (ImageView) view.findViewById(R.id.image_view5);
            icon6 = (ImageView) view.findViewById(R.id.image_view6);

        }

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Memo2Adapter.ViewHolder viewHolder1;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_card_bottom, null);
            viewHolder1 = new Memo2Adapter.ViewHolder(convertView);
            convertView.setTag(viewHolder1);
        } else {
            viewHolder1 = (Memo2Adapter.ViewHolder) convertView.getTag();
        }

        final CardBottom items = getItem(position);

        if (items != null) {
            if (items.memo4.picture2 != null) {
                Bitmap bmp4 = BitmapFactory.decodeByteArray(items.memo4.picture2, 0, items.memo4.picture2.length);
                viewHolder1.icon4.setImageBitmap(bmp4);
                viewHolder1.icon4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(context1,ReEdit2Activity.class);
                        intent.putExtra("updateDate",bCards.get(position).memo4.updateDate1);
                        context1.startActivity(intent);
                    }
                });
            }

            if (items.memo5.picture2 != null) {
                Bitmap bmp5 = BitmapFactory.decodeByteArray(items.memo5.picture2, 0, items.memo5.picture2.length);
                viewHolder1.icon5.setImageBitmap(bmp5);
                viewHolder1.icon5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(context1,ReEdit2Activity.class);
                        intent.putExtra("updateDate",bCards.get(position).memo5.updateDate1);
                        context1.startActivity(intent);
                    }
                });

            }
            if (items.memo6.picture2!= null) {
                Bitmap bmp6 = BitmapFactory.decodeByteArray(items.memo6.picture2, 0, items.memo6.picture2.length);
                viewHolder1.icon6.setImageBitmap(bmp6);
                viewHolder1.icon6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(context1,ReEdit2Activity.class);
                        intent.putExtra("updateDate",bCards.get(position).memo6.updateDate1);
                        context1.startActivity(intent);
                    }
                });
            }
        }
        return convertView;

    }
}



package com.example.yui06.coodinater;

import android.content.Context;
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
        ImageView icon1;
        ImageView icon2;
        ImageView icon3;

        public ViewHolder(View view) {
            icon1 = (ImageView) view.findViewById(R.id.image_view4);
            icon2 = (ImageView) view.findViewById(R.id.image_view5);
            icon3 = (ImageView) view.findViewById(R.id.image_view6);

        }

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MemoAdapter.ViewHolder viewHolder;


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_card_bottom, null);
            viewHolder = new MemoAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MemoAdapter.ViewHolder) convertView.getTag();
        }

        final CardBottom item = getItem(position);

        if (item != null) {
            if (item.memo4.picture2 != null) {
                Bitmap bmp1 = BitmapFactory.decodeByteArray(item.memo4.picture2, 0, item.memo4.picture2.length);
                viewHolder.icon1.setImageBitmap(bmp1);
            }

            if (item.memo5.picture2 != null) {
                Bitmap bmp2 = BitmapFactory.decodeByteArray(item.memo5.picture2, 0, item.memo5.picture2.length);
                viewHolder.icon2.setImageBitmap(bmp2);
            }
            if (item.memo6.picture2 != null) {
                Bitmap bmp3 = BitmapFactory.decodeByteArray(item.memo6.picture2, 0, item.memo6.picture2.length);
                viewHolder.icon3.setImageBitmap(bmp3);
            }
        }
        return convertView;

    }
}



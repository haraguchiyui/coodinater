package com.example.yui06.coodinater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MemoAdapter extends ArrayAdapter<Card> {

    public LayoutInflater layoutInflater;
    public List<Card> mCards;

    Bitmap bmp;

    ArrayList<Memo> tmp = new ArrayList<>();

    ArrayList<Bitmap> picture = new ArrayList<>();


    public MemoAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mCards = objects;
        //mCardsに入ってるmemo型items,pictureはbyte型→bitmap型に変換してimageViewに表示させたい


        for (int i = 0; i < tmp.size(); i++) {


            bmp = null;
            if (bmp != null) {
                bmp = BitmapFactory.decodeByteArray(tmp.get(i).picture, 0, tmp.get(i).picture.length);
            }
            picture.add(bmp);


        }
    }

    @Override
    public int getCount() {

        return mCards.size();


    }

    @Override
    public Card getItem(int position) {

        return mCards.get(position);
    }


    public static class ViewHolder {
        ImageView icon1;
        ImageView icon2;
        ImageView icon3;

        public ViewHolder(View view) {
            icon1 = (ImageView) view.findViewById(R.id.image_view);
            icon2 = (ImageView) view.findViewById(R.id.image_view_2);
            icon3 = (ImageView) view.findViewById(R.id.image_view_3);

        }

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_card, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Card item = getItem(position);

        if (item != null) {
            if (item.memo1.picture != null) {
                Bitmap bmp1 = BitmapFactory.decodeByteArray(item.memo1.picture, 0, item.memo1.picture.length);
                viewHolder.icon1.setImageBitmap(bmp1);
            }

            if (item.memo2.picture != null) {
                Bitmap bmp2 = BitmapFactory.decodeByteArray(item.memo2.picture, 0, item.memo2.picture.length);
                viewHolder.icon2.setImageBitmap(bmp2);
            }
            if (item.memo3.picture != null) {
                Bitmap bmp3 = BitmapFactory.decodeByteArray(item.memo3.picture, 0, item.memo3.picture.length);
                viewHolder.icon3.setImageBitmap(bmp3);
            }
        }
        return convertView;

    }
}




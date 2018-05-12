package com.example.yui06.coodinater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class MemoAdapter extends ArrayAdapter<MemoActivity> {

    public LayoutInflater layoutInflater;
    public List<MemoActivity> mCards;


    public MemoAdapter(Context context, int resource, List<MemoActivity> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mCards = objects;
    }

    @Override
    public int getCount(){
        return mCards.size();
    }

    @Override
    public MemoActivity getItem(int position){
        return mCards.get(position);
    }

    public static class ViewHolder{
        ImageView icon1;
        ImageView icon2;
        ImageView icon3;

        public ViewHolder(View view){
            icon1=(ImageView)view.findViewById(R.id.imageView4);
            icon2=(ImageView)view.findViewById(R.id.imageView5);
            icon3=(ImageView)view.findViewById(R.id.imageView6);

        }
    }

    @Override
    public View getView(final int position,View convertView,ViewGroup parent){
        final ViewHolder viewHolder;

        if (convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.activity_card,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        final MemoActivity item=getItem(position);

        if (item==null){
            //bitmap型に戻す
            Bitmap bmp=null;
            if (item.picture != null) {
                bmp = BitmapFactory.decodeByteArray(item.picture, 0, item.picture.length);
            }

            viewHolder.icon1.setImageBitmap(bmp);
            viewHolder.icon2.setImageBitmap(bmp);
            viewHolder.icon3.setImageBitmap(bmp);


        }





        return convertView;
    }


}

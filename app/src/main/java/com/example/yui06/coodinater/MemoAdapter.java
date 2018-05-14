package com.example.yui06.coodinater;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class MemoAdapter extends ArrayAdapter<Card> {

    public LayoutInflater layoutInflater;
    public List<Card> mCards;

    Memo memo1;
    Memo memo2;
    Memo memo3;





    public MemoAdapter(Context context, int resource, List<Card> objects) {
        super(context, resource, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mCards = objects;


    }

    @Override
    public int getCount(){

        return mCards.size();
    }

    @Override
    public Card getItem(int position){

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

        final Card item=getItem(position);

        if (item==null){

            viewHolder.icon1.setImageBitmap(item.memo1);
            viewHolder.icon2.setImageBitmap(item.memo2);
            viewHolder.icon3.setImageBitmap(item.memo3);


        }





        return convertView;
    }


}

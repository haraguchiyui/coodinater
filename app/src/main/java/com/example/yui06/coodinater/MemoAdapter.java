package com.example.yui06.coodinater;

import android.content.Context;
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




    public MemoAdapter( Context context, int resource, List<MemoActivity> objects) {
        super(context, resource, objects);
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        MemoActivity memo = getItem(position);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_closet, null);
        }

        ImageView icon1=(ImageView)convertView.findViewById(R.id.imageView4);
        ImageView icon2=(ImageView)convertView.findViewById(R.id.imageView5);
        ImageView icon3=(ImageView)convertView.findViewById(R.id.imageView6);

        //icon2.setImageBitmap(memo.picture);
        //icon3.setImageBitmap(memo.picture);

        return convertView;


    }
}






/*
    public MemoAdapter(Context context, int resource, List<CardActivity> objects) {
        super(context, resource, objects);

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
    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.activity_card,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

        final CardActivity item=getItem(position);

        if (item==null){
            //set data
            viewHolder.icon1.setBackgroundResource(memo.picture);
        }

        return convertView;
    }
}
*/
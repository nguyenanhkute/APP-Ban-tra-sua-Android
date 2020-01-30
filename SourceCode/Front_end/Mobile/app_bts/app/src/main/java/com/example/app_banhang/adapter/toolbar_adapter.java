package com.example.app_banhang.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_banhang.R;
import com.example.app_banhang.model.listview_danhmuc;

import java.util.ArrayList;

public class toolbar_adapter extends BaseAdapter {
    ArrayList<listview_danhmuc> arrayList;
    Context context;

    public toolbar_adapter(ArrayList<listview_danhmuc> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder{
        TextView textViewdanhmuc;
        ImageView imageViewdanhmuc;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){

        if (view == null){

            ViewHolder viewHolder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview,viewGroup, false);

            viewHolder.textViewdanhmuc = (TextView) view.findViewById(R.id.idtextlistview);
            viewHolder.imageViewdanhmuc = (ImageView) view.findViewById(R.id.idImagelistview);

            view.setTag(viewHolder);
        } else {
            ViewHolder viewHolder = (ViewHolder) view.getTag();

            listview_danhmuc danhmuc = arrayList.get(i);
            viewHolder.textViewdanhmuc.setText(danhmuc.getTendanhmuc());
            viewHolder.imageViewdanhmuc.setImageResource(danhmuc.getHinhanh());

        }
        return view;
    }
}

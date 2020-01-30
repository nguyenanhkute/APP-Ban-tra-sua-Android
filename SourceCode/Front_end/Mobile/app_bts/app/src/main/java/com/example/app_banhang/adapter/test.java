package com.example.app_banhang.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_banhang.R;
import com.example.app_banhang.model.GioHang;
import com.example.app_banhang.model.phanhoi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class test  extends BaseAdapter {
    Context context;
    ArrayList<phanhoi> arrayphanhoi;

    public test(Context context, ArrayList<phanhoi> arrayphanhoi) {
        this.context = context;
        this.arrayphanhoi = arrayphanhoi;
    }

    @Override
    public int getCount() {
        return arrayphanhoi.size();
    }
    @Override
    public Object getItem(int position) {
        return arrayphanhoi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public ImageView imgsanpham;
        public TextView txttensp,txtnoidung;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_danhgia, null);

            viewHolder.imgsanpham = (ImageView) convertView.findViewById(R.id.imageviewsanpham);
            viewHolder.txttensp =(TextView) convertView.findViewById(R.id.textviewtensanpham);
            viewHolder.txtnoidung =(TextView) convertView.findViewById(R.id.textviewnoidung);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        phanhoi phanhoi = (phanhoi) getItem(position);
        viewHolder.txttensp.setText(phanhoi.getTensp());
        viewHolder.txtnoidung.setText(phanhoi.getMota());
        viewHolder.imgsanpham.setImageBitmap(StringToBitMap(phanhoi.getHinhanh()));


       /* Picasso.get().load(phanhoi.getHinhanh())
                .error(R.drawable.hoa)
                .into(viewHolder.imgsanpham);*/

        //
        final ViewHolder finalViewHolder = viewHolder;


        return convertView;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

}
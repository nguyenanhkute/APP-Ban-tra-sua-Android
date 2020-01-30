package com.example.app_banhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_banhang.R;
import com.example.app_banhang.activity.CTSPActivity;
import com.example.app_banhang.model.phanhoi;
import com.example.app_banhang.model.sanpham;
import com.example.app_banhang.util.check_connection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class phanhoiAdapter extends RecyclerView.Adapter<phanhoiAdapter.ItemHolder> {
    static Context context;
    static ArrayList<phanhoi> phanhoiArrayList;

    public phanhoiAdapter(Context context, ArrayList<phanhoi> phanhoiArrayList) {
        this.context = context;
        this.phanhoiArrayList = phanhoiArrayList;
    }

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_danhgia,parent,false);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {


        phanhoi phanhoi = phanhoiArrayList.get(position);
        holder.txttensp.setText(phanhoi.getTenkh());
        holder.txtnoidung.setText(phanhoi.getMota());
        holder.imgsanpham.setImageBitmap(StringToBitMap(phanhoi.getHinhanh()));



    }

    @Override
    public int getItemCount() {
        return phanhoiArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgsanpham;
        public TextView txttensp,txtnoidung;


        public ItemHolder( View itemView){
            super(itemView);
            imgsanpham = (ImageView) itemView.findViewById(R.id.imageviewsanpham);
            txttensp =(TextView) itemView.findViewById(R.id.textviewtensanpham);
            txtnoidung =(TextView) itemView.findViewById(R.id.textviewnoidung);;


        }

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

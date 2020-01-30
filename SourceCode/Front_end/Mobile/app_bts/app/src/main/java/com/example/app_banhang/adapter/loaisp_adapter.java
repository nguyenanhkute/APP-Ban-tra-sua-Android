package com.example.app_banhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_banhang.R;
import com.example.app_banhang.activity.SanphamActivity;
import com.example.app_banhang.util.check_connection;
import com.example.app_banhang.model.loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class loaisp_adapter extends RecyclerView.Adapter<loaisp_adapter.ItemHolder> {
    ArrayList<loaisp> arrayListLoaisp;
    Context context;

    public loaisp_adapter(ArrayList<loaisp> arrayListLoaisp, Context context) {
        this.arrayListLoaisp = arrayListLoaisp;
        this.context = context;
    }
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_loaisp, null );
        return new ItemHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        loaisp lsp = arrayListLoaisp.get(position);
        holder.txttenlsp.setText(lsp.getTenloaisp());
        Picasso.get().load(lsp.getHinhanh())
                .error(R.drawable.hoa)
                .into(holder.imgloaisanpham);

    }

    @Override
    public int getItemCount() {
        return arrayListLoaisp.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgloaisanpham;
        public TextView txttenlsp;


        public ItemHolder(View itemView) {
            super(itemView);
            imgloaisanpham = (ImageView) itemView.findViewById(R.id.idImageviewlsp);
            txttenlsp = (TextView) itemView.findViewById(R.id.txtviewtenlsp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, SanphamActivity.class);
                    intent.putExtra("loaisanpham", (arrayListLoaisp.get(getAdapterPosition()).getTenloaisp()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //check_connection.showToast_SHORT(context,arrayListLoaisp.get(getAdapterPosition()).getTenloaisp());
                    context.startActivity(intent);
                }
            });
        }
    }

}
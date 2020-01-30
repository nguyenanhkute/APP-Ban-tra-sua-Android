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
import com.example.app_banhang.activity.CTSPActivity;
import com.example.app_banhang.model.sanpham;
import com.example.app_banhang.util.check_connection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class sanpham_adapter extends RecyclerView.Adapter<sanpham_adapter.ItemHolder> {
    static Context context;
    static ArrayList<sanpham> arraysanpham;

    public sanpham_adapter(Context context, ArrayList<sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanpham,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        sanpham sp= arraysanpham.get(position);
        holder.txttensp.setText(sp.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasp.setText("Giá: "+decimalFormat.format(sp.getGia())+" Đ");
        Picasso.get().load(sp.getHinhanh())
                .error(R.drawable.hoa)
                .into(holder.imgsanpham);

    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgsanpham;
        public TextView txttensp,txtgiasp;


        public ItemHolder( View itemView){
            super(itemView);
            imgsanpham = (ImageView) itemView.findViewById(R.id.idImageviewsp);
            txtgiasp = (TextView) itemView.findViewById(R.id.txtviewgiasp);
            txttensp =(TextView) itemView.findViewById(R.id.txtviewtensp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, CTSPActivity.class);
                    intent.putExtra("sanpham", (arraysanpham.get(getAdapterPosition()).getMasp()));
                    intent.putExtra("tensanpham", (arraysanpham.get(getAdapterPosition()).getTensp()));
                    //check_connection.showToast_SHORT(context,arraysanpham.get(getAdapterPosition()).getMasp());
                    context.startActivity(intent);
                }
            });
        }

    }
}

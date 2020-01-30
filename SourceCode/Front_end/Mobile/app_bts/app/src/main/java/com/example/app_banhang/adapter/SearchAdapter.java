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
import com.example.app_banhang.model.Product;
import com.example.app_banhang.util.check_connection;
import com.squareup.picasso.Picasso;

import java.util.List;


public class  SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

    static  List<Product> products;
    static  Context context;

    public SearchAdapter(List<Product> product, Context context) {
        this.products = product;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {
        Product sp = products.get(position);
        holder.tensp.setText(sp.getName());
        holder.gia.setText(sp.getPrice());
        Picasso.get().load(sp.getImage())
                .error(R.drawable.hoa)
                .into(holder.hinhanh);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView hinhanh;
        TextView tensp, gia;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tensp = (TextView) itemView.findViewById(R.id.nameItem);
            gia = (TextView) itemView.findViewById(R.id.priceItem);
            hinhanh = (ImageView) itemView.findViewById(R.id.icon_Item);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, CTSPActivity.class);
                    intent.putExtra("sanpham", (products.get(getAdapterPosition()).getId()));

                    intent.putExtra("tensanpham", (products.get(getAdapterPosition()).getName()));

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //check_connection.showToast_SHORT(context,products.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }

}

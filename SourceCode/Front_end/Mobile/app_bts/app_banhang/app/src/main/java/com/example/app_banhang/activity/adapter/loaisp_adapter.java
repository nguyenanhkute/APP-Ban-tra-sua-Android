package com.example.app_banhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_banhang.R;
import com.example.app_banhang.model.loaisp;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class loaisp_adapter extends BaseAdapter {
    ArrayList<loaisp> arrayListLoaisp;
    Context context;

    public loaisp_adapter(ArrayList<loaisp> arrayListLoaisp, Context context) {
        this.arrayListLoaisp = arrayListLoaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLoaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListLoaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        TextView textViewTenLsp;
        ImageView imageViewLsp;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp,null);

            viewHolder.textViewTenLsp = (TextView) view.findViewById(R.id.idtextviewloaisp);
            viewHolder.imageViewLsp = (ImageView) view.findViewById(R.id.idImageviewloaisp);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        loaisp lsp = (loaisp) getItem(i);
        viewHolder.textViewTenLsp.setText(lsp.getTenloaisp());


        Picasso.with(context).load(lsp.getHinhanh())
                .placeholder(R.drawable.hoa)
                .error(R.drawable.qua)
                .into(viewHolder.imageViewLsp);
        return view;
    }
}

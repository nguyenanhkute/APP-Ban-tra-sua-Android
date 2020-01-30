package com.example.app_banhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_banhang.R;
import com.example.app_banhang.activity.GiohangActivity;
import com.example.app_banhang.activity.MainActivity;
import com.example.app_banhang.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class thanhtoanAdapter  extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arraygiohang;

    public thanhtoanAdapter(Context context, ArrayList<GioHang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }
    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        public ImageView imgsanpham;
        public TextView txttensp,txtgiasp, txtsoluong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_thanhtoan, null);
            viewHolder.imgsanpham = (ImageView) convertView.findViewById(R.id.imageviewgiohang);
            viewHolder.txtgiasp = (TextView) convertView.findViewById(R.id.textviewgiatrimonhang);
            viewHolder.txttensp =(TextView) convertView.findViewById(R.id.textviewtengiohang);
            viewHolder.txtsoluong =(TextView) convertView.findViewById(R.id.textviewsoluong);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GioHang giohang = (GioHang) getItem(position);
        viewHolder.txttensp.setText(giohang.getTensp());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtsoluong.setText(decimalFormat.format( giohang.getSoluong()));
        viewHolder.txtgiasp.setText(decimalFormat.format(giohang.getGia()) + "Đ");

        Picasso.get().load(giohang.getHinhanh())
                .error(R.drawable.hoa)
                .into(viewHolder.imgsanpham);

        //
        final ViewHolder finalViewHolder = viewHolder;


        return convertView;
    }

}
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

public class giohangAdapter  extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arraygiohang;

    public giohangAdapter(Context context, ArrayList<GioHang> arraygiohang) {
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
        public TextView txttengiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalue, btnplus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_giohang, null);
            viewHolder.txttengiohang = (TextView) convertView.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang = (TextView) convertView.findViewById(R.id.textviewgiatrimonhang);
            viewHolder.imggiohang = (ImageView) convertView.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus = (Button) convertView.findViewById(R.id.buttonminus);
            viewHolder.btnvalue = (Button) convertView.findViewById(R.id.buttonvalue);
            viewHolder.btnplus = (Button) convertView.findViewById(R.id.buttonplus);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GioHang giohang = (GioHang) getItem(position);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGia()) + "Đ");

        Picasso.get().load(giohang.getHinhanh())
                .error(R.drawable.hoa)
                .into(viewHolder.imggiohang);

        viewHolder.btnvalue.setText(giohang.getSoluong()+"");
        int sl= Integer.parseInt(viewHolder.btnvalue.getText().toString());
        if(sl>=10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }else  if (sl==1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }else if (sl>1){
            viewHolder.btnminus.setVisibility(View.VISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }
        //
        final ViewHolder finalViewHolder = viewHolder;

        //nhan +
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int slmoinhat= Integer.parseInt(finalViewHolder.btnvalue.getText().toString())+1;
                int slhientai= MainActivity.manggiohang.get(position).getSoluong();
                int giaht=MainActivity.manggiohang.get(position).getGia();
                MainActivity.manggiohang.get(position).setSoluong(slmoinhat);
                int giamoinhat= (int)giaht*slmoinhat/slhientai;
                MainActivity.manggiohang.get(position).setGia(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "Đ");//buttonvalue

                GiohangActivity.EventUtil();

                finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));

                if (slmoinhat>9){
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                }else{
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                }
            }
        });

        //nhan -
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int slmoinhat= Integer.parseInt(finalViewHolder.btnvalue.getText().toString())-1;
                int slhientai= MainActivity.manggiohang.get(position).getSoluong();
                int giaht=MainActivity.manggiohang.get(position).getGia();
                MainActivity.manggiohang.get(position).setSoluong(slmoinhat);
                int giamoinhat= (int)giaht*slmoinhat/slhientai;
                MainActivity.manggiohang.get(position).setGia(giamoinhat);

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "Đ");//buttonvalue

                GiohangActivity.EventUtil();

                finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));

                if (slmoinhat==1){
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                }else{
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                }
            }
        });

        return convertView;
    }

}
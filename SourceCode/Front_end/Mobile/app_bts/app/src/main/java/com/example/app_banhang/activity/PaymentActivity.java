package com.example.app_banhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_banhang.R;
import com.example.app_banhang.adapter.thanhtoanAdapter;
import com.example.app_banhang.model.GioHang;
import com.example.app_banhang.util.SessionManager;
import com.example.app_banhang.util.server;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    static ListView lvgiohang;
    static TextView tvTen, tvSDT, tvDiachi,tvShip1, tvTongtien,tvThanhtoan;
    static Button btnthanhtoan;
    static Toolbar toolbargiohang;
    static thanhtoanAdapter giohang;
    static String getId, tongtienthanhtoan, mangsanpham, mangsl;

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);
        Anhxa();
        ActionToolbar();
        EventUtil();
    }



    private void EventButton() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.URL_DATHANG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");

                            if(success.equals("1")) {
                                Toast.makeText(PaymentActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                MainActivity.manggiohang.clear();
                                Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                                startActivity(intent);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PaymentActivity.this,"Đặt hàng that bai" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PaymentActivity.this,"Đặt hàng TB" + error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("makh", getId);
                params.put("tongtien", tongtienthanhtoan);
                params.put("mangsanpham", mangsanpham);
                params.put("mangsoluong", mangsl);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    public static void EventUtil() {
        int tongtien=0;
        for( int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien+=MainActivity.manggiohang.get(i).getGia();
        }
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        tvThanhtoan.setText(decimalFormat.format(tongtien)+" Đ");
    }


    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang = findViewById(R.id.listviewgiohang);
        btnthanhtoan= findViewById(R.id.buttonthanhtoangiohang);
        toolbargiohang= findViewById(R.id.toolbargiohang);
        tvTen = findViewById(R.id.tv_ten);
        tvSDT = findViewById(R.id.tv_sdt);
        tvDiachi = findViewById(R.id.tv_diachi);
        tvThanhtoan = findViewById(R.id.tv_tongtienthanhtoan);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        String getName = user.get(sessionManager.NAME);
        String getPhone = user.get(sessionManager.PHONENUMBER);
        String getAddress = user.get(sessionManager.ADDRESS);

        tvTen.setText(getName);
        tvSDT.setText(getPhone);
        tvDiachi.setText(getAddress);



        giohang = new thanhtoanAdapter(this,MainActivity.manggiohang);
        lvgiohang.setAdapter(giohang);



        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        DecimalFormat decimalFormatInt= new DecimalFormat("");

        int tongtiengiohang = 0;
        for( int i=0;i<MainActivity.manggiohang.size();i++){
            tongtiengiohang+=MainActivity.manggiohang.get(i).getGia();
        }

        tongtienthanhtoan = decimalFormatInt.format(tongtiengiohang);


        String tongtien = decimalFormat.format(tongtiengiohang);
        tvThanhtoan.setText(tongtien);

        mangsanpham = convertArrayToStringMethod(getArrayGioHangMaSp(MainActivity.manggiohang));

        mangsl = convertArrayToStringMethod(getArrayGioHangSoLuong(MainActivity.manggiohang));


        btnthanhtoan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EventButton();
            }
        });

    }
    // Chuyen ArrayList Giohang sang String[] - theo masp
    public String[] getArrayGioHangMaSp(ArrayList<GioHang> arrayList){
        String[] a = new String[MainActivity.manggiohang.size()];

        for( int i=0;i<MainActivity.manggiohang.size();i++) {
            a[i] = MainActivity.manggiohang.get(i).getMasp();
            if(a[i].equals(null)  || a[i].isEmpty())
            {
                break;
            }
        }
        return a;
    }
    // Chuyen ArrayList Giohang sang String[] - theo soluong
    public String[] getArrayGioHangSoLuong(ArrayList<GioHang> arrayList){
        String[] a = new String[MainActivity.manggiohang.size()];
        int temp = 0;
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        for( int i=0;i<MainActivity.manggiohang.size();i++) {
            temp= MainActivity.manggiohang.get(i).getSoluong();
            a[i] = decimalFormat.format(temp);
            if(a[i].equals(null)  || a[i].isEmpty())
            {
                break;
            }
        }
        return a;
    }

    public static String convertArrayToStringMethod(String[] strArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(strArray[i]);
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

}

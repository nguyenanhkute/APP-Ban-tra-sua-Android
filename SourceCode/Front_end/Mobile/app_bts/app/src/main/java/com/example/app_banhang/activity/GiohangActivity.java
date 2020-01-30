package com.example.app_banhang.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
import com.example.app_banhang.adapter.giohangAdapter;
import com.example.app_banhang.util.SessionManager;
import com.example.app_banhang.util.check_connection;
import com.example.app_banhang.util.server;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class GiohangActivity extends AppCompatActivity {

    static ListView lvgiohang;
    static TextView txtthongbao,txttongtien;
    static Button btnthanhtoan,btntieptucmua;
    static Toolbar toolbargiohang;
    static giohangAdapter giohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();
        checkData();
        EventUtil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size()>0) {
                    SessionManager sessionManager = new SessionManager(GiohangActivity.this);
                    boolean temp = sessionManager.isLogin();
                    if(temp == false)
                    {
                        AlertDialog.Builder builder= new AlertDialog.Builder(GiohangActivity.this);
                        builder.setTitle("Bạn chưa đăng nhập");
                        builder.setMessage("Bạn có muốn đăng nhập để thanh toán không ?");
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Intent intent = new Intent(GiohangActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                giohang.notifyDataSetChanged();
                                EventUtil();
                            }
                        });
                        builder.show();
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                        startActivity(intent);
                        /*Toast.makeText(GiohangActivity.this,"Đặt hàng thành công",Toast.LENGTH_SHORT).show();
                        MainActivity.manggiohang.clear();
                        finish();
                        startActivity(getIntent());*/
                    }
                    /*else {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.URL_DATHANG,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(new String(response));
                                            String success = jsonObject.getString("success");

                                            if(success.equals("1")){
                                                Toast.makeText(GiohangActivity.this,"Đặt hàng thành công",Toast.LENGTH_SHORT).show();

                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Toast.makeText(GiohangActivity.this,"Đặt hàng thất bại" + e.toString(),Toast.LENGTH_SHORT).show();

                                            findViewById(R.id.btn_register).setVisibility(View.VISIBLE);
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(GiohangActivity.this,"ĐH TB" + error.toString(),Toast.LENGTH_SHORT).show();

                                        findViewById(R.id.btn_register).setVisibility(View.VISIBLE);
                                    }
                                });
                        *//*{
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("username", username);
                                params.put("email", email);
                                params.put("pass", password);
                                params.put("address", address);
                                params.put("phone", phone);
                                return params;
                            }
                        };*//*

                        RequestQueue requestQueue = Volley.newRequestQueue(GiohangActivity.this);
                        requestQueue.add(stringRequest);

                    }*/
                } else {
                    check_connection.showToast_SHORT(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm");
                }


            }
        });
    }

    private void CatchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int position, long l) {
                AlertDialog.Builder builder= new AlertDialog.Builder(GiohangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (MainActivity.manggiohang.size()<=0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.manggiohang.remove(position);
                            giohang.notifyDataSetChanged();
                            EventUtil();
                            if (MainActivity.manggiohang.size()<=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else{
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohang.notifyDataSetChanged();
                                EventUtil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        giohang.notifyDataSetChanged();
                        EventUtil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUtil() {
        int tongtien=0;
        for( int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien+=MainActivity.manggiohang.get(i).getGia();
        }
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+" Đ");
    }

    private void checkData() {
        if (MainActivity.manggiohang.size()<=0){
            giohang.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);

        }else{
            giohang.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
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
        txtthongbao= findViewById(R.id.textviewthongbao);
        txttongtien= findViewById(R.id.txttongtien);
        btnthanhtoan= findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmua= findViewById(R.id.buttontieptucmuahang);
        toolbargiohang= findViewById(R.id.toolbargiohang);


        giohang = new giohangAdapter(this,MainActivity.manggiohang);
        lvgiohang.setAdapter(giohang);
    }

}

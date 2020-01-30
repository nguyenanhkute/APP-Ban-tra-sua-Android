package com.example.app_banhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_banhang.R;
import com.example.app_banhang.adapter.LSMHAdapter;
import com.example.app_banhang.model.sanpham;
import com.example.app_banhang.util.SessionManager;
import com.example.app_banhang.util.check_connection;
import com.example.app_banhang.util.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class LSMHActivity extends AppCompatActivity {

    static RecyclerView lvlsmh;
    static TextView txtthongbao;
    static Toolbar toolbarlsmh;
    static LSMHAdapter lsmh;
    public static ArrayList<sanpham> mangsanpham ;
    SessionManager sessionManager;

    String makhachhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsumuahang);


        Anhxa();
        ActionToolbar();
        GetDuLieuSanPham();

    }


    private void checkData() {
        if (mangsanpham.size() == 0){
            lsmh.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvlsmh.setVisibility(View.INVISIBLE);

        }else{
            lsmh.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvlsmh.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarlsmh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlsmh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetDuLieuSanPham() {
        String duongdan= server.URL_GETLSMH+makhachhang;
        StringRequest stringRequest= new StringRequest(Request.Method.GET, duongdan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String TenSP="";
                            String HinhAnh="";
                            String Gia= "";
                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("sanpham");

                            if(success.equals("1")){
                                for(int i =0;i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    TenSP= object.getString("tensp");
                                    HinhAnh= object.getString("hinhanh");
                                    Gia= object.getString("gia");
                                    mangsanpham.add(new sanpham(TenSP,HinhAnh,Gia));
                                    lsmh.notifyDataSetChanged();
                                }

                                checkData();

                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                check_connection.showToast_SHORT(getApplicationContext(),error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void Anhxa() {
        lvlsmh = (RecyclerView) findViewById(R.id.listviewlichsumuahang);
        txtthongbao=  (TextView) findViewById(R.id.textviewthongbao);
        toolbarlsmh= (Toolbar) findViewById(R.id.toolbarlichsumuahang);
        mangsanpham = new ArrayList<>();


        lsmh = new LSMHAdapter(this,mangsanpham);
        lvlsmh.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lvlsmh.setLayoutManager(llm);

        lvlsmh.setAdapter(lsmh);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        makhachhang = user.get(sessionManager.ID);


    }

}

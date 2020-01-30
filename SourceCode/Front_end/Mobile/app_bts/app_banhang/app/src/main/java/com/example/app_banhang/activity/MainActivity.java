package com.example.app_banhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_banhang.R;
import com.example.app_banhang.adapter.loaisp_adapter;
import com.example.app_banhang.model.loaisp;
import com.example.app_banhang.util.check_connection;
import com.example.app_banhang.util.server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar1    ;
    ViewFlipper viewFlipper1;
    RecyclerView recyclerView1;
    NavigationView navigationView1;
    ListView listView1;
    DrawerLayout drawerLayout1;

    ArrayList<loaisp> manglsp;
    loaisp_adapter lspadapter;

    ////////////
    String MaLSP="";
    String TenLSP="";
    String HinhAnhLSP="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (check_connection.haveNetworkConnection(getApplicationContext())){

            ActionBar();
            ActionViewFlipper();
            //GetDuLieuLoaiSP();
        } else {
            check_connection.showToast_SHORT(getApplicationContext(),"ban hay kiem tra lai ket noi");
        }

    }
/*
    private void GetDuLieuLoaiSP() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongdanloaiSP, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response!= null){
                    for (int i=0;i<response.length();i++){
                        try{
                            JSONObject jsonObject = response.getJSONObject(i);
                            MaLSP= jsonObject.getString("MaLoaiSP");
                            TenLSP= jsonObject.getString("TenLoaiSP");
                            HinhAnhLSP= jsonObject.getString("HinhAnh");
                            manglsp.add(new loaisp(MaLSP,TenLSP,HinhAnhLSP));
                            lspadapter.notifyDataSetChanged();

                        } catch (JSONException E){
                            E.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                check_connection.showToast_SHORT(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
*/
    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao= new ArrayList<>();
        mangquangcao.add("https://hoayeuthuong.com/hinh-hoa-tuoi/tim-diu-em/4933_shop-hoa-tuoi.jpg");
        mangquangcao.add("https://hoayeuthuong.com/hinh-hoa-tuoi/gio-hoa-hong/5062_cua-hang-hoa.jpg");
        for(int i=0; i<mangquangcao.size();i++){
            ImageView imageView1= new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView1);
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView1);
            imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper1.addView(imageView1);
        }
        viewFlipper1.setFlipInterval(5000);
        viewFlipper1.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper1.setInAnimation(animation_slide_in);
        viewFlipper1.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa(){
        toolbar1 = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper1 = (ViewFlipper) findViewById(R.id.idviewflipper);
        recyclerView1 = (RecyclerView) findViewById(R.id.idrecycler);
        navigationView1 = (NavigationView) findViewById(R.id.idnavigationview);
        listView1 = (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerLayout1 =(DrawerLayout) findViewById(R.id.iddrawerlayout);

        manglsp = new ArrayList<>();
        manglsp.add(0,new loaisp("LSP003","Trang chinh","http://thegioihoahong.com/wp-content/uploads/2018/06/bo-hoa-hong-do-em-la-tat-ca.jpg"));
       lspadapter= new loaisp_adapter(manglsp,getApplicationContext());
       listView1.setAdapter(lspadapter);
    }
}

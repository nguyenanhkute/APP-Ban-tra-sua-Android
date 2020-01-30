package com.example.app_banhang.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_banhang.R;
import com.example.app_banhang.adapter.SearchAdapter;
import com.example.app_banhang.adapter.sanpham_adapter;
import com.example.app_banhang.adapter.sanpham_lsp_Adapter;
import com.example.app_banhang.model.Product;
import com.example.app_banhang.model.loaisp;
import com.example.app_banhang.model.sanpham;
import com.example.app_banhang.util.ApiClient;
import com.example.app_banhang.util.ApiInterface;
import com.example.app_banhang.util.SessionManager;
import com.example.app_banhang.util.check_connection;
import com.example.app_banhang.util.server;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SanphamActivity extends AppCompatActivity {
    // search
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Product> productList;
    private SearchAdapter searchAdapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;
    SearchView searchView;
    // es
    Toolbar toolbarsp;

    BottomNavigationView bottomNavigationView;
    RecyclerView recyclerViewlspsp;
    ArrayList<sanpham> manglsp_sp;
    sanpham_adapter lsp_spAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sanpham);
        Intent intent = getIntent();
        String tenlsp= intent.getStringExtra("loaisanpham");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmanhinhsanpham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tenlsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


        GetDuLieuSanPham(tenlsp);
        Anhxa();
        if (check_connection.haveNetworkConnection(getApplicationContext())){
            // GetDuLieuSanPham();
        } else {
            check_connection.showToast_SHORT(getApplicationContext(),"ban hay kiem tra lai ket noi");
        }
    }

    private void Anhxa() {
        // Search
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        // es
        toolbarsp = (Toolbar) findViewById(R.id.toolbarmanhinhsanpham);

        recyclerViewlspsp = (RecyclerView) findViewById(R.id.idrecyclerlspsp);

        manglsp_sp = new ArrayList<>();
        lsp_spAdapter= new sanpham_adapter(getApplicationContext(),manglsp_sp);

        recyclerViewlspsp.setHasFixedSize(true);
        recyclerViewlspsp.setLayoutManager( new GridLayoutManager(getApplicationContext(),2));
        recyclerViewlspsp.setAdapter(lsp_spAdapter);//add spAdapter vao recycleview-> sau do dua vao mangsp

        // Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

    }
    //Search
    public void fetchProduct(String key)
    {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getProduct(key);

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, retrofit2.Response<List<Product>> response) {
                progressBar.setVisibility(View.GONE);

                if(key.equals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
                {
                    LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    //
                    recyclerView.setLayoutParams(lay);
                }else
                {
                    // Set list search
                    LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    //
                    recyclerView.setLayoutParams(lay);
                }

                productList = response.body();
                searchAdapter = new SearchAdapter(productList, SanphamActivity.this);
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SanphamActivity.this, "Error on:" +t.toString() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        inflater.inflate(R.menu.menu,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.itemSearch).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconified(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //fetchProduct(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty()) {
                    fetchProduct(newText);
                }else{

                    fetchProduct("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }
                return false;
            }



        });



        return true;
    }

    // End Search
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetDuLieuSanPham(String tenlsp) {


        String duongdan= server.DuongdanLSPSP+tenlsp;
        StringRequest stringRequest= new StringRequest(Request.Method.GET, duongdan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String MaSP ="";
                            String TenSP="";
                            String HinhAnh="";
                            String MaLoaiSP="";
                            Integer Gia= 0;
                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("LoaiSPsanpham");

                            if(success.equals("1")){
                                for(int i =0;i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    MaSP= object.getString("masp");
                                    TenSP= object.getString("tensp");
                                    HinhAnh= object.getString("hinhanh");
                                    MaLoaiSP = object.getString("maloaisp");
                                    Gia= object.getInt("gia");
                                    manglsp_sp.add(new sanpham(MaSP,TenSP,HinhAnh,MaLoaiSP,Gia));
                                    lsp_spAdapter.notifyDataSetChanged();
                                }
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
    //Bottom Nav
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId())
                    {
                        case R.id.nav_home:
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_coupon:
                            break;
                        case R.id.nav_profile:
                            SessionManager sessionManager = new SessionManager(SanphamActivity.this);
                            boolean temp = sessionManager.isLogin();

                            if(temp == true)
                            {
                                intent = new Intent(SanphamActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            }else{

                                AlertDialog.Builder builder= new AlertDialog.Builder(SanphamActivity.this);
                                builder.setTitle("Bạn chưa đăng nhập");
                                builder.setMessage("Bạn có muốn đăng nhập để tiếp tục mua hàng không ?");
                                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        Intent intent = new Intent(SanphamActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                    }
                                });
                                builder.show();

                            }
                    }

                    return true;
                }
            };
}

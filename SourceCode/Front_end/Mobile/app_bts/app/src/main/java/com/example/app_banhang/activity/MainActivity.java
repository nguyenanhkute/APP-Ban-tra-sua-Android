package com.example.app_banhang.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_banhang.R;
import com.example.app_banhang.adapter.SearchAdapter;
import com.example.app_banhang.adapter.loaisp_adapter;
import com.example.app_banhang.adapter.sanpham_adapter;
import com.example.app_banhang.adapter.sanpham_lsp_Adapter;
import com.example.app_banhang.adapter.toolbar_adapter;
import com.example.app_banhang.model.GioHang;
import com.example.app_banhang.model.Product;
import com.example.app_banhang.model.loaisp;
import com.example.app_banhang.model.sanpham;
import com.example.app_banhang.util.ApiClient;
import com.example.app_banhang.util.ApiInterface;
import com.example.app_banhang.util.SessionManager;
import com.example.app_banhang.util.check_connection;
import com.example.app_banhang.util.server;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    // search
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Product> productList;
    private SearchAdapter searchAdapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;
    // es
    Toolbar toolbar1;
    ViewFlipper viewFlipper1;
    RecyclerView recyclerViewlsp;
    RecyclerView recyclerViewsp;
    NavigationView navigationView1;
    ListView listView1;
    DrawerLayout drawerLayout1;
    BottomNavigationView bottomNavigationView;
    toolbar_adapter tbarAdapter;
    ArrayList<loaisp> manglsp;
    loaisp_adapter lspadapter;

    ArrayList<sanpham> mang_sp;
    sanpham_adapter spAdapter;
    public static ArrayList<GioHang> manggiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Anhxa();
       // ActionBar();
        ActionViewFlipper();
        GetDuLieuLoaiSP();


        GetDuLieuSanPham();


    }

    private void CatchOnItemListView() {
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int i, long l){
                switch (i){
                    case 0:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else
                        {
                            check_connection.showToast_SHORT(getApplicationContext(),"ban hay kiem tra lai ket noi");
                        }
                        drawerLayout1.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                            startActivity(intent);
                        } else
                        {
                            check_connection.showToast_SHORT(getApplicationContext(),"ban hay kiem tra lai ket noi");
                        }
                        drawerLayout1.closeDrawer(GravityCompat.START);
                    case 2:
                        if(check_connection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                            startActivity(intent);
                        } else
                        {
                            check_connection.showToast_SHORT(getApplicationContext(),"ban hay kiem tra lai ket noi");
                        }
                        drawerLayout1.closeDrawer(GravityCompat.START);


                }
            }
        });
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
                searchAdapter = new SearchAdapter(productList, MainActivity.this);
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error on:" +t.toString() , Toast.LENGTH_SHORT).show();
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
    private void GetDuLieuSanPham() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server.Duongdansanpham,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("sanpham");

                            if(success.equals("1")){
                                for(int i =0;i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String MaSP= object.getString("masp");
                                    String TenSP= object.getString("tensp");
                                    String HinhAnh= object.getString("hinhanh");
                                    String MaLoaiSP = object.getString("maloaisp");
                                    Integer Gia= object.getInt("gia");
                                    mang_sp.add(new sanpham(MaSP,TenSP,HinhAnh,MaLoaiSP,Gia));
                                    spAdapter.notifyDataSetChanged();
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


    private void GetDuLieuLoaiSP() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, server.DuongdanloaiSP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String MaLSP="";
                            String TenLSP="";
                            String HinhAnhLSP="";
                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("loaisanpham");

                            if(success.equals("1")){
                                for(int i =0;i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    MaLSP= object.getString("maloaisp");
                                    TenLSP= object.getString("tenloaisp");
                                    HinhAnhLSP= object.getString("hinhanh");
                                    manglsp.add(new loaisp(MaLSP,TenLSP,HinhAnhLSP));
                                    lspadapter.notifyDataSetChanged();
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

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao= new ArrayList<>();
        mangquangcao.add("https://media.vietteltelecom.vn/upload/ckfinder/images/525x351_khuyemai_trasua.jpg");
        mangquangcao.add("https://vinid.net/wp-content/uploads/2019/07/20190717_ViniD_HotDeal_Toco_BannerApp_1920x1080.jpg");
        mangquangcao.add("https://theme.hstatic.net/1000291153/1000377978/14/banner_collection.jpg?v=518");
        mangquangcao.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTIbcg3VYvWS_WIC3J3Sz_bXxHWn4QrL8uLXAzIYSTVBvVKlPO&s");
        for(int i=0; i<mangquangcao.size();i++){
            ImageView imageView1= new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView1);
            Picasso.get().load(mangquangcao.get(i)).into(imageView1);
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

    /*private void ActionBar() {
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                drawerLayout1.openDrawer(GravityCompat.START);
            }
        });
    }*/
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
                            SessionManager sessionManager = new SessionManager(MainActivity.this);
                            boolean temp = sessionManager.isLogin();

                            if(temp == true)
                            {
                                intent = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            }else{

                                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Bạn chưa đăng nhập");
                                builder.setMessage("Bạn có muốn đăng nhập để tiếp tục mua hàng không ?");
                                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
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

    private void Anhxa(){
        // Search
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        // es
        toolbar1 = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);

        viewFlipper1 = (ViewFlipper) findViewById(R.id.idviewflipper);

        recyclerViewlsp = (RecyclerView) findViewById(R.id.idrecyclerlsp);
        recyclerViewsp = (RecyclerView) findViewById(R.id.idrecyclersp);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    /*    navigationView1 = (NavigationView) findViewById(R.id.idnavigationview);
        listView1 = (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerLayout1 =(DrawerLayout) findViewById(R.id.iddrawerlayout);*/

        //load tool bar
        /*ArrayList<listview_danhmuc> mangtoolbar = new ArrayList<>();
        mangtoolbar.add(new listview_danhmuc("Danh mục", R.drawable.qua));
        mangtoolbar.add(new listview_danhmuc("Cá nhân", R.drawable.user1));

        tbarAdapter = new toolbar_adapter(mangtoolbar,this);
        listView1.setAdapter(tbarAdapter);
        // Sự kiện toolbar
        listView1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        Bundle bundle = new Bundle();
                        listview_danhmuc danhmuc = mangtoolbar.get(arg2);
                        if(danhmuc.getTendanhmuc().equals("Danh mục"))
                        {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        if(danhmuc.getTendanhmuc().equals("Cá nhân"))
                        {
                            SessionManager sessionManager = new SessionManager(MainActivity.this);
                            boolean temp = sessionManager.isLogin();
                            if(temp == true)
                            {
                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }


                        }
                       *//* if(danhmuc.getTendanhmuc().equals("Loại sản phẩm"))
                        {
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }*//*

                    }
                });*/


        //get loai san pham
        manglsp = new ArrayList<loaisp>();
        lspadapter= new loaisp_adapter(manglsp,getApplicationContext());
        recyclerViewlsp.setLayoutManager( new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewlsp.setAdapter(lspadapter);//add spAdapter vao recycleview-> sau do dua vao manglsp



        //get san pham
        mang_sp = new ArrayList<sanpham>();
        spAdapter= new sanpham_adapter(getApplicationContext(),mang_sp);

        recyclerViewsp.setLayoutManager( new GridLayoutManager(getApplicationContext(),2));
         recyclerViewsp.setAdapter(spAdapter);//add spAdapter vao recycleview-> sau do dua vao mangsp


        // Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


        //Gio hang
        if (manggiohang!= null){

        }else{
            manggiohang= new ArrayList<>();
        }

    }
}

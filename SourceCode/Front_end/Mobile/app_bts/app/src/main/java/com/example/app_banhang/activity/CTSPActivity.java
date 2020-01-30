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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import com.example.app_banhang.adapter.SearchAdapter;
import com.example.app_banhang.adapter.phanhoiAdapter;
import com.example.app_banhang.model.GioHang;
import com.example.app_banhang.model.Product;
import com.example.app_banhang.model.phanhoi;
import com.example.app_banhang.util.ApiClient;
import com.example.app_banhang.util.ApiInterface;
import com.example.app_banhang.util.SessionManager;
import com.example.app_banhang.util.check_connection;
import com.example.app_banhang.util.server;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CTSPActivity extends AppCompatActivity {
    // search
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Product> productList;
    private SearchAdapter searchAdapter;
    private ApiInterface apiInterface;

    ProgressBar progressBar;
    SearchView searchView;
    // es

    // phản hồi
    static ArrayList<phanhoi> phanhoiArrayList;
    phanhoiAdapter phanhoi_Adapter;
    RecyclerView recyclerViewPhanhoi;

    Toolbar toolbarctsp;
    BottomNavigationView bottomNavigationView;

    ImageView imageView;
    TextView  txttensp, txtgia, txtmota;
    Spinner spinnerSL;
    Button btndatmua;

    String MaSP ="";
    String TenSP="";
    String HinhAnh="";
    String MaLoaiSP="";
    Integer Gia= 0;
    String MoTa = "";
    String tensp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    /*    requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        setContentView(R.layout.activity_ctsp);
        Intent intent = getIntent();
        tensp= intent.getStringExtra("tensanpham");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tensp);



        Anhxa();
        ActionToolbar();

        GetDSPH(tensp);
        GetCTSP();
        CatchEventSpinner();

        //bat su kien them vao gio hang
        EventButton();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        String msp= MaSP;
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int sl= Integer.parseInt(spinnerSL.getSelectedItem().toString());
                if (MainActivity.manggiohang.size()>0){

                    boolean exist= false;
                    for (int i=0; i<MainActivity.manggiohang.size();i++){
                        if (MainActivity.manggiohang.get(i).getMasp().equals(MaSP)){
                            MainActivity.manggiohang.get(i).setSoluong(MainActivity.manggiohang.get(i).getSoluong()+sl);
                            if(MainActivity.manggiohang.get(i).getSoluong()>10){
                                MainActivity.manggiohang.get(i).setSoluong(10);
                            }
                            MainActivity.manggiohang.get(i).setGia(Gia*MainActivity.manggiohang.get(i).getSoluong());
                            exist= true;
                        }
                    }

                    if (exist== false){
                        int giatien= sl*Gia;
                        MainActivity.manggiohang.add(new GioHang(MaSP,TenSP,HinhAnh,giatien,sl));
                    }
                }else {
                    int giatien= sl*Gia;
                    MainActivity.manggiohang.add(new GioHang(MaSP,TenSP,HinhAnh,giatien,sl));
                }
                Intent intent =  new Intent(getApplicationContext(),GiohangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong= new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,soluong);
        spinnerSL.setAdapter(arrayAdapter);
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
                searchAdapter = new SearchAdapter(productList, CTSPActivity.this);
                recyclerView.setAdapter(searchAdapter);
                searchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(CTSPActivity.this, "Error on:" +t.toString() , Toast.LENGTH_SHORT).show();
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
    private void GetCTSP() {
        Intent intent = getIntent();
        String masp= intent.getStringExtra("sanpham");

        String duongdan= server.DuongdansanphamMaSP+masp;
        StringRequest stringRequest= new StringRequest(Request.Method.GET, duongdan,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("MaSPsanpham");

                            if(success.equals("1")){
                                    JSONObject object = jsonArray.getJSONObject(0);

                                    MaSP= object.getString("masp");
                                    TenSP= object.getString("tensp");
                                    HinhAnh= object.getString("hinhanh");
                                    MaLoaiSP = object.getString("maloaisp");
                                    Gia= object.getInt("gia");
                                    MoTa = object.getString("mota");

                                    txttensp.setText(TenSP);
                                    txtmota.setText(MoTa);

                                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                                    txtgia.setText("Giá: "+decimalFormat.format(Gia)+" Đ");


                                    Picasso.get().load(HinhAnh)
                                        .error(R.drawable.hoa)
                                        .into(imageView);

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
    private void GetDSPH(String tensp) {


        String duongdan2= server.URL_GETPhanHoi+tensp;
        StringRequest stringRequest= new StringRequest(Request.Method.GET, duongdan2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            String TenKhachHang ="";
                            String TenSP="";
                            String HinhAnh="";
                            String Mota="";
                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("phanhoi");

                            if(success.equals("1")){
                                for(int i =0;i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    TenKhachHang= object.getString("tenkh");
                                    TenSP= object.getString("tensp");
                                    HinhAnh= object.getString("hinhanh");
                                    Mota = object.getString("mota");

                                    phanhoiArrayList.add(new phanhoi(TenSP,TenKhachHang,HinhAnh,Mota));
                                    phanhoi_Adapter.notifyDataSetChanged();

                                }
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CTSPActivity.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CTSPActivity.this,"Lỗi" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarctsp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarctsp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                            SessionManager sessionManager = new SessionManager(CTSPActivity.this);
                            boolean temp = sessionManager.isLogin();

                            if(temp == true)
                            {
                                intent = new Intent(CTSPActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            }else{

                                AlertDialog.Builder builder= new AlertDialog.Builder(CTSPActivity.this);
                                builder.setTitle("Bạn chưa đăng nhập");
                                builder.setMessage("Bạn có muốn đăng nhập để tiếp tục mua hàng không ?");
                                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        Intent intent = new Intent(CTSPActivity.this,LoginActivity.class);
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

    private void Anhxa() {
        // Search
        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        // es
        toolbarctsp = (Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imageView = (ImageView) findViewById(R.id.imageviewchitietsanpham);
        recyclerViewPhanhoi = (RecyclerView) findViewById(R.id.lv_danhgia);

        txttensp= findViewById(R.id.textviewtenchitietsanpham);
        txtgia= findViewById(R.id.textviewgiachitietsanpham);
        txtmota= findViewById(R.id.textviewmotachitietsanpham);
        spinnerSL= findViewById(R.id.idspiner);
        btndatmua= findViewById(R.id.buttondatmua);
        // Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


        // Phản hồi

        phanhoiArrayList = new ArrayList<>();
        phanhoi_Adapter= new phanhoiAdapter(CTSPActivity.this,phanhoiArrayList);
        recyclerViewPhanhoi.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPhanhoi.setLayoutManager(llm);
        recyclerViewPhanhoi.setAdapter(phanhoi_Adapter);




    }
}

package com.example.app_banhang.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_banhang.R;
import com.example.app_banhang.util.SessionManager;
import com.example.app_banhang.util.server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class DanhgiaActivity extends AppCompatActivity {
    ImageView imHinh;
    EditText txtdanhgia;
    Button btndongy, btnhuy,btntupload;
    RadioGroup radioGroupDanhgia;
    int REQUEST_COST_CAMERA = 123;

    static String getId, tensp, hinh;
    SessionManager sessionManager;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhgia);
        btntupload = (Button) findViewById(R.id.buttonupload);
        imHinh = (ImageView) findViewById(R.id.imageviewdanhgiasp);
        btndongy = (Button) findViewById(R.id.buttondongy);
        btnhuy = (Button) findViewById(R.id.buttonhuy);
        txtdanhgia = (EditText)(findViewById(R.id.edt_danhgia));


        // Lấy tên sp từ LSMH
        Intent intent = getIntent();
        tensp= intent.getStringExtra("tensanpham");

        // Lấy id KH
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);

        btntupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_COST_CAMERA);
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), CTSPActivity.class);
                intent.putExtra("tensanpham", tensp);
                //check_connection.showToast_SHORT(context,arraysanpham.get(getAdapterPosition()).getMasp());
                startActivity(intent);
            }
        });
        btndongy.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Danhgia();

            }
        });
    }

    public void Danhgia( ){
        String motaph = txtdanhgia.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.URL_PhanHoi,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");

                            if(success.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Đánh giá thành công", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Đánh giá that bai" + e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Mời nhập nội dung và chọn hình " ,Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                hinh = getStringImage(bitmap);
                params.put("tensp", tensp);
                params.put("makh", getId);
                params.put("mota", motaph);
                params.put("hinhanh", hinh);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public String getStringImage(Bitmap bitmap )
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return  temp;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_COST_CAMERA && resultCode == RESULT_OK && data != null)
        {
            bitmap = (Bitmap) data.getExtras().get("data");
            imHinh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);


    }




}

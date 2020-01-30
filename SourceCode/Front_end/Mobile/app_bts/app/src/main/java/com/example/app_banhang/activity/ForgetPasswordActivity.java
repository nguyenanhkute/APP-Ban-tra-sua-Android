package com.example.app_banhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_banhang.R;
import com.example.app_banhang.util.server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword,editTextPhoneNumber, editTextPasswordAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_quenmatkhau);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhoneNumber = findViewById(R.id.editTextPhone);
        editTextPasswordAgain = findViewById(R.id.editTextPasswordAgain);





        findViewById(R.id.checkbox).setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }
                return true;
            }
        });
        findViewById(R.id.checkbox1).setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {

                    case MotionEvent.ACTION_UP:
                        editTextPasswordAgain.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        editTextPasswordAgain.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }
                return true;
            }
        });

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ForgetPasswordActivity.this, LoginActivity.class));
            }
        });

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String phone = editTextPhoneNumber.getText().toString().trim();
        String pass2 = editTextPasswordAgain.getText().toString().trim();
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";;

        if( email.equals("") || password.equals("")  || phone.equals("") || pass2.equals(""))
        {
            Toast.makeText(ForgetPasswordActivity.this,"Mời nhập đủ thông tin" ,Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(password.equals(pass2) && email.matches(emailPattern))
            {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server.URL_FORGETPASSWORD,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(new String(response));
                                    String success = jsonObject.getString("success");

                                    if(success.equals("1")){
                                        editTextEmail.getText().clear();
                                        editTextPassword.getText().clear();
                                        editTextPhoneNumber.getText().clear();
                                        editTextPasswordAgain.getText().clear();

                                        Toast.makeText(ForgetPasswordActivity.this,"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                                    }
                                    else if(success.equals("2")){
                                        Toast.makeText(ForgetPasswordActivity.this,"Email không tồn tại",Toast.LENGTH_SHORT).show();
                                    }
                                    else if(success.equals("3")){
                                        Toast.makeText(ForgetPasswordActivity.this,"Số điện thoại không tồn tại",Toast.LENGTH_SHORT).show();
                                    }
                                    else if(success.equals("0")){
                                        Toast.makeText(ForgetPasswordActivity.this,"Email hoặc Số điện thoại không đúng",Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(ForgetPasswordActivity.this,"Thay đổi mật khẩu thất bại" + e.toString(),Toast.LENGTH_SHORT).show();

                                    findViewById(R.id.btn_register).setVisibility(View.VISIBLE);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ForgetPasswordActivity.this,"Dang ki TB" + error.toString(),Toast.LENGTH_SHORT).show();

                                findViewById(R.id.btn_register).setVisibility(View.VISIBLE);
                            }
                        })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("pass", password);
                        params.put("phone", phone);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);

            }
            else if (!email.matches(emailPattern)){
                Toast.makeText(ForgetPasswordActivity.this,"Chưa đúng định dạng Email" ,Toast.LENGTH_SHORT).show();
            }
            else if (!password.equals(pass2)){
                Toast.makeText(ForgetPasswordActivity.this,"Hai mật khẩu không trùng khớp!" ,Toast.LENGTH_SHORT).show();
            }

        }






    }
}

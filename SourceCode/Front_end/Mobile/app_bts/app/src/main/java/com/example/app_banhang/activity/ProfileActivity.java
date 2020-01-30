package com.example.app_banhang.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import com.example.app_banhang.model.GioHang;
import com.example.app_banhang.util.SessionManager;
import com.example.app_banhang.util.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    EditText editTextAddress, editTextUsername, editTextEmail, editTextNumberPhone;
    String getId;
    private static final String TAG = ProfileActivity.class.getSimpleName();
    SessionManager sessionManager;
    private  Menu action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        init();
    }

    void init(){
        editTextAddress = (EditText) findViewById(R.id.edtTextAddress);
        editTextEmail = (EditText) findViewById(R.id.edtTextEmail);
        editTextNumberPhone =(EditText) findViewById(R.id.edtTextNumberPhone);
        editTextUsername = (EditText) findViewById(R.id.edtTextUsername);
        //getting the current user

        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);



        //when the user presses logout button calling the logout method
        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.manggiohang.clear();
                sessionManager.logout();

            }
        });
        findViewById(R.id.tv_lsmh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LSMHActivity.class));
                finish();

            }
        });

        findViewById(R.id.tv_changepassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(ProfileActivity.this);
                View customDialogView = getLayoutInflater().inflate(R.layout.dialog_thaydoimatkhau,null);

                final EditText editTextPass0 = (EditText) customDialogView.findViewById(R.id.pass0);
                final EditText editTextPass1 = (EditText) customDialogView.findViewById(R.id.pass1);
                final EditText editTextPass2 = (EditText) customDialogView.findViewById(R.id.pass2);

                customDialogView.findViewById(R.id.checkbox0).setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {

                        switch ( event.getAction() ) {

                            case MotionEvent.ACTION_UP:
                                editTextPass0.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                break;

                            case MotionEvent.ACTION_DOWN:
                                editTextPass0.setInputType(InputType.TYPE_CLASS_TEXT);
                                break;

                        }
                        return true;
                    }
                });
                customDialogView.findViewById(R.id.checkbox1).setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {

                        switch ( event.getAction() ) {

                            case MotionEvent.ACTION_UP:
                                editTextPass1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                break;

                            case MotionEvent.ACTION_DOWN:
                                editTextPass1.setInputType(InputType.TYPE_CLASS_TEXT);
                                break;

                        }
                        return true;
                    }
                });
                customDialogView.findViewById(R.id.checkbox2).setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {

                        switch ( event.getAction() ) {

                            case MotionEvent.ACTION_UP:
                                editTextPass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                break;

                            case MotionEvent.ACTION_DOWN:
                                editTextPass2.setInputType(InputType.TYPE_CLASS_TEXT);
                                break;

                        }
                        return true;
                    }
                });


                Button btn_ok = (Button) customDialogView.findViewById(R.id.btn_ok);
                Button btn_cancel = (Button) customDialogView.findViewById(R.id.btn_cancel);

                alert.setView(customDialogView);
                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                btn_cancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


                btn_ok.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        String pass0 = editTextPass0.getText().toString().trim();
                        String pass1 = editTextPass1.getText().toString().trim();
                        String pass2 = editTextPass2.getText().toString().trim();
                        if(pass1.equals(pass2)){

                            final ProgressDialog progressDialog = new ProgressDialog(ProfileActivity.this);
                            progressDialog.setMessage("Loadingg....");
                            progressDialog.show();
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, server.URL_CHANGEPASSWORD,
                                    new Response.Listener<String>() {


                                        @Override
                                        public void onResponse(String response) {
                                            progressDialog.dismiss();
                                            Log.i(TAG, response.toString());

                                            try{
                                                JSONObject jsonObject = new JSONObject(new String(response));
                                                String success = jsonObject.getString("success");

                                                if(success.equals("1")){

                                                    Toast.makeText(ProfileActivity.this,"Thay đổi mật khẩu thành công!" ,Toast.LENGTH_SHORT).show();
                                                }
                                                if(success.equals("2")){

                                                    Toast.makeText(ProfileActivity.this,"Mật khẩu cũ không đúng" ,Toast.LENGTH_SHORT).show();
                                                }

                                            }catch (JSONException e){
                                                e.printStackTrace();
                                                Toast.makeText(ProfileActivity.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            }

                                        }

                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(ProfileActivity.this,"Error" + error.toString(),Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                            findViewById(R.id.buttonLogin).setVisibility(View.VISIBLE);
                                        }
                                    })
                            {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", getId);
                                    params.put("pass", pass1);
                                    params.put("passold", pass0);
                                    return params;
                                }
                            };

                            RequestQueue requestQueue = Volley.newRequestQueue(ProfileActivity.this );
                            requestQueue.add(stringRequest);
                        }
                        else{
                            Toast.makeText(ProfileActivity.this,"Hai mật khẩu không giống nhau" ,Toast.LENGTH_SHORT).show();
                        }


                        alertDialog.dismiss();
                    }

                });

                alertDialog.show();
            }

        });
    }

    void getUserDetail() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loadingg....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, server.URL_READ+getId,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());

                        try{
                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if(success.equals("1")){
                                for(int i =0;i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    String strPhone = object.getString("phone").trim();
                                    String strAddress = object.getString("address").trim();

                                    editTextUsername.setText(strName);
                                    editTextAddress.setText(strAddress);
                                    editTextEmail.setText(strEmail);
                                    editTextNumberPhone.setText(strPhone);

                                }

                            }
                            else
                            {
                                Toast.makeText(ProfileActivity.this,"Error!" ,Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this,"Error" + error.toString(),Toast.LENGTH_SHORT).show();

                        findViewById(R.id.buttonLogin).setVisibility(View.VISIBLE);
                    }
                })
        {
           /* @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                return params;
            }*/
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action, menu);
        inflater.inflate(R.menu.menu_home,menu);

        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);
        action.findItem(R.id.menu_edit).setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_edit:
                editTextUsername.setFocusableInTouchMode(true);
                editTextNumberPhone.setFocusableInTouchMode(true);
                editTextEmail.setFocusableInTouchMode(true);
                editTextAddress.setFocusableInTouchMode(true);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextUsername, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:
                SaveEditDetail();

                action.findItem(R.id.menu_edit).setVisible(true);
                action.findItem(R.id.menu_save).setVisible(false);

                editTextUsername.setFocusableInTouchMode(false);
                editTextAddress.setFocusableInTouchMode(false);
                editTextEmail.setFocusableInTouchMode(false);
                editTextNumberPhone.setFocusableInTouchMode(false);

                editTextUsername.setFocusable(false);
                editTextAddress.setFocusable(false);
                editTextEmail.setFocusable(false);
                editTextNumberPhone.setFocusable(false);

                return true;
            case R.id.menu_home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void SaveEditDetail() {
        final String name = this.editTextUsername.getText().toString().trim();
        final String email = this.editTextEmail.getText().toString().trim();
        final String phone = this.editTextNumberPhone.getText().toString().trim();
        final String address = this.editTextAddress.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving....");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server.URL_EDIT,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());

                        try{
                            JSONObject jsonObject = new JSONObject(new String(response));
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){

                                Toast.makeText(ProfileActivity.this,"Succes!" ,Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(name,email,id, phone,address);
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this,"Error" + e.toString(),Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this,"Error" + error.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        findViewById(R.id.buttonLogin).setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getId);
                params.put("name", name);
                params.put("phone", phone);
                params.put("email", email);
                params.put("address", address);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

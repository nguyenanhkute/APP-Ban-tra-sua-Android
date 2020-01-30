package com.example.app_banhang.util;

import com.example.app_banhang.model.Product;
import com.example.app_banhang.model.sanpham;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("search.php")
    Call<List<Product>> getProduct (@Query("key") String keyword);
}

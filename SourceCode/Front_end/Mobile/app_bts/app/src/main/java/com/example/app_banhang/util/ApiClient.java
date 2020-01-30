package com.example.app_banhang.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit;

    public  static Retrofit getApiClient(){
        if(retrofit ==  null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(server.URL_SEARCH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return  retrofit;
    }
}

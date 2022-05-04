package com.cookandroid.capstone_front_android.network;

import java.net.CookieManager;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private final static String BASE_URL = "http://192.168.25.30:8080";
    private static Retrofit retrofit = null;

    private RetrofitClient() {
    }


    public static Retrofit getClient() {


        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
        }

        return retrofit;
    }
}

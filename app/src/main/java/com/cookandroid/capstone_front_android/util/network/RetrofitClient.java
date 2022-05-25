package com.cookandroid.capstone_front_android.util.network;

import android.webkit.CookieManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public final static String BASE_URL = "http://192.168.25.30:8080";
//    public final static String BASE_URL = "http://10.0.2.2:8080";

    // 로그인 인터셉터를 탑재하기 위한 OkHttp 클라이언트 제작 메소드
    private static OkHttpClient provideOkHttpClient(LoginInterceptor loginInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loginInterceptor)
                .build();
    }

    // 로그인 정보 없이 레트로핏 객체를 가져오고 싶을 때 (ex. 최초 로그인시, 회원 가입시)
    public static <T> T getClient(Class<T> api) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api);
    }

    // 로그인 정보를 가져와서 사용해야 할 때
    public static <T> T getClient(Class<T> api, String sessionId) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient(new LoginInterceptor(sessionId)))
                .build()
                .create(api);
    }

    // 쿠키매니저에서 세션값 가져오기
    public static String getSessionId() {
        return CookieManager.getInstance().getCookie(BASE_URL);
    }
}

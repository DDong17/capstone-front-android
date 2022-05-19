package com.cookandroid.capstone_front_android.util.network;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
인터셉터란?
클라이언트에서 서버로 HTTP 요청을 보내기전에, (즉 레트로핏 enqueue 메소드를 호출 하기 전에)
HTTP 메시지에 특정 로직을 추가해주는 역할의 객체

우리는 모든 요청을 서버로 보내기전에 헤더에 서버로부터 받은 사용자 로그인 정보 (세션값)을 부착해서 요청을 보내야함
그래야 서버에서는 이걸 감지하고 이 사람이 로그인이 되있는 유저인지, 아닌지 판독후 서버 로직을 수행 함
 */
public class LoginInterceptor implements Interceptor {

    // 쿠키매니저로부터 가져온 토큰값을 저장
    private final String tokenFromCookieManager;

    // 로그인 인터셉터를 생성할때 해당 토큰(세션값)을 넣어줌
    public LoginInterceptor(String tokenFromCookieManager) {
        Log.d("interceptor Session", tokenFromCookieManager);
        this.tokenFromCookieManager = tokenFromCookieManager;
    }

    // 헤더에 cookie : 세션값 으로 이제 모든 요청을 보낼때 HTTP 헤더값에 이 페어를 추가해서 보내줌
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("cookie", tokenFromCookieManager)
                .build();
        chain.proceed(request);
        return null;
    }
}

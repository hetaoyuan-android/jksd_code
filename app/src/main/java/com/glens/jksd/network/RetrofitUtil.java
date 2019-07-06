package com.glens.jksd.network;

import android.content.Context;
import android.util.Log;

import com.glens.jksd.PowerApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private Context mContext;
    //声明Retrofit对象
    private Retrofit mRetrofit;
    //声明RetrofitApiService对象
    private RetrofitApiService retrofitApiService;
    private OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

    //由于该对象会被频繁调用，采用单例模式，下面是一种线程安全模式的单例写法
    private volatile static RetrofitUtil instance;

    public static RetrofitUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (RetrofitUtil.class) {
                if (instance == null) {
                    instance = new RetrofitUtil(context);
                }
            }
        }
        return instance;
    }

    private RetrofitUtil(Context context) {
        mContext = context;
        init();
    }


    private void init() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("jksd请求","message> " +message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Accept-Encoding", "gzip, deflate")
                    .addHeader("Cookie", "yan yu lan shan")
                    .addHeader("Connection", "keep-alive")
                    .addHeader("token",PowerApplication.getToken())
                    .build();
            Log.e("RetrofitUtil", "调用了 client  Token值是 = " + "Bearer " + PowerApplication.getToken());
            return chain.proceed(request);
        });

        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(interceptor);
        OkHttpClient client = httpClientBuilder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitApiService = mRetrofit.create(RetrofitApiService.class);
    }

    public RetrofitApiService getRetrofitApiService() {
        return retrofitApiService;
    }
}

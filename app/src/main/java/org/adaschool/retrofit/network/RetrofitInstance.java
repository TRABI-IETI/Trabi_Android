package org.adaschool.retrofit.network;

import android.content.SharedPreferences;

import org.adaschool.retrofit.network.auth.JWTInterceptor;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "http://192.168.1.13:8082/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(SharedPreferences sharedPreferences) {
        if (retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(new JWTInterceptor(sharedPreferences))
                    .writeTimeout(0, TimeUnit.MILLISECONDS)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(1, TimeUnit.MINUTES).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
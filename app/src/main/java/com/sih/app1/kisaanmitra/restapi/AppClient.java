package com.sih.app1.kisaanmitra.restapi;

import android.content.Context;
import android.util.Log;


import com.sih.app1.kisaanmitra.BuildConfig;
import com.sih.app1.kisaanmitra.utils.AppConstants;
import com.sih.app1.kisaanmitra.utils.TinyDB;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

    private static AppClient mInstance;

    private AppClient() {
    }

    public static synchronized AppClient getInstance() {
        if (mInstance == null) mInstance = new AppClient();
        return mInstance;
    }

    public <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder httpClient = getOKHttpClient();
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public <S> S createServiceWithAuth(Class<S> serviceClass, Context context) {
        final TinyDB db = new TinyDB(context);
        Interceptor interceptorReq = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("token", db.getString(AppConstants.ACCESS_TOKEN)).build();
                Log.e("Header====", db.getString(AppConstants.ACCESS_TOKEN));
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder httpClient = getOKHttpClient();
        httpClient.addInterceptor(interceptorReq);
        OkHttpClient okHttpClient = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    private OkHttpClient.Builder getOKHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(httpLoggingInterceptor);
        }

        httpClient.connectTimeout(15, TimeUnit.SECONDS);
        httpClient.writeTimeout(15, TimeUnit.SECONDS);
        httpClient.readTimeout(15, TimeUnit.SECONDS);

        return httpClient;
    }

}
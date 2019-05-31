package com.solodilov.lifecycle.api;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.solodilov.lifecycle.Const;
import com.solodilov.lifecycle.LifeCycleApplication;
import com.solodilov.lifecycle.model.SearchRequests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static ApiManager singleton;
    private final ApiInterface service;
    private Retrofit retrofit;

    private ApiManager() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(interceptor);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build();
        service = retrofit.create(ApiInterface.class);
    }

    public static ApiManager getInstance() {
        if (singleton == null) {
            singleton = new ApiManager();
        }
        return singleton;
    }

    public Single<SearchRequests> getForesmatic() {
        Map<String,String> body = new HashMap<>();
        body.put("method", "getQuote");
        body.put("format", "json");
        body.put("lang", "ru");

        return service.getForesmatic(body);
    }

    public List<SearchRequests> getListPagin(int requestedStartPosition, int pageSize) {
        return null;
    }
}

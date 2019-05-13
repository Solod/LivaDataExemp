package com.solodilov.lifecycle.api;

import com.solodilov.lifecycle.model.SearchRequests;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    @Headers("Content-Type: application/json")
    @GET(".")
    Single<SearchRequests> getForesmatic(
            @QueryMap Map<String, String> body
    );
}

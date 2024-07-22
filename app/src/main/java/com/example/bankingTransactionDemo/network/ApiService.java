package com.example.bankingTransactionDemo.network;

import com.example.bankingTransactionDemo.model.Application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * An interface which has the api call GET
 */

public interface ApiService {
    @GET("transaction/628d91b8a8d1c4935c8f3e74/{page}")
    Call<Application> getTransaction(@Path("page") int page, @Header("appid") String appid);
}

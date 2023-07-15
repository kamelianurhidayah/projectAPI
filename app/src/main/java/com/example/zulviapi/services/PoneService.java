package com.example.zulviapi.services;

import com.example.zulviapi.model.Pones;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PoneService {

    @GET("/pones/{id}")
    Call<Pones> getPonesById(@Path("id") int id);

    @POST("/pones/")
    Call<Pones> createPones(@Body Pones pones);

    @PUT("/pones/{id}")
    Call<Pones> updatePonesById(@Path("id") int id, @Body Pones pones);

    @DELETE("/pones/{id}")
    Call<Void> deletePones(@Path("id") int id);
}

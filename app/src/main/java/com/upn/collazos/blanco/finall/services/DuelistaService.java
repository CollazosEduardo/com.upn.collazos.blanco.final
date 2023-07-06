package com.upn.collazos.blanco.finall.services;

import com.upn.collazos.blanco.finall.model.Duelista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DuelistaService {
    @GET("duelistas")
    Call<List<Duelista>> getAll();

    @POST("duelistas")
    Call<Duelista> create(@Body Duelista duelista);
}

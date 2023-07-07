package com.upn.collazos.blanco.finall.services;

import com.upn.collazos.blanco.finall.model.Carta;
import com.upn.collazos.blanco.finall.model.Duelista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DuelistaService {
    @GET("duelistas")
    Call<List<Duelista>> getAll();

    @POST("duelistas")
    Call<Duelista> create(@Body Duelista duelista);

    @PUT("duelistas/{id}")
    Call<Duelista> update(@Path("id") int id, @Body Duelista duelista);
}

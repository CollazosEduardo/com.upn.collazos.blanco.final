package com.upn.collazos.blanco.finall.services;

import com.upn.collazos.blanco.finall.model.Carta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartasService {
    @GET("cartas")
    Call<List<Carta>> getAll();

    @POST("cartas")
    Call<Carta> create(@Body Carta carta);

    @PUT("Cartas/{id}")
    Call<Carta> update(@Path("id") int id, @Body Carta cartas);

}

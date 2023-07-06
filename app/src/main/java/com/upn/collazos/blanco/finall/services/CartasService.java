package com.upn.collazos.blanco.finall.services;

import com.upn.collazos.blanco.finall.model.Carta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CartasService {
    @GET("cartas")
    Call<List<Carta>> getAll();

    @POST("cartas")
    Call<Carta> create(@Body Carta carta);
}

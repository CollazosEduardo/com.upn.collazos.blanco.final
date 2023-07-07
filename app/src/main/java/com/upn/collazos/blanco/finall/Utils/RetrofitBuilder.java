package com.upn.collazos.blanco.finall.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public static Retrofit build() {
        return new Retrofit.Builder()
                .baseUrl("https://64a6b8de096b3f0fcc806f8a.mockapi.io/final/") // revisar
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

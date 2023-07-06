package com.upn.collazos.blanco.finall.services;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;


public interface UserService {

    @POST("image")
    Call<ImageResponse> saveImage(@Body ImageToSave image);

    class ImageResponse {
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }
    }
    class ImageToSave {
        String base64Image;

        public ImageToSave(String base64Image) {
            this.base64Image = base64Image;
        }
    }
}



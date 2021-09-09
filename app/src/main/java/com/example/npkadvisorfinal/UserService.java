package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    //Añadir un nuevo usuario
    @NonNull
    @POST("persona/AddPersona/")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    //Settings Login Authentication
    @NonNull
    @POST("persona/login/")
    Call<UserResponse>SignIn(@Body UserRequest userRequest);

    //añadir un cultivo
    @NonNull
    @POST("cultivo")
    Call<CropResponse>saveCrop(@Body CropResponse2 cropRequest);

    //consultar todos los cultivos
    @NonNull
    @GET("cultivo")
    Call<CropResponse>findAllC();

    //Consultar variables Humedad, Npk, Temperatura
    @NonNull
    @GET("index")
    Call<IndexResponse>findIndex();

    //Consultar variables Humedad, NPK, Temperatura
    @NonNull
    @GET("index")
    Call<IndexResponse>findIndex1();


}

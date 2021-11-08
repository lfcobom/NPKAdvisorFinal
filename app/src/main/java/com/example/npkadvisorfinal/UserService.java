package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    //Añadir un nuevo usuario
    @NonNull
    @POST("persona")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    @NonNull
    @GET("persona")
    Call<UserResponse> FindUser();

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

    @NonNull
    @DELETE("cultivo/{Id}")
    Call<CropResponse>delete(@Path("Id") String cropId);


}

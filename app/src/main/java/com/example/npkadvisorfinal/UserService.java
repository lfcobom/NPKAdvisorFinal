package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserService {

    //Añadir un nuevo usuario
    @NonNull
    @POST("persona")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);

    //Encontrar usuarios
    @NonNull
    @GET("persona")
    Call<UserResponse> FindUser();

    //Login Authentication
    @NonNull
    @POST("persona/login")
    Call<LoginModel>login(@Body Login login);

    //Secret token authentication
    @NonNull
    @GET("cultivo")
    Call<ResponseBody> getSecret(@Header("Authorization")String authToken);


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

    //Eliminar cultivo
    @NonNull
    @DELETE("cultivo/{Id}")
    Call<CropResponse>delete(@Path("Id") String cropId);
    
    //Actualizar cultivos
    @Headers({"Content-Type: application/json"})
    @PUT("cultivo/{id}")
    Call<CropResponse> updateCrop (@Path("id") String id, @Body CropResponse2 body);


}

package com.example.rsa.androidnativobench.networking;

import com.example.rsa.androidnativobench.networking.requestBodys.LogInRequestBody;
import com.example.rsa.androidnativobench.networking.requestBodys.RegisterRequestBody;
import com.example.rsa.androidnativobench.networking.responseBodys.ListBoxsResponse;
import com.example.rsa.androidnativobench.networking.responseBodys.ListPatientsResponse;
import com.example.rsa.androidnativobench.networking.responseBodys.LogInResponse;
import com.example.rsa.androidnativobench.networking.responseBodys.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCallInterface {
//@Header("Authorization-token") String authorization,


    @POST("/login")
    Call<LogInResponse> login(@Body LogInRequestBody logInRequestBody);

    @POST("/register")
    Call<RegisterResponse> register(@Body RegisterRequestBody registerRequestBody);

    @GET("/vitabox")
    Call<ListBoxsResponse> listBoxs();

    @GET("/vitabox/{id}/patient")
    Call<ListPatientsResponse> listPatients(@Path("id") String id);

//    @PUT("addrs/{id}")
//    Call<responseClass> method(@Path("id") String id, @Body requestclass requestClass);

//    @FormUrlEncoded
//    @POST("addrs")
//    Call<ResponseClass> method(@Field("fieldName") String fieldName, @Field("anotherFieldName") String anotherFieldName);

}
package com.example.ricardo.androidnativeminiapp.networking;

import com.example.ricardo.androidnativeminiapp.networking.requestBodys.BoxRegistrationRequest;
import com.example.ricardo.androidnativeminiapp.networking.requestBodys.LogInRequestBody;
import com.example.ricardo.androidnativeminiapp.networking.requestBodys.RegisterRequestBody;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.ListBoxsResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.ListPatientsResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.LogInResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.RegisterResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.boards.ListBoardsResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.boxRegistration.BoxRegistrationResponse;
import com.example.ricardo.androidnativeminiapp.networking.responseBodys.users.ListUsersResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("/vitabox/{id}/board")
    Call<ListBoardsResponse> listBoards(@Path("id") String id);

    @GET("/vitabox/{id}/user")
    Call<ListUsersResponse> listUsers(@Path("id") String id);

    @POST("/vitabox/{id}/register")
    Call<BoxRegistrationResponse> registerBox(@Path("id") String id, @Body BoxRegistrationRequest boxRegistrationRequest);

//    @FormUrlEncoded
//    @POST("addrs")
//    Call<ResponseClass> method(@Field("fieldName") String fieldName, @Field("anotherFieldName") String anotherFieldName);

}
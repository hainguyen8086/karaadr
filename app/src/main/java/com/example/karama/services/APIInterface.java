package com.example.karama.services;

import com.example.karama.model.ResToken;
import com.example.karama.model.ResTokenRefresh;
import com.example.karama.model.ResUser;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface APIInterface {
    @GET("inside/GetInformationExam")
    Call<List<String>> getInformationExam(@HeaderMap Map<String, String> headers,
                                                   @QueryMap Map<String, String> params);

    @POST("mPharmacy/Service.svc/GetInformationExam")
    Call<String> getInforExam(@Body RequestBody body);

    @POST("auth")
    @FormUrlEncoded
    Call<ResToken> getToken(@Field("username") String username,
                            @Field("password") String password);

    @POST("auth/refresh")
    Call<ResTokenRefresh> getRefreshToken(@HeaderMap Map<String, String> headers);


    @POST("mPharmacy/Service.svc/GetInformationExam")
    Call<String> checkExam(@Body RequestBody body);

    @GET("users/2")
    Call<ResUser> getUser();

}

package com.example.karama.services;

import com.example.karama.model.Res3ListStaff;
import com.example.karama.model.Res4AddStaff;
import com.example.karama.model.ResAllCustomer;
import com.example.karama.model.ResNullData;
import com.example.karama.model.ResProducts;
import com.example.karama.model.ResProfile;
import com.example.karama.model.ResToken;
import com.example.karama.model.ResTokenRefresh;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface KaraInterface {
    //===pack Auth===
    //api1  1.Lấy Refresh token và Access token
    @POST("auth")
    @FormUrlEncoded
    Call<ResToken> get2Token(@Field("username") String username,
                            @Field("password") String password);

    //api2  2.Lấy Access Token từ Fresh Token
    @GET("auth/refresh")
    Call<ResTokenRefresh> getAccessToken(@HeaderMap Map<String, String> headers);

    //api 47.Xem danh sách sản phẩm CHECK TOKEN
    @GET("products-manager")
    Call<ResProducts> checkTokenSeeProduct(@HeaderMap Map<String, String> headers,
                                           @QueryMap HashMap<String, String> body);



    //=== pack staff ===
    //api3.Lấy danh sách nhân viên
    @GET("staffs")
    Call<Res3ListStaff> getListStaff(@HeaderMap Map<String, String> headers,
                                     @QueryMap HashMap<String, String> body);

    //api 4.Thêm nhân viên
    @POST("staffs")
    Call<Res4AddStaff> addStaff(@HeaderMap Map<String, String> headers,
                                @Body RequestBody body);

    //api 5.Cập nhật thông tin nhân viên
    @POST("staffs/update/{username}")
    Call<Res4AddStaff> updateStaff(@Path(value = "username", encoded = true) String username,
                                   @HeaderMap Map<String, String> headers,
                                   @Body RequestBody body);

    //api 6.Xem thông tin nhân viên theo username
    @GET("staffs/{username}")
    Call<ResProfile> getDataUser(@Path(value = "username", encoded = true) String username,@HeaderMap Map<String, String> headers);
    //api 7.Đổi mật khẩu dành của người dùng hiện tại
    @POST("staffs/password")
    Call<ResNullData> changePassUser(@HeaderMap Map<String, String> headers,
                                 @Body RequestBody body);
    //api 8.Thay đổi mật khẩu của nhân viên bất kì dành cho quản lý
    @POST("staffs/password/{usernamestaff}")
    Call<ResNullData> changPassStaff(@Path(value = "usernamestaff", encoded = true) String usernamestaff,
                                     @HeaderMap Map<String, String> headers,
                                     @Body RequestBody body);
    //pack ====Rooom booking =====
    //pack customer
    //api 40.Xem danh sách khách hàng
    @GET("guests-manager")
    Call<ResAllCustomer> allCustomer(@HeaderMap Map<String, String> headers,
                                     @QueryMap HashMap<String, String> body);






















}

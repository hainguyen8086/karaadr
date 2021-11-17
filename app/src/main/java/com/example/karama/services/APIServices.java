package com.example.karama.services;

import android.content.Context;
import android.util.Log;

import com.example.karama.helper.APIHelper;
import com.example.karama.helper.CallbackResponse;
import com.example.karama.helper.Config;
import com.example.karama.model.ResToken;
import com.example.karama.model.ResTokenRefresh;
import com.example.karama.model.ResUser;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIServices {
    public static void getAccessRefreshToken( CallbackResponse callbackResponse, String user, String pass) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", user);
            jsonObject.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("==prGetToken1:", jsonObject.toString());
        RequestBody requestBody = RequestBody.create(APIHelper.JSON, jsonObject.toString());
        APIClient.getClient(Config.URL).create(APIInterface.class)
                .getToken(user,pass)
                .enqueue(new Callback<ResToken>() {
                    @Override
                    public void onResponse(Call<ResToken> call, Response<ResToken> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResToken> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });

    }

    public static void checkExam(Context context, CallbackResponse callbackResponse, String employee) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Employcode", employee);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("==prExam:", jsonObject.toString());
        RequestBody requestBody = RequestBody.create(APIHelper.JSON, jsonObject.toString());
        APIClient.getClient(Config.URL_PHARMA).create(APIInterface.class)
                .checkExam(requestBody)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }

    public static void getUser(CallbackResponse callbackResponse) {
        APIClient.getClient(Config.URL_REQRES).create(APIInterface.class)
                .getUser()
                .enqueue(new Callback<ResUser>() {
                    @Override
                    public void onResponse(Call<ResUser> call, Response<ResUser> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResUser> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }
    public static void getAccessToken(CallbackResponse callbackResponse,String token) {
        APIClient.getClient(Config.URL).create(APIInterface.class)
                .getRefreshToken(APIHelper.API_HEADER(""))
                .enqueue(new Callback<ResTokenRefresh>() {
                    @Override
                    public void onResponse(Call<ResTokenRefresh> call, Response<ResTokenRefresh> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResTokenRefresh> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }
}

package com.example.karama.services;

import com.example.karama.data.SharedPrefManager;
import com.example.karama.helper.APIHelper;
import com.example.karama.helper.CallbackResponse;
import com.example.karama.helper.UrlConfig;
import com.example.karama.model.ResNullData;
import com.example.karama.model.room.Res13ListOrder;
import com.example.karama.model.room.Res9Reserve;
import com.example.karama.model.room.ResCheckin;
import com.example.karama.model.room.ResListRoomEmpty;
import com.example.karama.model.room.ResOrder;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RumServices {
    //9.Đặt phòng trước
    public static void bookingRoom(CallbackResponse callbackResponse, String strBook) {
        RequestBody requestBody = RequestBody.create(APIHelper.JSON, strBook);
        APIClient.getClient(UrlConfig.KARA).create(KaraInterface.class)
                .booking(APIHelper.API_HEADER(SharedPrefManager.getAccessToken()), requestBody)
                .enqueue(new Callback<Res9Reserve>() {
                    @Override
                    public void onResponse(Call<Res9Reserve> call, Response<Res9Reserve> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Res9Reserve> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }

    //api 10.Check-in phòng đã đặt trước
    public static void checkIn(CallbackResponse callbackResponse, String bookID) {
        APIClient.getClient(UrlConfig.KARA).create(KaraInterface.class)
                .checkin(bookID,APIHelper.API_HEADER(SharedPrefManager.getAccessToken()))
                .enqueue(new Callback<ResCheckin>() {
                    @Override
                    public void onResponse(Call<ResCheckin> call, Response<ResCheckin> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResCheckin> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }

    //api 11.Đặt phòng trực tiếp
    public static void bookLive(CallbackResponse callbackResponse, String strBook) {
        RequestBody requestBody = RequestBody.create(APIHelper.JSON, strBook);
        APIClient.getClient(UrlConfig.KARA).create(KaraInterface.class)
                .booklive(APIHelper.API_HEADER(SharedPrefManager.getAccessToken()),requestBody)
                .enqueue(new Callback<Res9Reserve>() {
                    @Override
                    public void onResponse(Call<Res9Reserve> call, Response<Res9Reserve> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Res9Reserve> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }

    //api 12.Huỷ đặt phòng trước
    public static void cancelBook(CallbackResponse callbackResponse, String id) {
        APIClient.getClient(UrlConfig.KARA).create(KaraInterface.class)
                .cancelBooking(id,APIHelper.API_HEADER(SharedPrefManager.getAccessToken()))
                .enqueue(new Callback<ResNullData>() {
                    @Override
                    public void onResponse(Call<ResNullData> call, Response<ResNullData> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResNullData> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }

    //api 13.Xem danh sách phiếu đặt phòng theo mã phòng và trạng thái
    public static void getListOrder(CallbackResponse callbackResponse, String id) {
        APIClient.getClient(UrlConfig.KARA).create(KaraInterface.class)
                .getListOrder(id,APIHelper.API_HEADER(SharedPrefManager.getAccessToken()))
                .enqueue(new Callback<Res13ListOrder>() {
                    @Override
                    public void onResponse(Call<Res13ListOrder> call, Response<Res13ListOrder> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Res13ListOrder> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }

    //api 14.Xem phiếu đặt phòng theo mã phiếu đặt phòng
    public static void getOrderByBookingID(CallbackResponse callbackResponse, String id) {
        APIClient.getClient(UrlConfig.KARA).create(KaraInterface.class)
                .getOrderByBookingID(id,APIHelper.API_HEADER(SharedPrefManager.getAccessToken()))
                .enqueue(new Callback<ResOrder>() {
                    @Override
                    public void onResponse(Call<ResOrder> call, Response<ResOrder> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResOrder> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }

    //api 15 Xem danh sách phòng trống trong khoảng thời gian
    public static void getListRoomEmpty(CallbackResponse callbackResponse, String stringTime) {
        RequestBody requestBody = RequestBody.create(APIHelper.JSON, stringTime);
        APIClient.getClient(UrlConfig.KARA).create(KaraInterface.class)
                .getListRoomEmpty(APIClient.API_HEADER(SharedPrefManager.getAccessToken()),requestBody)
                .enqueue(new Callback<ResListRoomEmpty>() {
                    @Override
                    public void onResponse(Call<ResListRoomEmpty> call, Response<ResListRoomEmpty> response) {
                        if (response.isSuccessful()) {
                            callbackResponse.Success(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResListRoomEmpty> call, Throwable t) {
                        callbackResponse.Error(t.getMessage());
                    }
                });
    }
}

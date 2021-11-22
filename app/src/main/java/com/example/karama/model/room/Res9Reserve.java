package com.example.karama.model.room;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Res9Reserve {
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private DataReserveRoom data;

}

package com.example.karama.model.order;

import com.example.karama.model.room.DataCheckin;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResBill {
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
    private DataBill data;
}

package com.example.karama.model.room;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResCheckin {
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
    private DataCheckin data;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataCheckin getData() {
        return data;
    }

    public void setData(DataCheckin data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResCheckin{" +
                "timestamp='" + timestamp + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

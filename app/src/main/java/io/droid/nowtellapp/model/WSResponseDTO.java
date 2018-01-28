package io.droid.nowtellapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Zeeshan on 1/26/2018.
 */

public class WSResponseDTO<T> {
    @Expose
    @SerializedName("status")
    String status;
    @Expose
    @SerializedName("message")
    String message;
    @Expose
    @SerializedName("payload")
    T payload;
    @Expose
    @SerializedName("errorCode")
    String errorCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

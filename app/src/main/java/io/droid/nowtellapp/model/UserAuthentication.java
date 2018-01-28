package io.droid.nowtellapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Zeeshan on 1/26/2018.
 */

public class UserAuthentication {
    @Expose
    @SerializedName("isAuthenticated")
    boolean isAuthenticated;
    @Expose
    @SerializedName("token")
    String token;
    @Expose
    @SerializedName("expiryDate")
    String expiryDate;

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}

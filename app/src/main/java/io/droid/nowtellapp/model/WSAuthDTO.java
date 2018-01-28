package io.droid.nowtellapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zeeshan on 1/26/2018.
 */

public class WSAuthDTO {
    @SerializedName("authentication")
    UserAuthentication authentication;

    public UserAuthentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(UserAuthentication authentication) {
        this.authentication = authentication;
    }
}

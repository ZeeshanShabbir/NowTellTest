package io.droid.nowtellapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Zeeshan on 1/26/2018.
 */

public class UserSignup {
    @Expose
    @SerializedName("isRegistered")
    boolean isRegistered;

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }
}

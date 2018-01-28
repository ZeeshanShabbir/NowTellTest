package io.droid.nowtellapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Zeeshan on 1/26/2018.
 */

public class WSSignUpDTO {
    @Expose
    @SerializedName("signUp")
    UserSignup signup;

    public UserSignup getSignup() {
        return signup;
    }

    public void setSignup(UserSignup signup) {
        this.signup = signup;
    }
}

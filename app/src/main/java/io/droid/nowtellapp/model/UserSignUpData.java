package io.droid.nowtellapp.model;

import java.util.HashMap;

/**
 * Created by Zeeshan on 1/27/2018.
 */

public class UserSignUpData {

    private HashMap<String, Object> param = new HashMap<>();

    private static final UserSignUpData ourInstance = new UserSignUpData();

    public static UserSignUpData getInstance() {
        return ourInstance;
    }

    private UserSignUpData() {
    }

    public HashMap<String, Object> getParam() {
        return param;
    }

    public void setParam(HashMap<String, Object> param) {
        this.param = param;
    }
}

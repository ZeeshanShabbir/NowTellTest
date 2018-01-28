package io.droid.nowtellapp.mvp;

import android.support.annotation.StringRes;

/**
 * Created by Zeeshan on 1/27/2018.
 */

public interface SignUpStepOneMvp {
    interface View {
        void showSignUpSecondScreen();
    }

    interface Presenter {
        void handleNext(String email,
                        String password,
                        String type,
                        String firstName,
                        String lastName,
                        String dob,
                        String contactNo,
                        String mobileNo, boolean checked);
    }
}

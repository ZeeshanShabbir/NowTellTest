package io.droid.nowtellapp.mvp;

import android.support.annotation.StringRes;

/**
 * Created by Zeeshan on 1/28/2018.
 */

public interface SignUpStepTwoMvp {
    interface View {
        void showError(@StringRes int error);

        void showToast(String message);

        void showLoginScreen();

        void showProgress();

        void hideProgress();
    }

    interface Presenter {
        void handleSignUp(String address, String addressLineOne, String country,
                          String city, String county, String postalCode, String addressSp,
                          String addressLineOneSp, String countrySp, String citySp, String countySp,
                          String postalCodeSp, String addressBl, String addressLineOneBl,
                          String countryBl, String cityBI, String countyBl, String postalCodeBl);

        void handleNoInternet();

        void handleDetach();
    }
}

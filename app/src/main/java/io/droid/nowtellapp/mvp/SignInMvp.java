package io.droid.nowtellapp.mvp;

import android.support.annotation.StringRes;

/**
 * Created by Zeeshan on 1/25/2018.
 */

public interface SignInMvp {
    interface View {
        void hideProgress();

        void showProgress();

        void showError(@StringRes int error);

        void showToast(String message);

        void showSignUp();

        void showHomeScreen();
    }

    interface Presenter {

        void handleLogin(String username, String password);

        void handleSignUp();

        void handleNoInternet();
    }
}

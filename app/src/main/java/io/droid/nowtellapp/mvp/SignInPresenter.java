package io.droid.nowtellapp.mvp;

import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;

import java.util.HashMap;

import io.droid.nowtellapp.R;
import io.droid.nowtellapp.model.WSAuthDTO;
import io.droid.nowtellapp.model.WSResponseDTO;
import io.droid.nowtellapp.util.Constants;
import io.droid.nowtellapp.util.Utils;
import io.droid.nowtellapp.webservices.NowTellApi;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zeeshan on 1/25/2018.
 */

public class SignInPresenter implements SignInMvp.Presenter {


    private final SharedPreferences sharedPreferences;

    private final SignInMvp.View view;
    private final NowTellApi nowTellApi;

    public SignInPresenter(SharedPreferences sharedPreferences, SignInMvp.View view, NowTellApi nowTellApi) {
        this.sharedPreferences = sharedPreferences;
        this.view = view;
        this.nowTellApi = nowTellApi;
    }

    @Override
    public void handleLogin(String username, String password) {
        if (Utils.isNullOrEmpty(username) || Utils.isNullOrEmpty(password)) {
            //view.showError();
            view.showError(R.string.error_fill_all_fields);
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            view.showError(R.string.error_invalid_email);
            return;
        }
        view.showProgress();
        HashMap<String, Object> body = new HashMap<>();
        body.put("email", username);
        body.put("Password", password);
        body.put("IPAddress", "198.160.1.0"); //using this ip because have no idea which to use.
        HashMap<String, String> header = new HashMap<>();
        header.put("User-Agent", "Android");
        header.put("Authorization", Utils.getAuthorizationToken().replace("\n", ""));
        header.put("Content-Type", "Application/json; charset=utf-8");

        nowTellApi.loginUser(body, header)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<WSResponseDTO<WSAuthDTO>>() {
                    @Override
                    public void accept(WSResponseDTO<WSAuthDTO> wsAuthDTOWSResponseDTO) throws Exception {
                        //Log.d("TAG", wsAuthDTOWSResponseDTO.getMessage());
                        view.hideProgress();
                        if (wsAuthDTOWSResponseDTO.getPayload() != null &&
                                wsAuthDTOWSResponseDTO.getPayload().getAuthentication() != null &&
                                wsAuthDTOWSResponseDTO.getPayload().getAuthentication().isAuthenticated()) {
                            sharedPreferences.edit().putString(Constants.SESSION_TOKEN,
                                    wsAuthDTOWSResponseDTO.getPayload().getAuthentication()
                                            .getToken()).apply();
                            sharedPreferences.edit().putString(Constants.SESSION_TOKEN_EXPIRY,
                                    wsAuthDTOWSResponseDTO.getPayload().getAuthentication()
                                            .getExpiryDate()).apply();
                            view.showHomeScreen();
                        } else {
                            view.showToast(wsAuthDTOWSResponseDTO.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("TAG", throwable.getMessage());
                        view.showToast(throwable.getLocalizedMessage());
                        view.hideProgress();
                    }
                });
    }

    @Override
    public void handleSignUp() {
        view.showSignUp();
    }

    @Override
    public void handleNoInternet() {
        view.showError(R.string.no_internet_connection);
    }
}

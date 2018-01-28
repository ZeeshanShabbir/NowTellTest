package io.droid.nowtellapp.dagger;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.droid.nowtellapp.mvp.SignInMvp;
import io.droid.nowtellapp.mvp.SignInPresenter;
import io.droid.nowtellapp.webservices.NowTellApi;

/**
 * Created by Zeeshan on 1/25/2018.
 */
@Module
public class SignInMvpModule {
    private final SignInMvp.View view;

    public SignInMvpModule(SignInMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    public SignInMvp.Presenter presenter(SharedPreferences sharedPreferences,NowTellApi api) {
        return new SignInPresenter(sharedPreferences, view,api);
    }
}

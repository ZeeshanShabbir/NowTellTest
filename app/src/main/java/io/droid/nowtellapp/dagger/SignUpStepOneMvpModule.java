package io.droid.nowtellapp.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.droid.nowtellapp.mvp.SignUpStepOneMvp;
import io.droid.nowtellapp.mvp.SignUpStepOnePresenter;

/**
 * Created by Zeeshan on 1/27/2018.
 */
@Module
public class SignUpStepOneMvpModule {
    private final SignUpStepOneMvp.View view;

    public SignUpStepOneMvpModule(SignUpStepOneMvp.View view) {
        this.view = view;
    }

    @Singleton
    @Provides
    SignUpStepOneMvp.Presenter presenter() {
        return new SignUpStepOnePresenter(view);
    }
}

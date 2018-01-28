package io.droid.nowtellapp.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.droid.nowtellapp.mvp.SignUpStepTwoMvp;
import io.droid.nowtellapp.mvp.SignUpStepTwoPresenter;
import io.droid.nowtellapp.webservices.NowTellApi;

/**
 * Created by Zeeshan on 1/28/2018.
 */
@Module
public class SignUpStepTwoMvpModule {
    private final SignUpStepTwoMvp.View view;

    public SignUpStepTwoMvpModule(SignUpStepTwoMvp.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    SignUpStepTwoMvp.Presenter presenter(NowTellApi api) {
        return new SignUpStepTwoPresenter(view,api);
    }

}

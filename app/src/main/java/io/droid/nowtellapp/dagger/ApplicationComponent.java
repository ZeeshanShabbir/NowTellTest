package io.droid.nowtellapp.dagger;

import javax.inject.Singleton;

import dagger.Component;
import io.droid.nowtellapp.mvp.SignUpStepTwoMvp;
import io.droid.nowtellapp.ui.activities.MainActivity;
import io.droid.nowtellapp.ui.activities.SplashActivity;

/**
 * Created by Zeeshan on 1/25/2018.
 */
@Singleton
@Component(modules = {AppModule.class, RetroApiModule.class})
public interface ApplicationComponent {

    void inject(SplashActivity splashActivity);

    SignInMvpComponet signInMvpComponet(SignInMvpModule signInMvpModule);

    SignUpStepOneComponent signUpStepOneMvpComponet(SignUpStepOneMvpModule signUpStepOneMvpModule);

    SignUpStepTwoComponent signUpStepTwoMvpComponet(SignUpStepTwoMvpModule signUpStepTwoMvpModule);
}

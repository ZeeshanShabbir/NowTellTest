package io.droid.nowtellapp.dagger;

import javax.inject.Singleton;

import dagger.Subcomponent;
import io.droid.nowtellapp.ui.fragments.SignUpStepTwoFragment;

/**
 * Created by Zeeshan on 1/28/2018.
 */
@Singleton
@Subcomponent(modules = {SignUpStepTwoMvpModule.class})
public interface SignUpStepTwoComponent {
    void inject(SignUpStepTwoFragment signUpStepTwoFragment);
}

package io.droid.nowtellapp.dagger;

import javax.inject.Singleton;

import dagger.Subcomponent;
import io.droid.nowtellapp.ui.fragments.SignUpStepOneFragment;

/**
 * Created by Zeeshan on 1/27/2018.
 */
@Singleton
@Subcomponent(modules = {SignUpStepOneMvpModule.class})
public interface SignUpStepOneComponent {
    void inject(SignUpStepOneFragment signUpStepOneFragment);
}

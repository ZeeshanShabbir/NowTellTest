package io.droid.nowtellapp.dagger;


import javax.inject.Singleton;

import dagger.Subcomponent;
import io.droid.nowtellapp.ui.fragments.SignInFragment;

/**
 * Created by Zeeshan on 1/25/2018.
 */
@Singleton
@Subcomponent(modules = {SignInMvpModule.class})
public interface SignInMvpComponet {
    void inject(SignInFragment signInFragment);
}

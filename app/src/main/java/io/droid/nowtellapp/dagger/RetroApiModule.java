package io.droid.nowtellapp.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.droid.nowtellapp.webservices.NowTellApi;
import io.droid.nowtellapp.webservices.RetrofitNowTellApi;

/**
 * Created by Zeeshan on 1/26/2018.
 */
@Module(includes = {NetworkModule.class})
public class RetroApiModule {
    @Provides
    @Singleton
    NowTellApi provideNowTellApi(RetrofitNowTellApi impl) {
        return impl;
    }
}

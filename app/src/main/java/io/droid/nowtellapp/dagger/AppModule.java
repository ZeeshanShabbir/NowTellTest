package io.droid.nowtellapp.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.droid.nowtellapp.NowTellApp;

/**
 * Created by Zeeshan on 1/25/2018.
 */
@Module
public class AppModule {
    private final NowTellApp nowTellApp;

    public AppModule(NowTellApp nowTellApp) {
        this.nowTellApp = nowTellApp;
    }

    @Provides
    @Singleton
    NowTellApp provideNowTellApp() {
        return nowTellApp;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return nowTellApp;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}

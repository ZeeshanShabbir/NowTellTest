package io.droid.nowtellapp;

import android.app.Application;
import android.content.Context;

import io.droid.nowtellapp.dagger.AppModule;
import io.droid.nowtellapp.dagger.ApplicationComponent;
import io.droid.nowtellapp.dagger.DaggerApplicationComponent;

/**
 * Created by Zeeshan on 1/24/2018.
 */

public class NowTellApp extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }


    public static NowTellApp get(Context context) {
        return (NowTellApp) context.getApplicationContext();
    }


}

package io.droid.nowtellapp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import io.droid.nowtellapp.NowTellApp;
import io.droid.nowtellapp.util.Constants;

public class SplashActivity extends AppCompatActivity {

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NowTellApp.get(this).getComponent().inject(this);

        if (sharedPreferences.getString(Constants.SESSION_TOKEN, null) != null) {

        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }
}
